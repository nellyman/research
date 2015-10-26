package uk.co.honda.hi.ssdif;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author uinxh
 *
 * Class to get locations of nodes within an XML schema document.
 * Using getInstance to get a new instance the schemaFilePath argument will provide
 * an instance to deal with that particular schema.
 * 
 * Elements are cached, first document through the locator will be slow, whereas
 * subsequent ones much faster.
 * 
 * If the schema is changed (timestamp checked) then the locator will reload the 
 * schema document. The cache will be cleared, hence slow performance for the first
 * document through.  
 * 
 * 
 */
public class SSDiffNodeLocator {


	private SoftReference documentWrapper=null;
	private SoftReference cacheWrapper=null;
	
	private static Map instances=null;
	
	private String schemaFileName=null; 
	private long schemaLastModified;

	
	
	private static final String XPATHSTART = "count(//*[@name='";
	private static final String XPATH_SUBNODE =	"']//*[@name='";
	private static final String XPATHEND = "']/preceding-sibling::*)+1";
	
	private static Logger logger =
		Logger.getLogger(SSDiffNodeLocator.class.getName());	

	
	
	private SSDiffNodeLocator(String schemaLocation)throws IOException{
		schemaFileName = schemaLocation;
		cacheWrapper = new SoftReference(new HashMap());
	}	
	
	/**
	 * Returns an SSDiffNodeLocator for a particular schemaLocation.
	 * @param schemaLocation location of schema file.
	 * @return SSDiffNodeLocator working with that schema file.
	 * @throws IOException
	 */
	public static SSDiffNodeLocator getInstance(String schemaLocation) throws IOException{
		
		SSDiffNodeLocator locator=null;
		
		if (instances==null){
			instances= new HashMap();
		}
		
		if (instances.containsKey(schemaLocation)){
			locator = (SSDiffNodeLocator)instances.get(schemaLocation);
			logger.debug("Already have a SSDiffNodeLocator for: "+schemaLocation);
		}else{
			locator = new SSDiffNodeLocator(schemaLocation);
			instances.put(schemaLocation, locator);
			logger.debug("Creating SSDiffNodeLocator for: "+schemaLocation);	
		}
		return locator;
	}
	
	

	public int[] getPositionOfAllElements(String[] nodeNames) throws IOException{
		
		this.checkReloadSchema();
		
		int[] positions = new int[nodeNames.length];
		List currentList=new ArrayList();
		int result;
		String cacheKey;
		
		Map cache = this.getCache();
		
		for (int i=0;i<nodeNames.length;i++){
			currentList.add(i, nodeNames[i]);
			
			cacheKey = currentList.toString();
			
			if (cache.containsKey(cacheKey)){
				result = ((Integer)cache.get(cacheKey)).intValue();
			}else{
				result = this.getAbsolutePosition(currentList);
				cache.put(cacheKey, new Integer(result));
			}
						
			positions[i]=result;
		}
		return positions;	
	}

	


	/**
	 * Gets called recursively by getPositionOfAllElements builds up the 
	 * node tree for each nodeName
	 * @param categoryNodeWithsubNode e.g. 'Person/Name3'
	 * @return
	 */
	private int getAbsolutePosition(List nodeNames) throws IOException{
		
		int location=-1;

		// need to check that node exists in document...

		String coreXPath = this.createXPathNoEnd(nodeNames);
		String doesExists = coreXPath+"'])";
		String getPosition=coreXPath+XPATHEND;
		 
		if (isNodeInDocument(doesExists)==false){
			logger.error("node referenced by "+doesExists+" not in document!");
			return -1;
			
		}		
		location = this.getNodePosition(getPosition);		
		return location;
	}
	
	


	private int getNodePosition(String xPath) throws IOException{
		
		int location=-1;
		try {
			
			Document schema = getSchemaDocument();
			String str = XPathAPI.eval(schema, xPath).toString();
			
			if (!"".equals(str)){
				Integer loc = new Integer(str);
				location = loc.intValue();
			}
			
		} catch (TransformerException e) {
			throw new IOException("Error running Xpath: "+xPath+" error:  "+e.getMessage());
		}
		return location;
	}
	


	private boolean isNodeInDocument(String xPath) throws IOException{
		boolean result=false;
				
		try {
			
			Document schema = getSchemaDocument();
			
			String str = XPathAPI.eval(schema, xPath).toString();
			if ((Integer.parseInt(str))>0){
				result=true;
			}
		} catch (TransformerException e) {
			throw new IOException("Error running Xpath: "+xPath+" error:  "+e.getMessage());
		}
		return result;
	}	
	

	/**
	 * 
	 * Checks the timestamp on the schema document.
	 * If it is different from previous checks then reloads the document
	 * and resets the cache.
	 */
	private void checkReloadSchema(){	
		// check updated timestamp.
		File schemaFile = new File (schemaFileName);
		long thislastModified = schemaFile.lastModified();
		// if it is different then throw schema away...
		if (thislastModified!=schemaLastModified){
			schemaLastModified=thislastModified;
			logger.info("Schema document timestamp has changed. Going to reload the schema document.");
			schemaLastModified=thislastModified;
			// setting documentWrapper to null will cause a reload of the schema file.
			documentWrapper=null;
			// the cache requires resetting so that calls to documentWrapper will be used.
			// and hence as its null, a reload of the document is required.
			cacheWrapper = new SoftReference(new HashMap());			
		}
	}
	
	
	/**
	 * Lazily creates the schema Document to be used within the class.
	 * @return Document, reference to the XML Schema Document.
	 * @throws IOException if can't get it.
	 */
	private synchronized Document getSchemaDocument() throws IOException {

		Document schemaDocument=null;
		
		if (documentWrapper!=null){
			schemaDocument=(Document)documentWrapper.get();	
		}
		

		if (schemaDocument== null) {
			
			DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(false);
			DocumentBuilder builder;
			try {

				builder = factory.newDocumentBuilder();
				schemaDocument = builder.parse(schemaFileName);
				documentWrapper = new SoftReference(schemaDocument);
				// create a new Cache... 
				cacheWrapper = new SoftReference(new HashMap());

			} catch (ParserConfigurationException e) {
				throw new IOException(
					"Problem parsing document " + e.getMessage());
			} catch (SAXException e) {
				throw new IOException(
					"Problem Sax Exception " + e.getMessage());
			}
		}
		return schemaDocument;
	}



	/**
	 * 
	 * @param nodeNames
	 * @return String of XPath query with open end so ending can be appended to it e.g. for count or position.
	 */
	private String createXPathNoEnd(List nodeNames){
		
		StringBuffer buffer = new StringBuffer(XPATHSTART);
		String nodeName;
		int size = nodeNames.size();
		for (int i=0;i<size;i++){
			nodeName = (String)nodeNames.get(i);
			buffer.append(nodeName);
			if (i!=(size-1)){
				buffer.append(XPATH_SUBNODE);
			}			
		}
		return buffer.toString();		
	}
	

	/**
	 * 
	 * @return Map of the cache. it's reinitialised if been GC'd
	 */	
	private synchronized Map getCache(){
		
		Map cache = (Map) cacheWrapper.get();
		if (cache==null){
			cache = new HashMap();	
		}
		return cache;		
	}
	

	
	private static String[] path={"ContactWithHonda", "TestDrive", "ProductSegment"};	
	
	public static void main(String[] args) throws Exception{
	
		SSDiffNodeLocator locator = new SSDiffNodeLocator("c:\\SSDIF.xsd");
		List nodeNames = Arrays.asList(path);
		System.out.println(locator.getAbsolutePosition(nodeNames));
	}
}

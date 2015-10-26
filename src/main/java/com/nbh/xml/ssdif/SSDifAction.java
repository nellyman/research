package uk.co.honda.hi.service.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import uk.co.honda.framework.struts.action.ErrorHandlingActionSupport;
import uk.co.honda.hi.data.ssdif.dao.SSDIFDAO;
import uk.co.honda.hi.ssdif.SSDiffNodeLocator;
import uk.co.honda.hi.value.ssdif.SSDifKey;

/**
 * @author uinxh
 * 
 * Super Class to hold general helper methods for SSDif XML Creator app. 
 * 
 */
public abstract class SSDifAction extends ErrorHandlingActionSupport {

	protected static Logger logger =
		Logger.getLogger(SSDifAction.class.getName());

	protected static boolean LOG_DOCUMENT=false;
	protected static String SCHEMA_LOCATION=null;
	
	protected SSDiffNodeLocator locator=null;
	protected SSDIFDAO ssdifDAO;


	private static final String PARAM_DELIMITER="/";
	private static final String SEQUENCE_DELIMITER="_";
	

	private static final String SSDIFDAO_STR ="ssdifDAO";
	private static final String DISPLAY_XML_PARAM = "displayXMLInLog";
	private static final String SCHEMA_LOCATION_PARAM = "SSDifSchemaLocation";
	
	protected static final String STARTMSG="Entering execute";
	protected static final String ENDMSG="Exiting execute";
	protected static final String STOREMSG="Database write ok."; 
	protected static final String NOSTOREMSG="Database write not ok. Please investigate.";
	
	protected static final String DOC_TYPE="text/xml";


	private static final String[] SequenceNumberNodes= {"RecordCoding","SequenceNumber"};
	private static final String[] DateOfCapture= {"RecordCoding","DateOfCapture"};


	/**
	 * Adds in sequenceNumber and Date of capture nodes.
	 * @param data SortedSet
	 * @return SortedSet with headers.
	 * @throws IOException
	 */
	private SortedSet addSystemHeader(SortedSet data) throws IOException{
		
		int sequenceId=-1;
		try{
			sequenceId = ssdifDAO.getNextSequenceID();
				
		} catch (Exception e) {
			sequenceId=-1;
			logger.error("Exception generated getting sequence Id, it will be set to "+sequenceId+
						" please rectify. "+e.getMessage());	
			}
		// sequence number...
		int[] order = locator.getPositionOfAllElements(SequenceNumberNodes);
		SSDifKey key = new SSDifKey(order, SequenceNumberNodes, ""+sequenceId);
		
		data.add(key);
		
		// date of capture...
		Date now = Calendar.getInstance().getTime();
		order = locator.getPositionOfAllElements(DateOfCapture);
		key = new SSDifKey(order, DateOfCapture, ""+now);
		
		data.add(key);
		
		return data;
	}


	/**
	 * @param destination String destination name in database.
	 * @return int destinationId.
	 */
	protected int getDestinationID(String destination){
	
		int destinationId=-1;	
		try {
			destinationId =ssdifDAO.getDestinationID(destination);
			logger.info("destination id for "+destination+" is "+destinationId);
		} catch (Exception e) {
			destinationId=-1;
			logger.error("Exception generated getting destination Id for Destination: "+destination+
					" Destination Id will be set to "+destinationId+" please rectify. "+e.getMessage());	
		}
		return destinationId;				
	}
	

	protected int getCampaignID(String campaignName){
		int campaignID=-1;	
		try {
			campaignID =ssdifDAO.getCampaignID(campaignName);
			logger.debug("campaign id for "+campaignName+" is "+campaignID);
		} catch (Exception e) {
			campaignID=-1;
			logger.error("Exception generated getting campaign Id for campaign: "+campaignName+
					" Campaign Id will be set to "+campaignID+" please rectify. "+e.getMessage());
		}
		return campaignID;		
	}

	
	/**
	 * @return Set of SSDifKeys that contain the Data Model of Brochure document. The placement
	 * of the keys in the Set describes the placement in the xml document.
	 */
	protected SortedSet createDataModel(HttpServletRequest request) throws IOException{
		
		SortedSet data = new TreeSet();
		
		// add in system headers..
		data = this.addSystemHeader(data);
		
		Enumeration paramNames= request.getParameterNames();
		String[] paramValue;
		String[] names;
		int[] positions, sequence;
		SSDifKey key;
		String paramName;
		while (paramNames.hasMoreElements()){
			
			paramName = (String)paramNames.nextElement();
			
			if (!"submit".equalsIgnoreCase(paramName)){
				names= this.createList(paramName);
			
				paramValue = request.getParameterValues(paramName);
			
				// splitName if multiple posns are used e.g. name_X.
				sequence = this.getSequences(names);
				
				// tidy up names...
				names = this.removeSequenceIds(names);
			
				// for each set of keys find placement in document.
				// add it to the document at corrent place.
				positions = locator.getPositionOfAllElements(names);
				
				key = new SSDifKey(positions, sequence, names, paramValue[0]);
			
				data.add(key);				
			}
		}		

		return data;
	}

	/**
	 * For each level of names an entry will be added to the sequence array.
	 * this array will define the sequence of multiple elements.
	 * @param names Array of split names e.g. [Person][name_2]
	 * @return int position in sequence, for the names example entered would be [-1][2]
	 */
	private int[] getSequences(String[] names){
		int size = names.length;
		int[] sequence = new int[size];
		String name, sequenceNumber;
		int startMatch, seqNum=-1;
		for (int i=0;i<size;i++){
			 name = names[i];
			startMatch=name.indexOf(SEQUENCE_DELIMITER);
			 if (startMatch!=-1){
				sequenceNumber = name.substring(startMatch+1);
				try{
					seqNum = Integer.parseInt(sequenceNumber);	
				}catch (NumberFormatException ne){
					logger.error("Cannot read Sequence Number at param: '"+name+"' a sequence number should be '"+SEQUENCE_DELIMITER+
									"' plus a decimal e.g. '"+SEQUENCE_DELIMITER+"3'. setting sequence to 20");
					seqNum=20;
				}				
			 }
			 sequence[i]=seqNum;
			seqNum=-1;
		}
		
		return sequence;
	}


	/**
	 * For any names that have '_X' will have them removed.
	 * @param names String[] of the names
	 * @return String[] of names without the sequence identification information.
	 */
	private String[] removeSequenceIds(String[] names){
		int size = names.length;
		String name;
		int startMatch;
		for (int i=0;i<size;i++){
			 name = names[i];
			startMatch=name.indexOf(SEQUENCE_DELIMITER);
			 if (startMatch!=-1){
				names[i] = name.substring(0, startMatch);
			 }
		}
		return names;
	}


	/**
	 * From a hieracial list of parameter names splits them by the delimiter.
	 * @param key String with 
	 * @return String[] split by PARAM_DELIMITER String. 
	 */
	private String[] createList(String key){
		String[] keyStr = key.split(PARAM_DELIMITER);		
		return keyStr;
	}
	
	protected void onInit() {
		super.onInit();
		ssdifDAO = (SSDIFDAO) getWebApplicationContext().getBean(SSDIFDAO_STR);
		LOG_DOCUMENT = (Boolean.valueOf(super.getServletContext().getInitParameter(DISPLAY_XML_PARAM))).booleanValue();
		logger.info("context param "+DISPLAY_XML_PARAM+" set to: "+LOG_DOCUMENT);

		SCHEMA_LOCATION = super.getServletContext().getInitParameter(SCHEMA_LOCATION_PARAM);
		logger.info("context param "+SCHEMA_LOCATION_PARAM+" set to: "+SCHEMA_LOCATION);

		try{
			locator = SSDiffNodeLocator.getInstance(SCHEMA_LOCATION);	
		}catch(IOException ioe){
			logger.error("Error opening the SSDif Schema at location: "+SCHEMA_LOCATION+
					" specified by context param: "+SCHEMA_LOCATION_PARAM+" Error message: "+ioe.getMessage());
		}
	}

}

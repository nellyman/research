package uk.co.honda.hi.service.action;

import java.io.PrintWriter;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import uk.co.honda.hi.ssdif.SSDifDocumentCreator;
import uk.co.honda.hi.value.ssdif.SSDifBean;


/**
 * @author uinxh
 * Action to receive a Brochure Request form and create an XML document from it which is then
 * saved into a Database.
 * 
 * The XML will be based upon the format defined in the SSDif schema.
 * 
 * Parameters are expected to follow the SSDif Trees format-
 * e.g.
 * 
 * <input type="text" name="Person/Title">
 * 
 * maps to <record><Person><Title>value
 * 
 * For multiple nodes then a sequence id is required-
 * 
 *  <input type="text" name="CommPrefs/PrefSuppType">
 *  <input type="text" name="CommPrefs_2/PrefSuppType">
 * 
 * Would create 2 CommPrefs nodes with their PrefSuppType nodes underneath-
 *
 *	<CommPrefs>
 *		<PrefSuppType>Pref1</PrefSuppType>
 *	</CommPrefs>
 *	<CommPrefs>
 *		<PrefSuppType>PrefType2</PrefSuppType>
 *	</CommPrefs>
 *
 *  If the sequence number is not understood then it will be the first child node.
 * 
 *  If the node name is not found in the schema then the document would be created 
 *  with the node inserted at a best match location. The document will probably fail validation.
 * 
 */
public class BrochureRequestReceiveAction extends SSDifAction {

	private static Logger logger =
		Logger.getLogger(BrochureRequestReceiveAction.class.getName());

	private static final String DESTINATION="BROCHURE";
	private static final boolean USE_XML_HEADERS= false;
	
	private static int DESTINATION_ID=-2;


	public void execute(ActionErrors errors, ActionMapping mapping,	ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info(STARTMSG);	
		long start = System.currentTimeMillis();
		
		PrintWriter out = response.getWriter();
		
		SortedSet data = null;
		
		data = this.createDataModel(request);
		

		//not going to put 'xml' tag at top of documents as they are going to be batched up
		String document = SSDifDocumentCreator.createXMLStr(USE_XML_HEADERS, data);
		
		if (LOG_DOCUMENT){
			logger.info(document);	
		}
		
		// get the Destination ID..
		if (DESTINATION_ID==-2){
			DESTINATION_ID = super.getDestinationID(DESTINATION);
		}
		
		
		//TODO: whats this ?!!
		int campId = ssdifDAO.getCampaignID("CAMPAIGN #1");
				
		// now need to store into database..
		SSDifBean bean = new SSDifBean(DESTINATION_ID, campId, document);
		
		
		boolean isStored = ssdifDAO.insert(bean);
		
		if(isStored){
			logger.debug(STOREMSG);
		}else{
			logger.error(NOSTOREMSG);
		}
		
		response.setContentType(DOC_TYPE);			
		out.println(document);
		
		long end = System.currentTimeMillis();
		
		logger.info(ENDMSG+". in "+(end-start)+" mS");
	}
}

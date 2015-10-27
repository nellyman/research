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
 *
 * Tester app...
 */
public class CallBackAction extends SSDifAction {

	private static Logger logger =
		Logger.getLogger(CallBackAction.class.getName());

	private static final String DESTINATION="CALLBACK";
	private static final boolean USE_XML_HEADERS= true;
	
	private static int DESTINATION_ID=-2;


	public void execute(ActionErrors errors, ActionMapping mapping,	ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info(STARTMSG);	
		
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
		int campId = 1;
				
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
		
		logger.info(ENDMSG);
	}

}

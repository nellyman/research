package uk.co.honda.hi.ssdif;

import java.util.Iterator;
import java.util.SortedSet;

import uk.co.honda.hi.value.ssdif.SSDifKey;

/**
 * @author uinxh
 *
 * Currently this class uses String manipulation to create an XML document.
 * 
 * 
 */
public class SSDifDocumentCreator {

	private static final String XML_DECLARATION =
		"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";

	private static final String START_ELEMENT="<record>";
	private static final String END_ELEMENT="</record>";
	private static final String OPEN_DETAIL="<";
	private static final String CLOSE_DETAIL="</";
	private static final String CLOSE_TAG=">";


	private SSDifDocumentCreator(){
		
	}

	/**
	 * Creates XML document by the placement of SSDifKey objects in a Sorted Set.
	 * @param xmlDeclarationAtTop boolean, if the xml documents are not going to be batched up then the XML declaration
	 * is probably required.
	 * @param data SortedSet of SSDifKey objects that describe the nodes in the document.
	 * @return String representation of the document.
	 */
	public static String createXMLStr(boolean xmlDeclarationAtTop, SortedSet data) {

		Iterator inElements = data.iterator();
		SSDifKey key = null, lastKey = null;
		int level = 0;
		String levelName;

		StringBuffer doc;

		if (xmlDeclarationAtTop) {
			doc = new StringBuffer(XML_DECLARATION);
		} else {
			doc = new StringBuffer();
		}

		doc.append(START_ELEMENT);

		while (inElements.hasNext()) {
			// get next Element...
			key = (SSDifKey) inElements.next();

			// initialising...

			if (lastKey == null) {
				lastKey = key;
			}

			// find the point at which this key and last one differ...			
			int differentLevel = lastKey.findDifferentOrder(key);


			// if the keys are the same then nothing to close down.
			if (differentLevel!=SSDifKey.SAMEORDERING){
				
				// else while the currentLevel (level) is larger than the difference..				
				while (level>differentLevel) {				
					level--; 
					// close it down...
					levelName = lastKey.getNodeNameAt(level);
					doc.append(CLOSE_DETAIL + levelName + CLOSE_TAG);
				}				
			}



			// Now see what to open, open nodes until there are no more to open...
			while (key.getNodeNameAt(level) != null) {
				levelName = key.getNodeNameAt(level);
				doc.append(OPEN_DETAIL + levelName + CLOSE_TAG);
				level++;
			}
			
			// append the nodes value.. 
			doc.append(key.getValue());
			lastKey = key;
		}


		// finished loop.
		// Last element will be open, need to close it down...

		while (lastKey.getOrderAt(--level) != -1) {
			// close it down...
			levelName = lastKey.getNodeNameAt(level);
			doc.append(CLOSE_DETAIL + levelName + CLOSE_TAG);
		}


		// document should be done.
		doc.append(END_ELEMENT);

		return doc.toString();
	}

}

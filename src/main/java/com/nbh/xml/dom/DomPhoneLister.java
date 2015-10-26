
import java.io.*;
import javax.xml.parsers.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class DomPhoneLister {

  public static void main(String args[]) {


    try {

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder saxParser = factory.newDOMParser();

            factory.setValidating(false);
            factory.setNamespaceAware(false);
            DocumentBuilder builder  = factory.newDocumentBuilder();

      DefaultHandler handler = new DefaultHandler() {
        boolean name = false;
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes)
            throws SAXException {
          if (qName.equalsIgnoreCase("NAME")) {
            name = true;
          }
        }
        public void characters(char ch[], int start, int length)
            throws SAXException {
          if (name) {
            System.out.println("Name: "
                                + new String(ch, start, length));
            name = false;
          }
        }
      };

      saxParser.parse("c:\\mystuff\\java\\projectSrc\\phonebook.xml", handler);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
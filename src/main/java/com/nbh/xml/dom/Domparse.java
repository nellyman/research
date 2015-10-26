package com.nbh.xml.dom;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Domparse {

    public Domparse(String xmlFile) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setValidating(false);
            factory.setNamespaceAware(false);
            DocumentBuilder builder  = factory.newDocumentBuilder();

            Document document = builder.parse( xmlFile );
            printNode(document,"");

        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

    private void printNode(Node node, String offSet){
          switch (node.getNodeType()){

              case Node.DOCUMENT_NODE:
                  Document doc=(Document)node;
                  printNode(doc.getDocumentElement()," ");

              case Node.ELEMENT_NODE:
                  System.out.println(offSet+node.getNodeName());
                  NamedNodeMap attrs = node.getAttributes();
                  if (attrs != null) {
                    for (int i = 0; i < attrs.getLength(); i++) {
                      Node aName = attrs.item(i); // Attr name
                      System.out.println(offSet+aName.getNodeName()+"=\""+aName.getNodeValue()+"\"");
                    }
                        // recurse child nodes..
                        NodeList children = node.getChildNodes();
                        if (children!=null){
                            for (int i=0; i<children.getLength();i++){
                                printNode(children.item(i), "  ");
                            }
                        }
                  }

                  case Node.TEXT_NODE:
                    if (node.getNodeValue()!=null){
                        System.out.println(offSet+" "+node.getNodeValue().trim());
                    }
          }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("location of xml file to parse :");
        String xmlFile = com.nbh.common.LineReader.getLineFromSystemIn();
        Domparse saxer = new Domparse(xmlFile);
    }
}

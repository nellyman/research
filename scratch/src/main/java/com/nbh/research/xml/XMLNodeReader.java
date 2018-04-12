package com.nbh.research.xml;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Jul 19, 2004
 * Time: 11:24:56 AM
 * To change this template use Options | File Templates.
 */
public class XMLNodeReader {

    private static String xmlMsg=
            "<?xml version='1.0'?>" +
            "<gulliverStatus>" +
            "<id>10101</id>" +
            "<status>GOOD</status>" +
            "</gulliverStatus>";

    public static void main(String[] args){
        XMLNodeReader reader = new XMLNodeReader();
        String op = reader.getXMLElement(xmlMsg.getBytes(),"status");
        System.out.println(op);
    }

    private String getXMLElement(byte[] msg, String node){
        String value="";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //factory.setNamespaceAware(true);
        //factory.setValidating(true);

        DocumentBuilder builder = null;
        org.w3c.dom.Document document=null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new ByteArrayInputStream(msg)));

            value =  this.getText(this.findSubNode(node, document.getFirstChild()));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public Node findSubNode(String name, Node node) {
      if (node.getNodeType() != Node.ELEMENT_NODE) {
        System.err.println(
            "Error: Search node not of element type");
        System.exit(22);
      }

      if (! node.hasChildNodes()) return null;

      NodeList list = node.getChildNodes();
      for (int i=0; i < list.getLength(); i++) {
        Node subnode = list.item(i);
        if (subnode.getNodeType() == Node.ELEMENT_NODE) {
          if (subnode.getNodeName().equals(name)) return subnode;
        }
      }
      return null;
    }


    public String getText(Node node) {
      String result = "";

      NodeList list = node.getChildNodes();
      for (int i=0; i < list.getLength(); i++) {
        Node subnode = list.item(i);
        if (subnode.getNodeType() == Node.TEXT_NODE) {
          result = subnode.getNodeValue();
        }
      }
      return result;
    }


}

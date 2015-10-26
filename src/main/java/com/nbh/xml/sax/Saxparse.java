package com.nbh.xml.sax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.UnsupportedEncodingException;
import java.io.File;


public class Saxparse {

    public Saxparse(String xmlFileName) throws UnsupportedEncodingException {

        // Use an instance of ourselves as the SAX event handler
        DefaultHandler handler = new Echo();
        // Use the default (non-validating) parser
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
          // Parse the input
          SAXParser saxParser = factory.newSAXParser();
          saxParser.parse( new File(xmlFileName), handler );
        } catch (Throwable t) {
          t.printStackTrace();
        }

    }


    public static void main(String[] args) throws Exception {
        System.out.println("location of xml file to parse :");
        String xmlFile = com.nbh.common.LineReader.getLineFromSystemIn();
        Saxparse saxer = new Saxparse(xmlFile);
    }
}


 class Echo extends DefaultHandler{

     StringBuffer textBuffer;

     public void startElement(String namespaceURI,
             String sName, // simple name
             String qName, // qualified name
             Attributes attrs)
     throws SAXException
     {
       echoText();
       System.out.println("< "+qName+" >");
       if (attrs != null) {
         for (int i = 0; i < attrs.getLength(); i++) {
           String aName = attrs.getLocalName(i); // Attr name
           if ("".equals(aName)) aName = attrs.getQName(i);
           System.out.println(aName+"=\""+attrs.getValue(i)+"\"");
         }
       }
     }

     public void endElement(String namespaceURI,
             String sName, // simple name
             String qName  // qualified name
             )
     throws SAXException
     {
       echoText();
       String eName = sName; // element name
       if ("".equals(eName)) eName = qName; // not namespace-aware
                  System.out.println("</"+eName+">");
     }


     //  characters can come from the document at any point, either all, or some.
     // pass them into a buffer and use (mainly) endElement to read them.
     public void characters(char buf[], int offset, int len)
     throws SAXException
     {
       String s = new String(buf, offset, len);
       if (textBuffer == null) {
         textBuffer = new StringBuffer(s);
       } else {
         textBuffer.append(s);
       }
     }

     private void echoText()
     throws SAXException
     {
       if (textBuffer == null) return;
       String s = ""+textBuffer;
       System.out.println(s);
       textBuffer = null;
     }
}

package com.nbh.research.xml.dom;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.IOException;

/**
 * If no transform file entered then
 * Performs an 'identity change' the document is not changed, only pumped out onto
 * the screen.
 *
 * else performs the transform.
 *
 */
public class DomTransform {

     public DomTransform(String xmlFileName, String transformFileName){
         File f = new File(xmlFileName);
         try {

            // create the DOM document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(f);

             // Use a Transformer for output
            TransformerFactory tFactory =TransformerFactory.newInstance();
             Transformer transformer=null;

             if ("".equals(transformFileName)){
                transformer = tFactory.newTransformer();
             }
             else{
                 StreamSource stylesource =  new StreamSource(new File(transformFileName));
                 transformer = tFactory.newTransformer(stylesource);
             }

             StreamResult result = new StreamResult(System.out);
            DOMSource source = new DOMSource(document);
            transformer.transform(source, result);

         } catch (SAXException e) {
             e.printStackTrace();  //To change body of catch statement use Options | File Templates.
         } catch (IOException e) {
             e.printStackTrace();  //To change body of catch statement use Options | File Templates.
         } catch (TransformerConfigurationException e) {
             e.printStackTrace();  //To change body of catch statement use Options | File Templates.
         } catch (TransformerException e) {
             e.printStackTrace();  //To change body of catch statement use Options | File Templates.
         } catch (ParserConfigurationException e) {
             e.printStackTrace();  //To change body of catch statement use Options | File Templates.
         }
     }


    public static void main(String[] args) throws Exception {
        System.out.println("location of xml file to transform :");
        String xmlFile = com.nbh.common.LineReader.getLineFromSystemIn();
        System.out.println("location of stylesheet : ");
        String styleSheet=com.nbh.common.LineReader.getLineFromSystemIn();
        DomTransform saxer = new DomTransform(xmlFile, styleSheet);

    }
}

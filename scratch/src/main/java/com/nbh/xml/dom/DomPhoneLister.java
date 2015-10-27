package com.nbh.xml.dom;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DomPhoneLister {

    public static void main(final String args[]) {


        try {

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder saxParser = factory.newDOMParser();

            factory.setValidating(false);
            factory.setNamespaceAware(false);
            final DocumentBuilder builder  = factory.newDocumentBuilder();

            final DefaultHandler handler = new DefaultHandler() {
                boolean name = false;
                public void startElement(final String uri, final String localName,
                        final String qName, final Attributes attributes)
                                throws SAXException {
                    if (qName.equalsIgnoreCase("NAME")) {
                        this.name = true;
                    }
                }
                public void characters(final char ch[], final int start, final int length)
                        throws SAXException {
                    if (this.name) {
                        System.out.println("Name: "
                                + new String(ch, start, length));
                        this.name = false;
                    }
                }
            };

            saxParser.parse("c:\\mystuff\\java\\projectSrc\\phonebook.xml", handler);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
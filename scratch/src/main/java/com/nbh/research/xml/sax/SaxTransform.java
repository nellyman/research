package com.nbh.research.xml.sax;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTransform {

    public static void main(final String args[]) {


        try {

            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser saxParser = factory.newSAXParser();

            final DefaultHandler handler = new DefaultHandler() {
                boolean name = false;
                @Override
                public void startElement(final String uri, final String localName,
                        final String qName, final Attributes attributes)
                                throws SAXException {
                    if (qName.equalsIgnoreCase("NAME")) {
                        this.name = true;
                    }
                }
                @Override
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
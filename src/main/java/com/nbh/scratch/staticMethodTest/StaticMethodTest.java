package com.nbh.scratch.staticMethodTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to test whether using a localMethod is any quicker / slower than a static one.
 * Short answer is that there is no difference !!
 * @author uinxh
 *
 */
public class StaticMethodTest{

	public void performTest(){
		
	}
	
	public static String constructXmlForHttpSendStatically(String message) {

		StringBuffer toReturn = new StringBuffer("<FeedbackRequest xmlns='urn:Honda:FeedbackRequest:v1.0'>");
		toReturn.append(" <Sender>");
		toReturn.append("  <Title>Mr</Title>");
		toReturn.append("  <Forename>John</Forename>");
		toReturn.append("  <Lastname>Smith</Lastname>");
		toReturn.append("  <Email>John@mail.com</Email>");
		toReturn.append("  <Postcode>RG402JE</Postcode>");
		toReturn.append(" </Sender>");
		toReturn.append(" <Subject>This is subject</Subject>");
		toReturn.append(" <Query>");
		toReturn.append("  <DataProtection>"+message+"</DataProtection>");
		toReturn.append("  <Question>TheQuestion</Question>");
		toReturn.append(" </Query>");
		toReturn.append("</FeedbackRequest> ");
		
		return (toReturn.toString());
	}	
	
	
	public String constructXmlForHttpSend(String message) {

		StringBuffer toReturn = new StringBuffer("<FeedbackRequest xmlns='urn:Honda:FeedbackRequest:v1.0'>");
		toReturn.append(" <Sender>");
		toReturn.append("  <Title>Mr</Title>");
		toReturn.append("  <Forename>John</Forename>");
		toReturn.append("  <Lastname>Smith</Lastname>");
		toReturn.append("  <Email>John@mail.com</Email>");
		toReturn.append("  <Postcode>RG402JE</Postcode>");
		toReturn.append(" </Sender>");
		toReturn.append(" <Subject>This is subject</Subject>");
		toReturn.append(" <Query>");
		toReturn.append("  <DataProtection>"+message+"</DataProtection>");
		toReturn.append("  <Question>TheQuestion</Question>");
		toReturn.append(" </Query>");
		toReturn.append("</FeedbackRequest> ");
		
		return (toReturn.toString());
	}
	
	

	public static void main(String[] args) {
		
		// Create the test candidates...
		List<StaticMethodTest> testCandidates = new ArrayList<StaticMethodTest>(10000000);
		
		for (StaticMethodTest i: testCandidates){
			i = new StaticMethodTest();
		}
		
		// test with local methods...
		int t=0;
		long startLocal = System.currentTimeMillis();
		for (StaticMethodTest i: testCandidates){
			i.constructXmlForHttpSend("message "+(t++));
		}
		long endLocal = System.currentTimeMillis();
		
		// test static method
		t=0;
		long startStatic = System.currentTimeMillis();
		for (StaticMethodTest i: testCandidates){
			i.constructXmlForHttpSendStatically("Message number "+i);	
		}
		long endStatic = System.currentTimeMillis();
		
		System.out.println("For LocalMethod: "+(endLocal-startLocal)+" mS");
		System.out.println("For staticMethod: "+(endStatic-startStatic)+" mS");
	}




}

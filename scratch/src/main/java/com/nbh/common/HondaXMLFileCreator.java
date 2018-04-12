package com.nbh.common;
import java.io.*;

public class HondaXMLFileCreator{

	//<registrations>
	//<registration busdomain="011" busunit="17001" custref="0784" />
	//</registrations>

	private static final String OPEN="<registrations>";
	private static final String CLOSE="</registrations>";
	private static final String LINE_LEFT = "<registration busdomain=\"";
	private static final String LINE_MIDDLE = "\" busunit=\"";
	private static final String LINE_RIGHT = "\" custref=\"";
	private static final String LINE_END = "\" /> ";
	private static int count=6000;

	public static void createFile(String busdomain, String busUnit, String custRef){

		FileWriter writer=null;
		try{
			writer=new FileWriter("registration"+(count++)+".txt");
			writer.write(OPEN+"\r\n");
			writer.write(LINE_LEFT+busdomain+LINE_MIDDLE+busUnit+LINE_RIGHT+custRef+LINE_END+"\r\n");
			writer.write(CLOSE);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());

		}finally{
			try{
				writer.close();
			}catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}
		}
	}



	public static void main(String[] args) throws Exception{

		String busD="",busN="",custRef="", input="";
		do{
			System.out.println("BusinessDomain, or enter to accept "+busD);
			input = LineReader.getLineFromSystemIn();
			if (!"".equals(input)){
				busD=input;
			}
			System.out.println("BusinessUnit, or enter to accept "+busN);
			input = LineReader.getLineFromSystemIn();
			if (!"".equals(input)){
				busN=input;
			}
			System.out.println("Customer Reference, '' to stop");
			input = LineReader.getLineFromSystemIn();
			if ("".equals(input)){
				break;
			}
			custRef=input;

			HondaXMLFileCreator.createFile(busD, busN, custRef);
			System.out.println("Creating : "+busD+" "+busN+" "+custRef);

		}while (!"".equals(custRef));





	}




}
package com.nbh.util;
import java.io.*;


/**
*
* From a Log file will pull all lines that have the matching text and
* put them into a report file.
*
**/
public class logAnalyser{

	private static String root="C:\\Documents and Settings\\uinxh\\My Documents\\work\\deadlocks\\";
	private static String logFile=root+"smsPBY.log";
	private static String match="006-HUK-UISXA";
	private static String outputFileName=root+"pbySMS.txt";


	public static void main(String[] args) throws Exception{

/*		if (args.length<3){
			System.out.println("logAnalyser <logFile> <String to match> <outputFile>");
			System.exit(0);
		}

		String logFile=args[0];
		String match=args[1];
		String outputFileName=args[2];
*/

		System.out.println("working...");

		BufferedReader in= new BufferedReader(new FileReader(logFile));
		FileWriter f = new FileWriter(outputFileName);
		String line = in.readLine();

		while(line!=null){
			if (line.indexOf(match)!=-1){
				f.write(line+"\r\n",0, line.length()+2);
			}
			line = in.readLine();
		}

		f.close();
		System.out.println("done. Results at- "+outputFileName);
	}





}
package com.nbh.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Jun 1, 2004
 * Time: 10:38:13 AM
 * To change this template use Options | File Templates.
 */
public class LineReader {

     private static BufferedReader reader;

    public static void  init(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

	/**
	* returns whatever is entered in from the System in.
	* @return String of entered line
	*/
    public static String getLineFromSystemIn() throws IOException{
        if (reader==null)
                init();
        return (reader.readLine());
    }

    /**
	 * Returns the entered text from System in as an integer. IOException
	 * thrown if integer isn't entered.
	 * @return Integer
	 * @throws IOException if a non-Integer is entered.
	 */
    public static Integer getIntegerFromSystemIn() throws IOException{

		Integer number=new Integer(0);
		String line = getLineFromSystemIn();
		try{
			number = new Integer(line);

		}catch(NumberFormatException ne){
			throw new IOException("invlaid number. Entered: '"+line+"'");
		}
		return number;
	}

    public static void main(String[] args) throws Exception{
        String in = LineReader.getLineFromSystemIn();
    }
}

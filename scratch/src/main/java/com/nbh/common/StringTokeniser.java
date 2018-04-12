
package com.nbh.common;

import java.util.StringTokenizer;

/**
 *
 * A StringTokenizer will ignore tokens if theres nothing in them
 * Which can cause problems when a known amount of tokens need to be 
 * processed.
 * 
 * This class will return null tokens as blanks eg -
 * 
 * 
 * first,,,forth
 * 
 * StringTokenizer =
 * first + forth
 * 
 * this
 * 
 * first + space + space + forth
 * 
 * @author neal hardwick
 * 
 */
 
 public class StringTokeniser{

	private String line;
	private int startToken=0;
	private int endToken=0;
	private String delim=",";

	/**
	 * 
	 * @param line The line to process
	 */
	public StringTokeniser(String line){
		this.line=line;
	}
	
	
	/**
	 * 
	 * @param line The line to process
	 * @param delimiter The delimiter to use.
	 */
	public StringTokeniser(String line, String delimiter){
		this.line=line;
		delim = delimiter;
	}


	/**
	 * reset Resets the counts -starts tokens at the start again.
	 *
	 */
	public void reset(){
		startToken=0;
		endToken=0;		
	}
	
	
	/**
	 * @param line The new line to process.
	 */
	public void setLine(String line){
		this.line=line;
		reset();
	}


	/**
	 * 
	 * @return String the nextToken. Will return a null if no more
	 * tokens exist.
	 */
	public String nextToken(){

		String token="";
		
		// first element
		if (startToken==0){
			endToken=line.indexOf(delim);
			if (endToken!=-1){
				token = line.substring(startToken,endToken);
				startToken=endToken;
				return token;
			}else{
				// no delimiters found -return whole line
				startToken=-1;
				return line;			
			}
		}
			
		// load up remaing elements..
		startToken = line.indexOf(delim, startToken);
		if (startToken!=-1){
			endToken= line.indexOf(delim, (++startToken));	
			if (endToken!=-1){					
				token=line.substring(startToken, endToken);
			}else{
				token=line.substring(startToken, line.length());
			}
		}else{
			token=null;
		}

		return token;
	}



	public boolean hasNextToken() {
		// work out next startToken...
		int test= line.indexOf(delim, startToken);
		if (test==-1){
			return false;
		}else{
			return true;
		}		
	}


	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		// TODO Auto-generated method stub
		
	}



	/**
	* Test, showing the different processing btn this and StringTokenizer 
	* @param args String command line arguments.
	*/
	public static void main(String[] args) {
		
		String line="mary,had,a,little,lamb,,,her,coat,was,white,as,snow,,it,was";
		//String line="";
		StringTokeniser st = new StringTokeniser(line);
		String token="";
				
		while(st.hasNextToken()){
			token=st.nextToken();
			if (token!=null){
				System.out.println(token);	
			}			
		}
		
		System.out.println("The java.util's StringTokenizer..");
		
		StringTokenizer stt = new StringTokenizer(line,",",true);
		while (stt.hasMoreTokens()){
			System.out.println(stt.nextToken());
		}
		
		System.out.println("Again this...");
		st.reset();
		token="";
		while(st.hasNextToken()){
			token=st.nextToken();
			if (token!=null){
				System.out.println(token);	
			}			
		}

		
		
		
	}



}

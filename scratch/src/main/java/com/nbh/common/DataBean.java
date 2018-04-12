/*
 * Created on 17-Dec-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.nbh.common;

import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DataBean {

	
		private Map data;
		private String errorMessage="";
	
		/**
		 * constructor
		 */
		public DataBean(){
			data=new TreeMap(); // definate ordering
		}

		/**
		* 
		* @param key String object to reference data by
		* @param data The data stored with this key
		*/
		public void addData(String key, String data){
			this.data.put(key, data);
		}	
		
		
		/**
		 * determines if theres more keys to look at.
		 * @return boolean hasMore
		 */
		public boolean hasMoreKeys(){
			checkKeys();
			return (keys.hasNext());
		}
		
		private Iterator keys;
		
		public Object nextKey(){
			checkKeys();
			if (keys.hasNext()){
				return (keys.next());	
			}
			return null;
		}
		
	private void checkKeys(){
		if (keys==null){
			Set keySet = data.keySet();
			keys=keySet.iterator();
		}				
	}
		
		/**
		 * 
		 * @param key String object to get
		 * @return String data value
		 */
		public String getData(String key){
			if (key==null){
				return "";
			}
			String dataToReturn =(String)data.get(key.trim());
			if (dataToReturn==null){
				dataToReturn="";
			}
			return dataToReturn;
		}
		/**
		 * returns a String representation of the class 
		 * @return String
		 */
		public String toString(){
			StringBuffer buffer = new StringBuffer();
			if (data.size()==0){
				buffer.append("no elements in array.");
			}else{			
				Set keys = data.keySet();
				Iterator ks = keys.iterator();
				String key ="";
				while (ks.hasNext()){
					key = (String)ks.next(); 				
					Object o = data.get(ks);
					if (o instanceof String){
						buffer.append("key : "+ks+"/t   value "+(String)o);	
					}				
				}
			}
			return buffer.toString();		
		}
	
		/**
		 * See if the customer object was created successfully.
		 * @return String, either empty or with an error message.
		 */
		public String getErrorMessage(){
			return errorMessage;
		}
	
		/**
		 * Error message on processing any part of the customer object.
		 * @param message String error message.
		 */
		public void addErrorMessage(String message){
			if (message!=null){
				errorMessage=errorMessage+message;	
			}		
		}




	public static void main(String[] args) {
	}
	
	
}

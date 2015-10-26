package com.nbh.ldap;

import netscape.ldap.*;
import java.util.*;

public class LDAPConnectionPool {

	private static Vector connections=new Vector();
	static boolean verbose=true;

	static public void setVerbose(boolean msgs){
		verbose=msgs;
	}

	static public LDAPConnection getConnection(){

		LDAPConnection connectionToGive = null;
		if (connections.size()==0){	// create a new connection
			connectionToGive = establishNewConnection();
		}
		else{		// else use an existing one...
			try{
				if (verbose)
					System.out.println("Pool size : "+connections.size());
				connectionToGive = (LDAPConnection)connections.firstElement();
				connections.removeElementAt(0);
				if (verbose)
					System.out.println("reusing connection. "+connections.size()+" connections left");
			}
			catch(NoSuchElementException zeroErr){

			}
		}
		return connectionToGive;
	}

	static public void putConnection(LDAPConnection returningConnection){
		if (verbose)
			System.out.println("connection returned to pool");
		connections.addElement(returningConnection);
	}

	static public void closeAllConnections(){
		Enumeration allConnections = connections.elements();
		while(allConnections.hasMoreElements()){
			closeConnection((LDAPConnection)allConnections.nextElement());
		}
	}



	static private LDAPConnection establishNewConnection(){

		LDAPConnection lc=null;
		try{
		 	lc =new LDAPConnection();
		 	lc.connect(LDAPParameters.LDAPURI  ,  LDAPParameters.port);
		 	if (verbose)
		 		System.out.println("connected upto the LDAP server");
           	lc.authenticate(LDAPParameters.LDAP_CONNECTION_NAME , LDAPParameters.LDAP_CONNECTION_PASSWORD);
           	if (verbose)
           		System.out.println("authenticated ok on the LDAP server.");
         	}
          catch (LDAPException le){
          	 	System.err.println(" Problem getting connection to LDAP server "+le.getMessage());
          	 	lc=null;
          }
          catch (Exception e){
          		System.err.println(" Unknown problem getting connection to LDAP server "+e.toString());
				lc=null;
          }
		return lc;
	}

	static private void closeConnection(LDAPConnection ld ){
		if (verbose)
			System.out.println("Closing connection");
	     if (ld==null || !ld.isConnected())
     	       return;
        	try{
			ld.disconnect();
        	}
        catch(LDAPException ldape){
			System.err.println(" Problem closing connection to LDAP server "+ldape.getMessage());
        }
	}

}


class LDAPParameters{


    // public String LDAPURI="192.168.1.22";	/** The LDAP server url (LDAP://) */
   public static final String LDAPURI="192.168.1.23";	/** The LDAP server url (LDAP://) */
   public static final String LDAP_CONNECTION_NAME="CN=Directory Manager";		/** The username parameter for the LDAP */
   //public String LDAP_CONNECTION_NAME="admin";
   public  static  final String LDAP_CONNECTION_PASSWORD="12345678";		/** The password required to setup. */
    //public  String LDAP_CONNECTION_PASSWORD="stanford";		/** The password required to setup. */
   public  static final int port=389;					/** the port which the server is working on. */
   public  static final String O="dc=siroe, dc=com"; /** the organisation include the 'o=' (or whatever) **/
   public  static final String OU="People";
}
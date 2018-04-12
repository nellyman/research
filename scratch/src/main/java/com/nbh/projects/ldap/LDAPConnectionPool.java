package com.nbh.projects.ldap;


import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

public class LDAPConnectionPool {

    private static Vector connections=new Vector();
    static boolean verbose=true;

    static public void setVerbose(final boolean msgs){
        LDAPConnectionPool.verbose=msgs;
    }

    static public LDAPConnection getConnection(){

        LDAPConnection connectionToGive = null;
        if (LDAPConnectionPool.connections.size()==0){	// create a new connection
            connectionToGive = LDAPConnectionPool.establishNewConnection();
        }
        else{		// else use an existing one...
            try{
                if (LDAPConnectionPool.verbose) {
                    System.out.println("Pool size : "+LDAPConnectionPool.connections.size());
                }
                connectionToGive = (LDAPConnection)LDAPConnectionPool.connections.firstElement();
                LDAPConnectionPool.connections.removeElementAt(0);
                if (LDAPConnectionPool.verbose) {
                    System.out.println("reusing connection. "+LDAPConnectionPool.connections.size()+" connections left");
                }
            }
            catch(final NoSuchElementException zeroErr){

            }
        }
        return connectionToGive;
    }

    static public void putConnection(final LDAPConnection returningConnection){
        if (LDAPConnectionPool.verbose) {
            System.out.println("connection returned to pool");
        }
        LDAPConnectionPool.connections.addElement(returningConnection);
    }

    static public void closeAllConnections(){
        final Enumeration allConnections = LDAPConnectionPool.connections.elements();
        while(allConnections.hasMoreElements()){
            LDAPConnectionPool.closeConnection((LDAPConnection)allConnections.nextElement());
        }
    }



    static private LDAPConnection establishNewConnection(){

        LDAPConnection lc=null;
        try{
            lc =new LDAPConnection();
            lc.connect(LDAPParameters.LDAPURI  ,  LDAPParameters.port);
            if (LDAPConnectionPool.verbose) {
                System.out.println("connected upto the LDAP server");
            }
            lc.authenticate(LDAPParameters.LDAP_CONNECTION_NAME , LDAPParameters.LDAP_CONNECTION_PASSWORD);
            if (LDAPConnectionPool.verbose) {
                System.out.println("authenticated ok on the LDAP server.");
            }
        }
        catch (final LDAPException le){
            System.err.println(" Problem getting connection to LDAP server "+le.getMessage());
            lc=null;
        }
        catch (final Exception e){
            System.err.println(" Unknown problem getting connection to LDAP server "+e.toString());
            lc=null;
        }
        return lc;
    }

    static private void closeConnection(final LDAPConnection ld ){
        if (LDAPConnectionPool.verbose) {
            System.out.println("Closing connection");
        }
        if (ld==null || !ld.isConnected()) {
            return;
        }
        try{
            ld.disconnect();
        }
        catch(final LDAPException ldape){
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
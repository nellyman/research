/*
 * MemberList.java
 *
 * Created on 30 January 2002, 10:37
 */

package com.nbh.LDAP.Members;

/**
 *
 * @author  Administrator
 * @version
 */

import netscape.ldap.*;
import java.util.*;

public class MemberList {
    
    private String LDAP="localhost";
    private int port=389;
    private Vector members,attributes;
    
    /** Creates new MemberList */
    public MemberList() {
        members=new Vector();
        attributes=new Vector();
    }
    
    // void setServer(String name)
    // sets the LDAP server's name.
    // The name is stored in a class variable.
    public void setServer(String name){
        LDAP=name;
    }
    // void setPort(int Portnumber)
    // sets the LDAP servers port.
    // The int is stored in a class variable.
    public void setPort(int newPort){
        port=newPort;
    }
    
    // void getMembers()
    // Gets the members from the LDAP server.
    // Stores the information in Vector members.
    public void getMembers() {
        members.clear();
        LDAPConnection ld = new LDAPConnection();
        LDAPSearchResults results = null;
        try{
            ld.connect(LDAP,port);
            
            String MY_FILTER = "sn=*";
            String MY_SEARCHBASE = "o=Airius.com";
            
            //            LDAPSearchResults res = ld.search( MY_SEARCHBASE,    LDAPConnection.SCOPE_SUB,   MY_FILTER, null, false );
            results = ld.search("o=Airius.com",LDAPv2.SCOPE_SUB,  MY_FILTER ,null, false);
        }
        catch(LDAPException ex){
            System.err.println("Error getting member list "+ex.getMessage());
        }
        
         /* Sort the results first by last name, then by first. */
        String[]  sortAttrs = {"sn", "cn"};
        boolean[] ascending = {true, true};
        results.sort( new LDAPCompareAttrNames(sortAttrs, ascending) );
        
        
         /* Iterate through and print out the results. */
        while ( results.hasMoreElements() ) {
            /* Get the next directory entry. */
            LDAPEntry findEntry = null;
            try {
                findEntry = results.next();
                
            /* If the next result is a search reference,
               print the LDAP URLs in that reference. */
            } catch ( LDAPReferralException e ) {
                System.out.println( "Search references: " );
                LDAPUrl refUrls[] = e.getURLs();
                for ( int i=0; i < refUrls.length; i++ ) {
                    System.out.println( "\t" + refUrls[i].getUrl());
                }
                continue;
            } catch ( LDAPException e ) {
                System.out.println( "Error: " + e.toString() );
                continue;
            }
            
                  /* Print the DN of the entry. */
            System.out.println(findEntry.getDN() );
            String[] attrs={"cn","sn","mail"};
            String[] valsBack = this.getValues(findEntry, attrs);
            for (int i=0;i<valsBack.length;i++){
                System.out.println("\t"+valsBack[i]);
            }
        } // end while
    } // end getMembers
    
    public static void main(String[] args){
        MemberList l=new MemberList();
        l.getMembers();
    }
    
    
    /** Needs an LDAP entry and an array of attributes to return. Used with pwforgot, and authentication.*/
    
    private synchronized String[] getValues(LDAPEntry entry,String[] valuesToGet){
        
        String[] valsToReturn=new String[valuesToGet.length];		// return String same size as valuesToGet.
        for (int i=0;i<valuesToGet.length;i++){				// now we have the values from the ldap
            LDAPAttribute attr=entry.getAttribute(valuesToGet[i]);		// get the attribute named by the String.
            if (attr==null){
                valsToReturn[i]="";						// If the value is null, loop round.
                continue;
            }
            Enumeration enumVals=attr.getStringValues();			// Get the values returned.
            while ((enumVals!=null) && enumVals.hasMoreElements()){		// loop for the values -(one value per entry is norm)
                valsToReturn[i]=(String)enumVals.nextElement();		// Pull the value to a String.
            }
        }
        return valsToReturn;
    }
    
    private synchronized Vector  getAllValues(LDAPEntry entry){
        
     Vector attributes = new Vector();
     LDAPAttributeSet laSet = entry.getAttributeSet();
     
        return null;
        
    }
}

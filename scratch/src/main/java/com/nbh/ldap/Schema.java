package com.nbh.LDAP;

import netscape.ldap.*;
import neal.app.log.*;
import java.util.*;
import java.io.*;

public class Schema{

    public Schema(){

	LDAPConnection ld = new LDAPConnection();

		/* Construct a new LDAPSchema object to hold
			   the schema that you want to retrieve. */
	LDAPSchema dirSchema = new LDAPSchema();

	char hr=10;

	System.out.println("Creating a log file - LDAP.log for reference...");


	try {
				log theLog=new log("LDAP.log");
			   ld.connect( "ben.consultivity.com",389, "cn=directory manager", "bigcabbage");
			   /* Get the schema from the Directory. Anonymous access okay. */
			   dirSchema.fetchSchema( ld );


                //Get and print the inetOrgPerson object class description.
                LDAPObjectClassSchema objClass = dirSchema.getObjectClass("inetOrgPerson" );
                if ( objClass != null ) {
			                    // System.out.println("inetOrgPerson := "+objClass.toString()+"\n");
			                    String personDetails=objClass.toString();
								StringTokenizer st = new StringTokenizer(personDetails);
								while (st.hasMoreTokens()) {
										String snippet=st.nextToken();
								         System.out.println(snippet);
								         theLog.add(snippet);
								     }
                }

                // Get and print the definition of the userPassword attribute.
                LDAPAttributeSchema attrType = dirSchema.getAttribute("userpassword" );
                if ( attrType != null ) {
			                    System.out.println("userPassword := " + attrType.toString()+"\n");
                }

		}
		catch ( Exception e ) {

   				System.err.println( e.toString() );
		}
  }

public static void main(String[] args){

	Schema Scheme=new Schema();
}
}
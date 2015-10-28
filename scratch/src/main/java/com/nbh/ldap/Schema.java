package com.nbh.ldap;

import java.util.StringTokenizer;

public class Schema{

    public Schema(){

        final LDAPConnection ld = new LDAPConnection();

        /* Construct a new LDAPSchema object to hold
			   the schema that you want to retrieve. */
        //final LDAPSchema dirSchema = new LDAPSchema();

        final char hr=10;

        System.out.println("Creating a log file - LDAP.log for reference...");


        try {

            ld.connect( "ben.consultivity.com",389);
            /* Get the schema from the Directory. Anonymous access okay. */
            //dirSchema.fetchSchema( ld );


            //Get and print the inetOrgPerson object class description.
            final LDAPObjectClassSchema objClass = null; //dirSchema.getObjectClass("inetOrgPerson" );
            if ( objClass != null ) {
                // System.out.println("inetOrgPerson := "+objClass.toString()+"\n");
                final String personDetails=objClass.toString();
                final StringTokenizer st = new StringTokenizer(personDetails);
                while (st.hasMoreTokens()) {
                    final String snippet=st.nextToken();
                    System.out.println(snippet);
                }
            }

            // Get and print the definition of the userPassword attribute.
            /*    final LDAPAttributeSchema attrType = dirSchema.getAttribute("userpassword" );
            if ( attrType != null ) {
                System.out.println("userPassword := " + attrType.toString()+"\n");
            }*/

        }
        catch ( final Exception e ) {

            System.err.println( e.toString() );
        }
    }

    public static void main(final String[] args){

        final Schema Scheme=new Schema();
    }
}
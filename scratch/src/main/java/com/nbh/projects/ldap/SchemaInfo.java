package com.nbh.projects.ldap;

/** outputs information from the LDAP server.
 * getSchema information, pulls information from an LDAP server and stores
 * The results in Vectors.
 * Methods exist to get the information out, and change the server and port information.
 * A self testing Main method exists at the bottom of the file.
 *
 * Neal Hardwick Jan 2001
 * version 1.0
 *
 * Constructors -
 * getSchemaInfo()
 *
 * Methods -
 * void setServer(String name)
 * void setport(int Port)
 * void getSchema()
 * Vector getClasses()
 * Vector getClassNames()
 * Vector getRequiredAttrs(int index)
 * Vector getOptionalAttrs(int index)
 *
 **/

import java.util.Enumeration;
import java.util.Vector;

// class used to pull LDAP information. Used mainly for LDAPpan and LDAPframe.

public class SchemaInfo{

    private String LDAP="localhost";
    private int port=389;
    private final Vector allClasses,allNames;

    // Constructor.
    // initialises Vectors
    public SchemaInfo() {

        this.allClasses=new Vector();
        this.allNames=new Vector();
    }

    // void setServer(String name)
    // sets the LDAP server's name.
    // The name is stored in a class variable.
    public void setServer(final String name){
        this.LDAP=name;
    }
    // void setPort(int Portnumber)
    // sets the LDAP servers port.
    // The int is stored in a class variable.
    public void setPort(final int newPort){
        this.port=newPort;
    }

    // void getSchema()
    // Gets the schema from the LDAP server.
    // Stores the information in two Vectors.
    // allClasses -contaning the class objects
    // and allNames - A vector of all the class names.
    public void getSchema() {

        this.allClasses.clear();
        this.allNames.clear();
        final LDAPConnection ld = new LDAPConnection();
        //final LDAPSchema theWholeSchema = new LDAPSchema();		// The LDAPSchema object,
        try{
            ld.connect(this.LDAP,this.port);
            //theWholeSchema.fetchSchema( ld );											// retrieve the schema
        }
        catch(final LDAPException ex){}
        final Enumeration classes=null; //theWholeSchema.getObjectClasses();		// Get the classes into an enumeration.

        /* while (classes.hasMoreElements()){
            final LDAPObjectClassSchema indvScheme=(LDAPObjectClassSchema) classes.nextElement();		// get the definition of the induvidual class
            this.allClasses.add(indvScheme);					// Create a vector from the enumeration - ready for calling class to read.
            this.allNames.add(indvScheme.getName());
        }*/
    }

    // Vector getClasses()
    // returns the vector with all Classes retrieved by the getSchema() method.

    public Vector getClasses(){					// All the classes
        return this.allClasses;
    }

    // Vector getClassNames()
    // Returns the vector containing all the class names found by getSchema.
    public Vector getClassNames(){ 				// Outputs a vector containing all the NAMES of the classes in the schema.

        return this.allNames;
    }

    // Vector getRequiredAttrs(int index)
    // Pulls out the required attributes for a class at a specific index in the classVector
    public Vector getRequiredAttrs(final int index){

        final Vector returnedReqs=new Vector();
        final LDAPObjectClassSchema currentOne=(LDAPObjectClassSchema)this.allClasses.get(index);
        if (currentOne==null) {
            return null;
        }
        /*final Enumeration attrs=currentOne.getRequiredAttributes();				// The required attributes
        while (attrs.hasMoreElements()){
            returnedReqs.add(attrs.nextElement());
        }*/
        return returnedReqs;
    }

    //Vector getOptionalAttrs(int index)
    // Pulls out the optional attributes for a class at a specific index in the 'class' vector.
    public Vector getOptionalAttrs(final int index){

        final Vector optionalReqs=new Vector();
        final LDAPObjectClassSchema currentOne=(LDAPObjectClassSchema)this.allClasses.get(index);
        if (currentOne==null) {
            return null;
        }
        /*final Enumeration attrs=currentOne.getOptionalAttributes();				// The required attributes
        while (attrs.hasMoreElements()){
            optionalReqs.add(attrs.nextElement());
        }*/
        return optionalReqs;
    }


    public static void main(final String[] args){

        SchemaInfo theSchema=null;
        theSchema=new SchemaInfo();
        //        theSchema.Schema();
        final Vector v=theSchema.getClasses();
        final Enumeration e=v.elements();
        while (e.hasMoreElements()){
            final LDAPObjectClassSchema thisOne=(LDAPObjectClassSchema )e.nextElement();
            //  System.out.println(thisOne.getName());
        }
        final Enumeration reqAttrs=theSchema.getRequiredAttrs(12).elements();
        final Enumeration optioAttrs=theSchema.getOptionalAttrs(12).elements();
        while (reqAttrs.hasMoreElements()){
            System.out.println("Required : "+reqAttrs.nextElement());
        }
        while (optioAttrs.hasMoreElements()){
            System.out.println("Optional : "+optioAttrs.nextElement());
        }
    }
}





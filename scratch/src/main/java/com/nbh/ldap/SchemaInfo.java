package com.nbh.ldap;

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
import netscape.ldap.*;
import java.util.*;

// class used to pull LDAP information. Used mainly for LDAPpan and LDAPframe.

public class SchemaInfo{
    
    private String LDAP="localhost";
    private int port=389;
    private Vector allClasses,allNames;
    
    // Constructor.
    // initialises Vectors
    public SchemaInfo() {
        
        allClasses=new Vector();
        allNames=new Vector();
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
    
    // void getSchema()
    // Gets the schema from the LDAP server.
    // Stores the information in two Vectors.
    // allClasses -contaning the class objects
    // and allNames - A vector of all the class names.
    public void getSchema() {
        
        allClasses.clear();
        allNames.clear();
        LDAPConnection ld = new LDAPConnection();
        LDAPSchema theWholeSchema = new LDAPSchema();		// The LDAPSchema object,
        try{
            ld.connect(LDAP,port);
            theWholeSchema.fetchSchema( ld );											// retrieve the schema
        }
        catch(LDAPException ex){}
        Enumeration classes=theWholeSchema.getObjectClasses();		// Get the classes into an enumeration.
        
        while (classes.hasMoreElements()){
            LDAPObjectClassSchema indvScheme=(LDAPObjectClassSchema) classes.nextElement();		// get the definition of the induvidual class
            allClasses.add(indvScheme);					// Create a vector from the enumeration - ready for calling class to read.
            allNames.add(indvScheme.getName());
        }
    }
    
    // Vector getClasses()
    // returns the vector with all Classes retrieved by the getSchema() method.
    
    public Vector getClasses(){					// All the classes
        return allClasses;
    }
    
    // Vector getClassNames()
    // Returns the vector containing all the class names found by getSchema.
    public Vector getClassNames(){ 				// Outputs a vector containing all the NAMES of the classes in the schema.
        
        return allNames;
    }
    
    // Vector getRequiredAttrs(int index)
    // Pulls out the required attributes for a class at a specific index in the classVector
    public Vector getRequiredAttrs(int index){
        
        Vector returnedReqs=new Vector();
        LDAPObjectClassSchema currentOne=(LDAPObjectClassSchema)allClasses.get(index);
        if (currentOne==null)
            return null;
        Enumeration attrs=currentOne.getRequiredAttributes();				// The required attributes
        while (attrs.hasMoreElements()){
            returnedReqs.add((String)attrs.nextElement());
        }
        return returnedReqs;
    }
    
    //Vector getOptionalAttrs(int index)
    // Pulls out the optional attributes for a class at a specific index in the 'class' vector.
    public Vector getOptionalAttrs(int index){
        
        Vector optionalReqs=new Vector();
        LDAPObjectClassSchema currentOne=(LDAPObjectClassSchema)allClasses.get(index);
        if (currentOne==null)
            return null;
        Enumeration attrs=currentOne.getOptionalAttributes();				// The required attributes
        while (attrs.hasMoreElements()){
            optionalReqs.add((String)attrs.nextElement());
        }
        return optionalReqs;
    }
    
    
    public static void main(String[] args){
        
        SchemaInfo theSchema=null;
        theSchema=new SchemaInfo();
//        theSchema.Schema();
        Vector v=theSchema.getClasses();
        Enumeration e=v.elements();
        while (e.hasMoreElements()){
            LDAPObjectClassSchema thisOne=(LDAPObjectClassSchema )e.nextElement();
            System.out.println(thisOne.getName());
        }
        Enumeration reqAttrs=theSchema.getRequiredAttrs(12).elements();
        Enumeration optioAttrs=theSchema.getOptionalAttrs(12).elements();
        while (reqAttrs.hasMoreElements()){
            System.out.println("Required : "+reqAttrs.nextElement());
        }
        while (optioAttrs.hasMoreElements()){
            System.out.println("Optional : "+optioAttrs.nextElement());
        }
    }
}





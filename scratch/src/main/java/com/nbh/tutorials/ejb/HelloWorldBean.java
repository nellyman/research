package com.nbh.tutorials.ejb;


import javax.ejb.SessionContext;

/* This class is the actual implementation of the business logic. This is the EJB for simplicity's sake. */

public class HelloWorldBean implements javax.ejb.SessionBean {
    
    private SessionContext ctx;
    public void setSessionContext(SessionContext ctx) {
        this.ctx = ctx;
    }
    public void ejbRemove() {
        System.out.println( "ejbRemove()" );
    }
    public void ejbActivate() {
        System.out.println( "ejbActivate()" );
    }
    public void ejbPassivate() {
        System.out.println( "ejbPassivate()" );
    }
    /* The method called to display the string "Hello World!" on the client. */
    public String hello() {
        System.out.println( "hello()" );
        return "Hello World!";
    }
    public void ejbCreate() {
        System.out.println( "ejbCreate()" );
    }
    
}

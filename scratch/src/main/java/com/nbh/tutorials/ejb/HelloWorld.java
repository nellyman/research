package com.nbh.tutorials.ejb;


/* This is the remote interface that the client calls to have the EJB do the work. */

public interface HelloWorld extends javax.ejb.EJBObject
{

public String hello() throws java.rmi.RemoteException; 

} 
 

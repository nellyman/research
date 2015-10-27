package com.nbh.tutorials.ejb;




/* HelloWorldHome provides the container the means to create and destroy EJB's. */

public interface HelloWorldHome extends javax.ejb.EJBHome 
{

HelloWorld create() throws java.rmi.RemoteException, javax.ejb.CreateException;

}

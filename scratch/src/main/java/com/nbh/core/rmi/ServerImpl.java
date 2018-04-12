/*
 * ServerImpl.java
 *
 * Created on 02 September 2007, 20:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.nbh.core.rmi;


import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject
    implements ServerInterface {

    private String name;

    public ServerImpl() throws RemoteException{
        super();
    }

    public String ping(String s){
        return "Hello " + s;
    }

}

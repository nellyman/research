package com.nbh.research.xml.jaxrpc;

import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: 12-Aug-2004
 * Time: 15:39:29
 * To change this template use Options | File Templates.
 */
public interface HelloServiceIF {
    public String sayHello(String s) throws RemoteException;
}

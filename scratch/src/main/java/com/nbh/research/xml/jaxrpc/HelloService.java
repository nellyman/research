package com.nbh.research.xml.jaxrpc;

import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: 12-Aug-2004
 * Time: 15:40:03
 * To change this template use Options | File Templates.
 */
public class HelloService implements HelloServiceIF{

    private String greeting="hello";

    public String sayHello(String s) throws RemoteException {
        return greeting+" "+s;
    }

}

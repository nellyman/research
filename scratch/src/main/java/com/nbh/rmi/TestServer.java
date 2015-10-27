/*
 * TestServer.java
 *
 * Created on 02 September 2007, 20:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.nbh.rmi;

import java.rmi.*;
import org.apache.log4j.Logger;  

public class TestServer {

    private static Logger log = Logger.getLogger(TestServer.class);
    
    public static ServerInterface getConnection()
        throws Exception
    {
        return (ServerInterface)
            Naming.lookup("rmi://127.0.0.1:1099/Server");
    }
    
    public static void main(String[] args){
        TestServer test = new TestServer();
        ServerInterface intf=null;
        try {
            intf = test.getConnection();
            String msg = intf.ping("Tommy");
            log.info(msg);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


/*
 * Servermain.java
 *
 * Created on 02 September 2007, 20:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.nbh.core.rmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Servermain {

    public static void main(String argv[]) {

        try {

            LocateRegistry.createRegistry(1099);

            ServerInterface s = new ServerImpl();
            Naming.rebind("//127.0.0.1/Server", s);

        } catch(Exception e) {
            System.out.println("Server: "+e.getMessage());
            e.printStackTrace();
        }
    }
}


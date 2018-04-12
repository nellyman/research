/*
 * ServerInterface.java
 *
 * Created on 02 September 2007, 20:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.nbh.core.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public String ping(String fileName) throws
        RemoteException;

}

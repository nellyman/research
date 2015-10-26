package com.nbh.tutorials.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RMIHello extends UnicastRemoteObject implements Remote, RMIGreetingIntf{

	public RMIHello() throws RemoteException{
		super();
	}

	public static void main(String[] args) {
	}

	/* (non-Javadoc)
	 * @see com.nbh.tutorials.rmi.RMIHello#getGreeting()
	 */
	public String getGreeting() throws IOException, ClassNotFoundException {
		
		return "Hello World, are you alright !!";
	}
}

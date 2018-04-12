package com.nbh.core.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RMIClient {

		private static final String SERVER="localhost";
		private static final int PORT = 1099;
		
		public static void main(String args[]) {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
/*			try {
				String name = "//" + SERVER + "/greeting";
				System.out.println("Going to find server: "+name);
				RMIGreetingIntf greeting = (RMIGreetingIntf) Naming.lookup(name);
				System.out.println("found name.");
				
				String greet = greeting.getGreeting();
				
				System.out.println(greet); 
				
			} catch (Exception e) {
				System.err.println("exception: " + e.getMessage());
				e.printStackTrace();
			}
*/
			Registry registry;
			RMIGreetingIntf rmiGreet;
			try{
				// get the �registry� 
				registry=LocateRegistry.getRegistry(SERVER,PORT);
				// look up the remote object
				rmiGreet= (RMIGreetingIntf)(registry.lookup("greeting"));
				
				// call the remote method
				System.out.println(rmiGreet.getGreeting());				
			} catch(RemoteException e){
				e.printStackTrace();
			} catch(NotBoundException e){
				e.printStackTrace();
			} catch(IOException ioe){
				ioe.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

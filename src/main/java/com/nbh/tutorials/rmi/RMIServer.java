package com.nbh.tutorials.rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RMIServer {


	public static void register(){
	
		try{
			System.out.println("starting registry... ");
			LocateRegistry.createRegistry(1099);
			RMIHello greeting = new RMIHello();
			System.out.println("registry started ");
			//register it...
			Naming.rebind("greeting", greeting);
			System.out.println("registered greeting class.");
		}catch(Exception e){
			System.out.println("exception !! "+e.getMessage());	
		}
	}

	public static void main(String[] args) throws IOException{
		
		register();
			
	}
}

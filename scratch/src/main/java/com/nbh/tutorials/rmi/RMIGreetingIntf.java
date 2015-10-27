package com.nbh.tutorials.rmi;

import java.io.IOException;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface RMIGreetingIntf {
	
	public String getGreeting() throws IOException, ClassNotFoundException;

}

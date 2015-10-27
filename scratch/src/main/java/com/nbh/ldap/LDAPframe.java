package com.nbh.LDAP;

// Frame to bring up the LDAPpan panel.
// Size is set.


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class LDAPframe extends JFrame{

public static void main(String[] args){

		final LDAPframe j=new LDAPframe();
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);					// An alternative to the above inner class - This method sets what happens when close is invoked.
		j.show();
	}
public LDAPframe(){
			super("LDAP schema details");
			setSize(600,230);									// Frame's size has to be declared here.
			LDAPpan pan=new LDAPpan();
			Container c=getContentPane();				// Get the container within the Frame - don't write to the frame itself
			c.add(pan);											// Add the Panel.
	}
}
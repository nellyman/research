package com.nbh.projects.ldap;

// Frame to bring up the LDAPpan panel.
// Size is set.


import java.awt.Container;

import javax.swing.JFrame;

public class LDAPframe extends JFrame{

    public static void main(final String[] args){

        final LDAPframe j=new LDAPframe();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					// An alternative to the above inner class - This method sets what happens when close is invoked.
        j.show();
    }
    public LDAPframe(){
        super("LDAP schema details");
        this.setSize(600,230);									// Frame's size has to be declared here.
        final LDAPpan pan=new LDAPpan();
        final Container c=this.getContentPane();				// Get the container within the Frame - don't write to the frame itself
        c.add(pan);											// Add the Panel.
    }
}
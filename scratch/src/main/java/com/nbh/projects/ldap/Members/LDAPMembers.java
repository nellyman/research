/*
 * LDAPMembers.java
 *
 * Created on 30 January 2002, 10:13
 */

package com.nbh.projects.ldap.Members;



import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import com.nbh.projects.ldap.*;

/**
 *
 * @author  Administrator
 * @version
 */

public class LDAPMembers extends JFrame{
    
    public static void main(String[] args){
        
        final LDAPMembers j=new LDAPMembers();
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);					// An alternative to the above inner class - This method sets what happens when close is invoked.
        j.show();
    }
    public LDAPMembers(){
        super("LDAP member details");
        setSize(600,230);									// Frame's size has to be declared here.
        LDAPpan pan=new LDAPpan();
        Container c=this.getContentPane();				// Get the container within the Frame - don't write to the frame itself
        c.add(pan);											// Add the Panel.
    }
}


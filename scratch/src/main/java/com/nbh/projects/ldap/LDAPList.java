package com.nbh.projects.ldap;
// Test for the jlist component

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LDAPpanList extends JPanel{

public SchemaInfo LDAPinfo;
JList theList;

public LDAPpanList(){

		LDAPinfo=new SchemaInfo();		// initialise the class to produce the schema information.
		JPanel thePanel=new JPanel();

		theList=new JList(LDAPinfo.getOptionalAttrs(1));
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.getViewport().setView(theList);
		theList.addMouseListener(mouseListener);

		thePanel.add(scrollPane);
		add(thePanel);
	}

	MouseListener mouseListener = new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount() == 2) {
	             int index = theList.locationToIndex(e.getPoint());
	             System.out.println("Double clicked on Item " + index);
	          }
	     }
	 };
}

public class LDAPList extends JFrame{

	public static void main(String[] args){
		final LDAPList j=new LDAPList();
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);					// An alternative to the above inner class - This method sets what happens when close is invoked.
		j.show();
	}
	public LDAPList(){
			super("LDAP schema details with a JList");
			setSize(550,230);									// Frame's size has to be declared here.
			LDAPpanList pan=new LDAPpanList();
			Container c=getContentPane();				// Get the container within the Frame - don't write to the frame itself
			c.add(pan);											// Add the Panel.
	}
}

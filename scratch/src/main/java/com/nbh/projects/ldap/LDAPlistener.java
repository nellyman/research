package com.nbh.projects.ldap;

// The listener associated with the LDAPpan panel class. The listener is added to the 'fetch' button.
// Basically pulls the text from the LDAPpan splits it, feeds the information into the getSchemaInfo class
// and then calls its getInfo method.
// The LDAPpan set..Box methods are called to set the information onto the box.

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class LDAPlistener extends MouseAdapter implements ActionListener{

LDAPpan lPanel;

public LDAPlistener(LDAPpan thelistend){

	lPanel=thelistend;
}

public void actionPerformed(ActionEvent e) {			// There is only one component on the Panel that has a listener associated with it, the button

	// The TextBox at the top of the Panel has information regarding the LDAP server's name and the port number. Seperated by a space
	String textEntered=lPanel.theLDAPURL.getText();
	String portEntered=lPanel.theLDAPPort.getText();

	// If possible set the new Server and Port values..
	if (textEntered!=null){
			lPanel.LDAPinfo.setServer(textEntered);
			if (!portEntered.equals("")){
					lPanel.LDAPinfo.setPort(Integer.parseInt(portEntered));

					// Read in the new Schema.
					lPanel.LDAPinfo.getSchema();
					// Display it out.
					lPanel.setClassBox();
			}
	}
}

public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount()==2){
				 			//JList localList=null;
				 			/*
	         				System.out.println(" Mouse clicked");
	         				System.out.println(lPanel.reqArea.getSelectedValue());
	         				System.out.println(lPanel.opoArea.getSelectedValue());
	         				System.out.println(lPanel.reqArea.locationToIndex(e.getPoint()));
	         				System.out.println(lPanel.opoArea.locationToIndex(e.getPoint()));
	         				System.out.println(lPanel.reqArea.hasFocus());
	         				System.out.println(lPanel.opoArea.hasFocus());*/

	         			/*	if (lPanel.reqArea.hasFocus()){
									localList=lPanel.reqArea;
							}
							if (lPanel.opoArea.hasFocus()){
									localList=lPanel.opoArea;
							}*/
							// System.out.println(lPanel.theList.getSelectedValue());
							int index = lPanel.theList.locationToIndex(e.getPoint());
							if (index!=-1){
								lPanel.setReqBox(index);
								lPanel.setOpoBox(index);
							}
			}
}

}
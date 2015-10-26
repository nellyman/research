/*
* FrameContents.java
* JPanel for displaying attributes of a LDAP server.
* A 600 x 230 frame is the best option for completing this.
* The panel is created by the constructor.
* There are methods to refresh the contents of the Lists.
*
* Constructors-
* LDAPpan()			creates the Panel.
*
* Methods -
*
* void setReqBox(int index)			Changes the contents of the required attributes box to new requirements based on the vector class-names index
* void setOpoBox(int index)			Same as setReqBox, but for the Optional Attributes
* void setClassBox()						Changes the class box.
*
**/
package com.nbh.LDAP.Members;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import netscape.ldap.*;
import com.nbh.ldap.*;


public class FrameContents extends JPanel{

public JTextField theLDAPURL=new JTextField(10);
public JTextField theLDAPPort=new JTextField(5);

JList attributeList=null;
JList memberList=null;
SchemaInfo LDAPinfo;

public FrameContents(){

		setLayout(new BorderLayout());

		LDAPinfo=new SchemaInfo();		// initialise the class to produce the schema information.
		LDAPinfo.getSchema();
		LDAPlistener memberListener=new LDAPlistener(this);
		Font fnt=new Font("Arial",Font.BOLD,14);

                // serverSelector  has labels and text fields for server selection.
		JPanel serverSelector=new JPanel();						
		serverSelector.setLayout(new FlowLayout());
		serverSelector.setLayout(new GridLayout(2,2));
		serverSelector.add(new Label("name : "));
		serverSelector.add(theLDAPURL);
		serverSelector.add(new Label("port : "));
		serverSelector.add(theLDAPPort);

                // topRight has the fetch button
		JPanel topRight=new JPanel();
		Button fetchButton=new Button("fetch");
		fetchButton.addActionListener(memberListener);				//listener added to the Button.
		topRight.add(fetchButton);

                // top Panel has server info and fetch button.
		JPanel top=new JPanel();
		top.add(serverSelector);
		top.add(topRight);

                
                // lower contains lists that hold Member information
		JPanel lower=new JPanel();
		lower.setLayout(new FlowLayout());

                
                
                
                // lowerLeft holds all members in the list.
		JPanel lowerLeft=new JPanel();
		lowerLeft.setLayout(new BorderLayout());

		lowerLeft.add(BorderLayout.NORTH,new JLabel("Member Name"));
// *********************************
// *********************************                
		Vector validateInfo=LDAPinfo.getClassNames();
		if (validateInfo.size()==0)
			validateInfo.add("");
		memberList=new JList(validateInfo);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setView(memberList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(180, 100));
		memberList.addMouseListener(memberListener);								// Add the listener to the class box.
		lowerLeft.add(BorderLayout.SOUTH,scrollPane);
		lower.add(lowerLeft);

		JPanel lowerMiddle=new JPanel();
		lowerMiddle.setLayout(new BorderLayout());

		lowerMiddle.add(BorderLayout.NORTH,new JLabel("Required Attributes"));

		/*Vector requiredCheck=LDAPinfo.getRequiredAttrs(0);
		if (requiredCheck==null)
			requiredCheck.add("");
		attributeList=new JList(requiredCheck);*/
		attributeList=new JList();
		JScrollPane reqPane = new JScrollPane();
		reqPane.getViewport().setView(attributeList);
		reqPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		reqPane.setPreferredSize(new Dimension(180, 100));
		lowerMiddle.add(BorderLayout.SOUTH,reqPane);
		lower.add(lowerMiddle);

		JPanel rightLower=new JPanel();
		rightLower.setLayout(new BorderLayout());

		rightLower.add(BorderLayout.NORTH,new JLabel("Optional Attributes"));

		/*Vector opCheck=LDAPinfo.getOptionalAttrs(0);
		if (opCheck.size()==0)
			opCheck.add("");
		opoList=new JList(opCheck);*/
		opoList=new JList();
		JScrollPane opoPane = new JScrollPane();
		opoPane.getViewport().setView(opoList);
		opoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		opoPane.setPreferredSize(new Dimension(180, 100));
		rightLower.add(BorderLayout.SOUTH,opoPane);
		lower.add(rightLower);

		add(BorderLayout.NORTH,top);
		add(BorderLayout.SOUTH,lower);
}

	public void setAttrBox(int index){
		attributeList.setListData(LDAPinfo.getRequiredAttrs(index));
	}
	public void setOpoBox(int index){
		opoList.setListData(LDAPinfo.getOptionalAttrs(index));
	}

	// Sets the class names box -used after a change of server.
	public void setMembersBox(){
		memberList.setListData(LDAPinfo.getClassNames());
	}
}


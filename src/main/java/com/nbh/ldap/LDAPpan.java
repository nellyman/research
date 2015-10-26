package com.nbh.ldap;

/** JPanel for displaying attributes of a LDAP server.
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import netscape.ldap.*;

public class LDAPpan extends JPanel{

public JTextField theLDAPURL=new JTextField(10);
public JTextField theLDAPPort=new JTextField(5);

JList opoList=null;
JList reqList=null;
JList theList=null;
SchemaInfo LDAPinfo;

public LDAPpan(){

		setLayout(new BorderLayout());

		LDAPinfo=new SchemaInfo();		// initialise the class to produce the schema information.
		LDAPinfo.getSchema();
		LDAPlistener theListener=new LDAPlistener(this);
		Font fnt=new Font("Arial",Font.BOLD,14);

		JPanel topLeft=new JPanel();							// top-left is labels and text field for server selection.
		topLeft.setLayout(new FlowLayout());
		topLeft.setLayout(new GridLayout(2,2));
		topLeft.add(new Label("name : "));
		topLeft.add(theLDAPURL);
		topLeft.add(new Label("port : "));
		topLeft.add(theLDAPPort);

		JPanel topRight=new JPanel();
		Button b=new Button("fetch");
		b.addActionListener(theListener);				//listener added to the Button.
		topRight.add(b);

		JPanel top=new JPanel();
		top.add(topLeft);
		top.add(topRight);

		JPanel lower=new JPanel();
		lower.setLayout(new FlowLayout());

		JPanel lowerLeft=new JPanel();
		lowerLeft.setLayout(new BorderLayout());

		lowerLeft.add(BorderLayout.NORTH,new JLabel("Class Name"));

		Vector validateInfo=LDAPinfo.getClassNames();
		if (validateInfo.size()==0)
			validateInfo.add("");
		theList=new JList(validateInfo);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setView(theList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(180, 100));
		theList.addMouseListener(theListener);								// Add the listener to the class box.
		lowerLeft.add(BorderLayout.SOUTH,scrollPane);
		lower.add(lowerLeft);

		JPanel lowerMiddle=new JPanel();
		lowerMiddle.setLayout(new BorderLayout());

		lowerMiddle.add(BorderLayout.NORTH,new JLabel("Required Attributes"));

		/*Vector requiredCheck=LDAPinfo.getRequiredAttrs(0);
		if (requiredCheck==null)
			requiredCheck.add("");
		reqList=new JList(requiredCheck);*/
		reqList=new JList();
		JScrollPane reqPane = new JScrollPane();
		reqPane.getViewport().setView(reqList);
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

	public void setReqBox(int index){
		reqList.setListData(LDAPinfo.getRequiredAttrs(index));
	}
	public void setOpoBox(int index){
		opoList.setListData(LDAPinfo.getOptionalAttrs(index));
	}

	// Sets the class names box -used after a change of server.
	public void setClassBox(){
		theList.setListData(LDAPinfo.getClassNames());
	}
}
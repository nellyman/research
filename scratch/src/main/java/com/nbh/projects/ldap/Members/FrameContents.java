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
package com.nbh.projects.ldap.Members;



import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.nbh.projects.ldap.LDAPlistener;
import com.nbh.projects.ldap.SchemaInfo;


public class FrameContents extends JPanel{

    public JTextField theLDAPURL=new JTextField(10);
    public JTextField theLDAPPort=new JTextField(5);

    JList attributeList=null;
    JList memberList=null;
    SchemaInfo LDAPinfo;
    JList opoList=null;


    public FrameContents(){

        this.setLayout(new BorderLayout());

        this.LDAPinfo=new SchemaInfo();		// initialise the class to produce the schema information.
        this.LDAPinfo.getSchema();
        final LDAPlistener memberListener=null; //new LDAPlistener(this);
        final Font fnt=new Font("Arial",Font.BOLD,14);

        // serverSelector  has labels and text fields for server selection.
        final JPanel serverSelector=new JPanel();
        serverSelector.setLayout(new FlowLayout());
        serverSelector.setLayout(new GridLayout(2,2));
        serverSelector.add(new Label("name : "));
        serverSelector.add(this.theLDAPURL);
        serverSelector.add(new Label("port : "));
        serverSelector.add(this.theLDAPPort);

        // topRight has the fetch button
        final JPanel topRight=new JPanel();
        final Button fetchButton=new Button("fetch");
        fetchButton.addActionListener(memberListener);				//listener added to the Button.
        topRight.add(fetchButton);

        // top Panel has server info and fetch button.
        final JPanel top=new JPanel();
        top.add(serverSelector);
        top.add(topRight);


        // lower contains lists that hold Member information
        final JPanel lower=new JPanel();
        lower.setLayout(new FlowLayout());




        // lowerLeft holds all members in the list.
        final JPanel lowerLeft=new JPanel();
        lowerLeft.setLayout(new BorderLayout());

        lowerLeft.add(BorderLayout.NORTH,new JLabel("Member Name"));
        // *********************************
        // *********************************
        final Vector validateInfo=this.LDAPinfo.getClassNames();
        if (validateInfo.size()==0) {
            validateInfo.add("");
        }
        this.memberList=new JList(validateInfo);
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setView(this.memberList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(180, 100));
        this.memberList.addMouseListener(memberListener);								// Add the listener to the class box.
        lowerLeft.add(BorderLayout.SOUTH,scrollPane);
        lower.add(lowerLeft);

        final JPanel lowerMiddle=new JPanel();
        lowerMiddle.setLayout(new BorderLayout());

        lowerMiddle.add(BorderLayout.NORTH,new JLabel("Required Attributes"));

        /*Vector requiredCheck=LDAPinfo.getRequiredAttrs(0);
		if (requiredCheck==null)
			requiredCheck.add("");
		attributeList=new JList(requiredCheck);*/
        this.attributeList=new JList();
        final JScrollPane reqPane = new JScrollPane();
        reqPane.getViewport().setView(this.attributeList);
        reqPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        reqPane.setPreferredSize(new Dimension(180, 100));
        lowerMiddle.add(BorderLayout.SOUTH,reqPane);
        lower.add(lowerMiddle);

        final JPanel rightLower=new JPanel();
        rightLower.setLayout(new BorderLayout());

        rightLower.add(BorderLayout.NORTH,new JLabel("Optional Attributes"));

        /*Vector opCheck=LDAPinfo.getOptionalAttrs(0);
		if (opCheck.size()==0)
			opCheck.add("");
		opoList=new JList(opCheck);*/
        this.opoList=new JList();
        final JScrollPane opoPane = new JScrollPane();
        opoPane.getViewport().setView(this.opoList);
        opoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opoPane.setPreferredSize(new Dimension(180, 100));
        rightLower.add(BorderLayout.SOUTH,opoPane);
        lower.add(rightLower);

        this.add(BorderLayout.NORTH,top);
        this.add(BorderLayout.SOUTH,lower);
    }

    public void setAttrBox(final int index){
        this.attributeList.setListData(this.LDAPinfo.getRequiredAttrs(index));
    }
    public void setOpoBox(final int index){
        this.opoList.setListData(this.LDAPinfo.getOptionalAttrs(index));
    }

    // Sets the class names box -used after a change of server.
    public void setMembersBox(){
        this.memberList.setListData(this.LDAPinfo.getClassNames());
    }
}


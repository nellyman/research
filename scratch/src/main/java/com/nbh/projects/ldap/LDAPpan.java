package com.nbh.projects.ldap;

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

public class LDAPpan extends JPanel{

    public JTextField theLDAPURL=new JTextField(10);
    public JTextField theLDAPPort=new JTextField(5);

    JList opoList=null;
    JList reqList=null;
    JList theList=null;
    SchemaInfo LDAPinfo;

    public LDAPpan(){

        this.setLayout(new BorderLayout());

        this.LDAPinfo=new SchemaInfo();		// initialise the class to produce the schema information.
        this.LDAPinfo.getSchema();
        final LDAPlistener theListener=new LDAPlistener(this);
        final Font fnt=new Font("Arial",Font.BOLD,14);

        final JPanel topLeft=new JPanel();							// top-left is labels and text field for server selection.
        topLeft.setLayout(new FlowLayout());
        topLeft.setLayout(new GridLayout(2,2));
        topLeft.add(new Label("name : "));
        topLeft.add(this.theLDAPURL);
        topLeft.add(new Label("port : "));
        topLeft.add(this.theLDAPPort);

        final JPanel topRight=new JPanel();
        final Button b=new Button("fetch");
        b.addActionListener(theListener);				//listener added to the Button.
        topRight.add(b);

        final JPanel top=new JPanel();
        top.add(topLeft);
        top.add(topRight);

        final JPanel lower=new JPanel();
        lower.setLayout(new FlowLayout());

        final JPanel lowerLeft=new JPanel();
        lowerLeft.setLayout(new BorderLayout());

        lowerLeft.add(BorderLayout.NORTH,new JLabel("Class Name"));

        final Vector validateInfo=this.LDAPinfo.getClassNames();
        if (validateInfo.size()==0) {
            validateInfo.add("");
        }
        this.theList=new JList(validateInfo);
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setView(this.theList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(180, 100));
        this.theList.addMouseListener(theListener);								// Add the listener to the class box.
        lowerLeft.add(BorderLayout.SOUTH,scrollPane);
        lower.add(lowerLeft);

        final JPanel lowerMiddle=new JPanel();
        lowerMiddle.setLayout(new BorderLayout());

        lowerMiddle.add(BorderLayout.NORTH,new JLabel("Required Attributes"));

        /*Vector requiredCheck=LDAPinfo.getRequiredAttrs(0);
		if (requiredCheck==null)
			requiredCheck.add("");
		reqList=new JList(requiredCheck);*/
        this.reqList=new JList();
        final JScrollPane reqPane = new JScrollPane();
        reqPane.getViewport().setView(this.reqList);
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

    public void setReqBox(final int index){
        this.reqList.setListData(this.LDAPinfo.getRequiredAttrs(index));
    }
    public void setOpoBox(final int index){
        this.opoList.setListData(this.LDAPinfo.getOptionalAttrs(index));
    }

    // Sets the class names box -used after a change of server.
    public void setClassBox(){
        this.theList.setListData(this.LDAPinfo.getClassNames());
    }
}
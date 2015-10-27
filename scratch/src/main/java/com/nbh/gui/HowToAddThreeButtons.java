/*
 * Created on 18-Jan-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.nbh.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HowToAddThreeButtons {


	public HowToAddThreeButtons(){
		
		final Frame mainFrame = new Frame("Button Example");
		
		// create the panel..
		Panel mainPanel = new Panel();
		
		// setup buttons..
		Button button1 = new Button("number 1");
		Button button2 = new Button("number 2");
		Button button3 = new Button("number 3");	
		
		// add the buttons..
		mainPanel.add(button1);
		mainPanel.add(button2);
		mainPanel.add(button3);
		
		// add the bottom panel..
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		
		mainFrame.addWindowListener(new WindowAdapter(){							//anonymous inner class WindowAdapter
				public void windowClosing(WindowEvent e){				// implement the windowClosing method.
				mainFrame.dispose();													// Closes all windows.
				System.exit(0);												// Exists the JVM
			}
		 });
		
		mainFrame.pack();
		
		mainFrame.setSize(500,70);
		mainFrame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		HowToAddThreeButtons threeButtons = new HowToAddThreeButtons();
	}
}

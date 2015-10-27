/*
 * Created on 18-Jan-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.nbh.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HowToAddThreeButtonsSwing implements ActionListener{


	final static int METAL=1;
	final static int MAC=2;
	final static int WINDOWS=3;
	final static int MOTIF=4; 
	final JFrame mainFrame;

	public HowToAddThreeButtonsSwing(){
		
		mainFrame  = new JFrame("JButton Example"); 
		
		// create the panel..
		JPanel mainPanel = new JPanel();
		
		// setup buttons..
		JButton button1 = new JButton(""+METAL);
		JButton button2 = new JButton(""+MOTIF);
		JButton button3 = new JButton(""+WINDOWS);
		JButton button4 = new JButton(""+MAC);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		
		// add the buttons..
		mainPanel.add(button1);
		mainPanel.add(button2);
		mainPanel.add(button3);
		//mainPanel.add(button3);
		
		// add the bottom panel..
		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
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
	
	public void actionPerformed(ActionEvent e) {
		Object cmp = e.getSource();
		if (cmp instanceof JButton){			
			JButton buttonPressed =(JButton)cmp; 
			String title = buttonPressed.getText();
			System.out.println("title : "+title+" pressed !!");
			setLookAndFeel(new Integer(buttonPressed.getLabel()).intValue());
			
			mainFrame.dispose();
			mainFrame.pack();
			mainFrame.setSize(500,70);
			mainFrame.setVisible(true);
			
		}else{
			System.out.println("not a button !!");
		}
		
	}
	
	private void setLookAndFeel(int type){
		
		try{
			switch(type){
				case METAL:				
					UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
					System.out.println("metal");
					break;
				case MAC:
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					System.out.println("mac");
					break;
				case MOTIF:
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel());
					System.out.println("motif");
					break;
				case WINDOWS:
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
					System.out.println("windows");
					break;
				default:
					UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
					System.out.println("default");
					break;			
			}
		}catch(UnsupportedLookAndFeelException uex){
			System.out.println("unsupported look and feel !");
		}catch(ClassNotFoundException uex){
			System.out.println("look and feel could not be found !");
		}catch(InstantiationException uex){
			System.out.println("look and feel could not be instantiated !");
		}catch(IllegalAccessException uex){
			System.out.println("look and feel cannot be used on this platform !");
		}
	}
	
	
	public static void main(String[] args) {
		HowToAddThreeButtonsSwing threeButtons = new HowToAddThreeButtonsSwing();
	}




}

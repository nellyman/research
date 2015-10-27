package com.nbh.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;

/**
 * Maintained version in util jar.
 * @author Neal
 *
 */

public class BasicDialog extends JFrame {

	private static Logger logger = Logger.getLogger(BasicDialog.class);

	private static final long serialVersionUID = 1L;

	// Load the default style and add it as the "regular" text
	private static Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	private JTextPane textPane = null;
	private ActionListener listener;

	public BasicDialog(ActionListener listener, String title, String button1Name, String button2Name) {
		
		this.listener = listener;
		textPane = new JTextPane();
		
		JButton b1 = this.createButton(button1Name);
		JButton b2 = this.createButton(button2Name);

		JPanel buttonPanel = this.createButtonPanel(b1, b2);

		Container contentPane = getContentPane();
		contentPane.add(new JScrollPane(textPane), BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		// Build the UI
		// this.getContentPane().add(new JScrollPane(this.textPane));
		this.textPane.setEditable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle(title);
		this.setSize(400, 300);
		this.setLocation(d.width / 2 - 200, d.height / 2 - 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	
	public void addText(String text) {
		StyledDocument doc = textPane.getStyledDocument();

		// Load the default style and add it as the "regular" text
		Style regular = doc.addStyle("regular", def);

		try {
			doc.insertString(0, text+"\n", regular);			
		} catch (BadLocationException e) {
			logger.error("error adding text: "+e.getMessage());
		}
	}
	
	public void clearText(){
		StyledDocument doc = textPane.getStyledDocument();
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {
			logger.error("error clearing text: "+e.getMessage());
		}		
	}

	private JPanel createButtonPanel(JButton b1, JButton b2) {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		if (b1 != null) {
			buttonPane.add(b1);
		}
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		if (b2 != null) {
			buttonPane.add(b2);
		}
		return buttonPane;
	}

	private JButton createButton(String buttonName) {
		if (buttonName == null) {
			return null;
		}
		JButton button = new JButton(buttonName);
		button.setActionCommand(buttonName);
		button.addActionListener(listener);
		return button;
	}



	public static void main(String[] args) {
		BasicDialog d = new BasicDialog(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				logger.info("'" + arg0.getActionCommand() + "'");
			}
		},"Testing", "go", null);
		d.addText("Mary had a little lamb!!");
	}

}

package com.nbh.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.nbh.tutorials.thread.LongProcess;
/**
*   Class TestCursor
*/
public class LongRunningTaskCursor extends JPanel
{
    
  public LongRunningTaskCursor()
  {
    setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
    setLayout(new GridLayout(0, 1));
    JButton button = new JButton("Execute");
    button.addMouseListener(new MouseAdapter()
      {
          Thread t=new Thread();
        public void mouseEntered(MouseEvent event)
        {
            if (t.isAlive())
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        public void mouseExited(MouseEvent event){
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        public void mouseClicked(MouseEvent event){         
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));            
            t = new Thread(new LongProcess(10000));            
            t.start();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      });
    add(button);
  }

  /**
  * main
  */
  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(
        UIManager.getCrossPlatformLookAndFeelClassName());
      JFrame frame = new JFrame("Cursor");
      LongRunningTaskCursor app = new LongRunningTaskCursor();
      frame.getContentPane().add(app, BorderLayout.CENTER);

      // Finish setting up the frame, and show it.
      frame.addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
        {
          System.exit(0);
        }
      });
      frame.pack();
      frame.setVisible(true);
    }
    catch (Exception e)
    {}

  }
}
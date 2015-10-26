package com.nbh.gui;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: 20-Aug-2004
 * Time: 10:31:18
 * To change this template use Options | File Templates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstAppl extends Frame {
  // The initial width and height of the frame
  public static int WIDTH = 250;
  public static int HEIGHT = 130;


  public FirstAppl(String lab) {
    super(lab);
      this.showLooks();
      setLayout(new GridLayout(3,1));
    JButton top = new JButton("Top");

    top.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("top");
      }
    });

    JButton bottom = new JButton("Bottom");

    bottom.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("bottom");
      }
    });

    add(new JLabel("Swing Components are like AWT 1.1"));
    add(top);
    add(bottom);
  }

  public void showLooks(){
      UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
      for (int i=0; i<info.length;i++){
          UIManager.LookAndFeelInfo thisInfo = info[i];
          System.out.println("installed look and feels are : "+thisInfo.getName()+"  "+thisInfo.getClassName());
      }
  }

  public static void main(String s[]) {

      try {
          //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
          UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();  //To change body of catch statement use Options | File Templates.
      } catch (InstantiationException e) {
          e.printStackTrace();  //To change body of catch statement use Options | File Templates.
      } catch (IllegalAccessException e) {
          e.printStackTrace();  //To change body of catch statement use Options | File Templates.
      } catch (UnsupportedLookAndFeelException e) {
          e.printStackTrace();  //To change body of catch statement use Options | File Templates.
      }


    FirstAppl frame = new FirstAppl("First Swing Application");

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {System.exit(0);}
    });

    frame.setSize(WIDTH, HEIGHT);
    frame.show();
  }
}


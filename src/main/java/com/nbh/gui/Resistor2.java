package com.nbh.gui;


import javax.swing.JOptionPane;

public class Resistor2 {
    public static void main(String args[]) {
        //the following initialises all the variables for resistor calculations
        
        int  resistorSeriesValue; //integer value used in series calculation of
        int resistor;
        int seriesTotal;    //total series output of resistor values
        
        double parallelTotal;    //total parallel output of resistor values
        double runningTotal;    //sum of inverse of all resistor values entered
        double resistorParallelValue;  //double value of resistor used in parallel
        double calculation;
        
        String input;      //string used for input of resistor values
        
        seriesTotal=0;
        runningTotal=0;      //initialise all necessary variables to zero
        parallelTotal=0; {
            
            //start do-while loop
            do {
                input=JOptionPane.showInputDialog("Enter Resistor Value or -1 to Quit:");
                //brings up the InputDialog box
                
                resistorSeriesValue=Integer.parseInt(input);
                //converts String input into an integer for use in series calculation
                
                resistorParallelValue=Double.parseDouble(input);
                //converts String input into an Double for use in parallel calculation
                
                //begin if loop
                if (resistorSeriesValue!=-1) {
                    seriesTotal=seriesTotal+resistorSeriesValue;
                    //add input value to current series total
                    
                    runningTotal=runningTotal+(1/resistorParallelValue);
                    //add inverse of input value to current parallel total
                    
                }
                //end if loop
                
            }
            while(resistorSeriesValue!=-1);
            //end do-while loop
        }
        
        parallelTotal = (1/runningTotal);//invert runningTotal to give parallelTotal
        
        JOptionPane.showMessageDialog(null,"In series, the resistors are equal to: "+
        seriesTotal+" Ohms.\nIn parallel, the resistors are equal to: "+
        parallelTotal+" Ohms","Resistor Calculator", JOptionPane.INFORMATION_MESSAGE);
        //brings up a results box showing the values of both series and parallel resistances,
        //using an Information Message (green question mark icon)
        
        System.exit(0);//exits the program and system
    }
}
/*
 * LineFormat.java
 *
 * Created on 12 November 2007, 14:05
 *
 * Need to specifiy the data and the command line, plus if the first element of data is to be removed or not...
 *e.g.
 *
 * LineFormat  d:\dataFile.txt    d:\sqlCmd.txt   yes
 * 
 */

package com.nbh.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author neal
 */
public class LineFormat {
    
    private String dataFileName;
    private String cmdFileName;
    private  boolean removeFirstDataElement;
    private String output="d:\\output.txt";
    
    /** Creates a new instance of LineFormat */
    public LineFormat(String dataFileName, String cmdFileName, boolean removeFirstDataElement) {
        this.dataFileName = dataFileName;
        this.cmdFileName = cmdFileName;
        this.removeFirstDataElement = removeFirstDataElement;
        this.format();
    }

    private void format(){
        List data = readInData();
        String cmd = this.getCommand();
        
        // now join them together !! 
        List<String> cmdLines = joinCmdData(cmd, data);
        
        this.saveData(cmdLines);
        
    }
    
    private void saveData(List<String> cmdLines){
          File f = new File(output);
           BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            
            for (String cmdLine : cmdLines){
                writer.write(cmdLine+" \r\n");
            }            
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            try {
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private List<String> joinCmdData(String cmd, List<String> data){
        List cmdLines= new ArrayList();
        String cmdLine=cmd;
        for (String d : data) {
            cmdLine=cmdLine+d;
            cmdLines.add(cmdLine);
            cmdLine=cmd;
        }
        return cmdLines;
    }
    
    private String getCommand(){
        String cmd = "Problem with command !! ";
        File f = new File(cmdFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            cmd = reader.readLine();
            System.out.println(cmd);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return cmd;
    }
    
    
    private String removeFirstElement(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        String data = tokenizer.nextToken(); // thrown away !
        
        StringBuffer buff = new StringBuffer("(");
        while (tokenizer.hasMoreTokens()){
            buff.append(tokenizer.nextToken());
            if (tokenizer.hasMoreTokens()){
                buff.append(",");
            }
        }
        
        return buff.toString();
    }
    
    
    private List<String> readInData(){
        List<String> dataList = new ArrayList();
        
        File f = new File(dataFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String data = reader.readLine();
            while (data !=null){
                if (removeFirstDataElement){
                    data = removeFirstElement(data);
                }
                System.out.println(data);
                dataList.add(data);
                data = reader.readLine();
            }                        
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
    
    public static void main(String[] args){
        System.out.println("enter 1 for defaults, (d:\\data.txt, cmd.txt, Y) ");
        String defaults = Console.readLine();
        
        String dataFileName = "d:\\data.txt";
        String cmdFileName = "d:\\cmd.txt";
        boolean remove=false;
                
        if (!"1".equals(defaults)){
            
            System.out.println("enter datafile location: ");
            dataFileName = Console.readLine();
            System.out.println("enter COMMAND file  location: ");
            cmdFileName = Console.readLine();
            System.out.println("enter 'Y' to ignore first column in data entry: ");
            String removeFirstDataElement = Console.readLine();            
            remove=false;
            if ("y".equalsIgnoreCase(removeFirstDataElement)){
                remove=true;
            }
        }
        LineFormat format = new LineFormat(dataFileName, cmdFileName, remove);
    }
    
}



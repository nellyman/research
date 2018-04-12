package com.nbh.core;


import java.io.*;

public class CommandLine
{

public static void main(String[] args) throws IOException
{

Runtime myRuntime = Runtime.getRuntime();

Process myProcess = myRuntime.exec("cmd.exe /c dir");


// Print command output onto standard output
       InputStream is = myProcess.getInputStream();
       
       InputStreamReader isr = new InputStreamReader(is);
       
       BufferedReader br = new BufferedReader(isr);
       
       String str;
       
       System.out.println("starting...");
       
       while((str = br.readLine()) != null) 
       {
           System.out.println(str);
       }
       
       is.close();
       
       System.out.println("over...");

       // Wait for the process to complete.
       int status = -1;
       
       System.out.println("waiting starts");
       
       try 
       {
        status = myProcess.waitFor();
       }
       catch (InterruptedException ie) 
       {
        System.out.println(ie);
       }
       
       System.out.println("waiting over");


} // end main



} // end CommandLine
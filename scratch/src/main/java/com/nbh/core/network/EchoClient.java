package com.nbh.core.network;


import java.net.*;
import java.io.*;

public class EchoClient 
{
  public static void main( String[] args )
  {
   try
   {
	 Socket s = new Socket( "localhost", 2000 );
         
         // read the incoming from the server ...
	 BufferedReader br = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
         // ready the queue to send data ....
	 PrintStream ps = new PrintStream( s.getOutputStream() );
         // setup the reader for the client typing...
	 BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
         
	 String line = sysin.readLine();
	 while( line.length() > 0 )
	 {
	  ps.println( line );
	  ps.flush();
	  String serverResponse = br.readLine();
	  System.out.println( "Server: " + serverResponse );
	  line = sysin.readLine();
	 }
	 s.close();
   }
   catch( Exception e )
   {
	 e.printStackTrace();
   }
  }
}
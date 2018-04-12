package com.nbh.core.network;

/**
 * EchoServer
 *
 * Uses a socket to echo back anything typed at the server...
 *
 * 
 *
**/


import java.net.*;
import java.io.*;

public class EchoServer {
  private ServerSocket serverSocket;

  public EchoServer()  {
   try {
	 serverSocket = new ServerSocket( 2000 );
	 System.out.println( "Started Echo Server on port 2000, telnet to localhost to use it... " );
	 while( true ) {
	  Socket s = serverSocket.accept();
	  System.out.println( "Received a new connection, creating the thread to handle it" );
	  SocketHandlerThread thread = new SocketHandlerThread( s );
	  thread.start();
	 }
   }
   catch( Exception e ) {
	 e.printStackTrace();
   }
  }

  public static void main( String[] args ) {
   EchoServer server = new EchoServer();
  }

  class SocketHandlerThread extends Thread {
   private Socket s;

   public SocketHandlerThread( Socket s ) {
	 this.s = s;
   }

   public void run() {
	 try {
	  BufferedReader br = new BufferedReader( new 
	  InputStreamReader( s.getInputStream() ) );
	  PrintStream ps = new PrintStream( s.getOutputStream() );
	  String line = br.readLine();
	  while( line != null ) {
	    System.out.println( "Received from client: " + line );
	    ps.println( line );
	    ps.flush();
	    line = br.readLine();
	  }
	  s.close();
	  System.out.println( "Closed Socket..." );
	 }
	 catch( Exception e ){
	  e.printStackTrace();
	 }
   }
  }
}
package com.nbh.tutorials.ejb;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.net.SocketFactory;

/**
 * Noddy program to use a simple stateless EJB.
 * Note that for JBoss the LDAP naming requires a javax.net.socketfactory class to be used
 * which is only available on JDK 14 +. To modify NetBeans add '-jdkhome c:\app\java\jsdk14' to the ide.cfg
 * in the ide bin directory.
 *
 **/


import javax.naming.*;
import java.util.Hashtable;

public class HelloWorldClient {
    
    public static void main( String [] args ) {
        
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        env.put(Context.PROVIDER_URL, "localhost:1099");
        env.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");

        try {
            
            // InitialContext lContext = new InitialContext();
            
            Context ctx = new InitialContext(env);
            //Context ctx = (Context) Sctx.lookup("java:");            
            // Object obj =  lContext.lookup( "helloworld" );
         
            NamingEnumeration enum = ctx.list("java:");
            while(enum.hasMore()){
                NameClassPair nam = (NameClassPair)enum.next();
                System.out.println(nam.getName());
            }
            Object obj = ctx.lookup( "HelloWorld" ); 
            HelloWorldHome home = (HelloWorldHome)javax.rmi.PortableRemoteObject.narrow( obj, HelloWorldHome.class );
            HelloWorld helloWorld = home.create();
            System.out.println( helloWorld.hello());
            helloWorld.remove();
            
        }
        
        catch ( Exception e ) {
            
            e.printStackTrace();
            System.out.println( "Exception: " + e.getMessage() );
            
        }
    }
}

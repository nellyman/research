/*
 * Created on 16-Nov-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.nbh.net;


import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConnectionTest {


	private static  String PROXY_LOGIN = "";

	//private static String REMOTE_LOGIN = "";
		
	private static final String TEST_URL=" https://hcc.cpm-int.com/TestSvc/FeedbackRequest.aspx";
	//private static final String TEST_URL="https://www.honda-onlinestores.co.uk/secure/servlet/uk.co.honda.secure.store.servlets.SecureStoreRebuildOrder";
	//private static final String TEST_URL="https://www.cahoot.com/";
	// private static final String TEST_URL="https://seal.verisign.com/splash?form_file=fdf/splash.fdf&dn=SCGI.EBAY.COM&lang=en";
	private static boolean PROXIES_SET=false;



	public void testConnection() throws Exception{
		
		System.out.println("Trying to connect...");
		HttpURLConnection connection = this.getProxyConnection(TEST_URL);
		connection.connect();
		//Certificate[] certs = connection.getServerCertificates();
		System.out.println("Connected !!");
	}





	private void setProxies(){
		// sets all proxies...
		System.setProperty("proxySet", "true");
		System.setProperty("https.proxyUser", "uiamm");
		System.setProperty("https.proxyPassword", "nqop98t");			
		System.setProperty("https.proxyHost", "eheproxypa.eu.honda.com");
		System.setProperty("https.proxyPort", "8080");
						
		System.setProperty("http.proxyHost", "eheproxypa.eu.honda.com");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("http.proxyUser", "uiamm");
		System.setProperty("http.proxyPassword", "nqop98t");
			
		PROXY_LOGIN = new sun.misc.BASE64Encoder().encode(("uiamm:nqop98t").getBytes());
		
		//REMOTE_LOGIN = new sun.misc.BASE64Encoder().encode((REMOTE_USER + ":" + REMOTE_PASSWORD).getBytes());		
	}


	private HttpURLConnection getProxyConnection(String urlStr)  throws Exception{
	
		if (!PROXIES_SET){
			setProxies();
			PROXIES_SET=true;
		}
		
		URL url = new URL(urlStr);
		//HttpsURLConnection.setDefaultHostnameVerifier(new  MyHostnameVerifier());
		
		
/*		SSLContext sc = SSLContext.getInstance("SSL");
		System.setProperty("javax.net.ssl.trustStore", "localhost.public");
		
		sc.init(null, null, new java.security.SecureRandom());
		
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
	*/	

		//connection.setHostnameVerifier(new  MyHostnameVerifier());
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		connection.setRequestProperty("Proxy-Authorization", "Basic " + PROXY_LOGIN);
		
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty(
			"Content-Type",
			"application/x-www-form-urlencoded;   charset=ISO8859-1");

		return connection;
	}


	public void testPkg(){
		Object fact=SSLSocketFactory.getDefault();
		System.out.println(fact.getClass().toString() );
	}


	public static void main(String[] args) throws Exception {
		

//		System.setProperty("java.protocol.handler.pkgs","com.ibm.net.ssl.www.protocol");
//		System.setProperty("java.protocol.handler.pkgs1","com.sun.net.ssl.internal.www.protocol");
		ConnectionTest test =  new ConnectionTest();
		test.testPkg();
		test.testConnection();
	}
	
	
	class MyHostnameVerifier implements HostnameVerifier {

		public boolean verify(String hostname, SSLSession session) {

			return true;
		}
	}

	
}

package com.nbh.core.net;

import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author uinxh
 * 
 * Created: 02-Mar-07
 *
 */
public class ConnectionProxy {

	private static  String PROXY_LOGIN = "";
	private static boolean PROXIES_SET=false;

	public static void setProxies(){
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
	
	public static String getProxyLogin(){
		return PROXY_LOGIN;
	}
	
	public static HttpURLConnection getProxyConnection(String urlStr)  throws Exception{
	
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
	
}

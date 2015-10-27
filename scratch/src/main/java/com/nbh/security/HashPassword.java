package com.nbh.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ibm.misc.BASE64Decoder;
import com.ibm.misc.BASE64Encoder;
import com.nbh.common.LineReader;

/**
 * 
 * From http://www.owasp.org/index.php/Hashing_Java
 * 
 * A good cryptographic Hash function must have these properties:
 * Preimage resistant : From the function output y it must 
 * impossible to compute the input x such that hash(x)=y. 
 * Second preimage resistant : from an input x1 it must 
 * impossible to compute another input x2 (different of x1) 
 * such that hash(x1)=hash(x2). 
 * Collision resistant : It must be difficult to find two inputs x1 
 * and x2 (x1<>x2) such that hash(x1)=hash(x2). 
 * 
 * 
 * A salt is a random number of a fixed length. This salt must be different 
 * for each stored entry. It must be stored as clear text next to the hashed password. 
 * In this configuration, an attacker must handle a brute force attack on each 
 * individual password. The database is now birthday attack resistant. 
 * A 64 bits salt is recommended in RSA PKCS5 standard.
 *  
 * To slow down the computation it is recommended to iterate the hash 
 * operation n times. While hashing the password n times does slow down 
 * hashing for both attackers and typical users, typical users don't really 
 * notice it being that hashing is such a small percentage of their total 
 * time interacting with the system. On the other hand, an attacker trying 
 * to crack passwords spends nearly 100% of their time hashing so hashing n 
 * times gives the appearance of slowing the attacker down by a factor of n 
 * while not noticeably affecting the typical user. A minimum of 1000 operations 
 * is recommended in RSA PKCS5 standard. 
 * 
 * 
 *  
 * @author uinxh
 *
 */

public class HashPassword {

	private static String SALT = "ThisIsAddedOnToIncreaseHashComplexity";
	
	private static int iterationNb=1000;
	
	 public static byte[] getHash(String password) throws NoSuchAlgorithmException, IOException {

	       MessageDigest digest = MessageDigest.getInstance("SHA-1");
	       digest.reset();
	       digest.update(base64ToByte(SALT));
	       byte[] bytes = digest.digest(password.getBytes("UTF-8"));
	       for (int i = 0; i < iterationNb; i++) {
	           digest.reset();
	           bytes = digest.digest(bytes);
	       }
	       return bytes;
	 }


	   /**
	    * From a base 64 representation, returns the corresponding byte[] 
	    * @param data String The base64 representation
	    * @return byte[]
	    * @throws IOException
	    */
	   private static byte[] base64ToByte(String data) throws IOException {
	       BASE64Decoder decoder = new BASE64Decoder();
	       return decoder.decodeBuffer(data);
	   }
	 
	   /**
	    * From a byte[] returns a base 64 representation
	    * @param data byte[]
	    * @return String
	    * @throws IOException
	    */
	   private static String byteToBase64(byte[] data){
	       BASE64Encoder endecoder = new BASE64Encoder();
	       return endecoder.encode(data);
	   }
	 
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		System.out.println(HashPassword.getHash("Passw0rd"));
		
		System.out.println("Please set the password: ");
		String password = LineReader.getLineFromSystemIn();
		byte[] passBytes = HashPassword.getHash(password);
		boolean match=false;
		while (match){
			System.out.println("Now lets login, enter a password: ");
			password = LineReader.getLineFromSystemIn();
			byte[] candidateBytes = HashPassword.getHash(password);
			if (passBytes.equals(candidateBytes)){
				match=true;
				System.out.println("They match, well done !");
			}else{
				System.out.println("incorrect password");
			}
		}
		
	}

}

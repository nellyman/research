package com.nbh.security;

import java.io.IOException;

/**
 * Tests password masking entry.
 * From the cmd line, (not ide window, doesn't work) will mask whatever entered with '*'
 * The password is stored into an array, Strings are not recomended as they are immutable (?)
 *
 * Using a string for this purpose has numerous problems, including:
 *
 * o..... It's not pinned, so the garbage collector can move it around at will leaving several copies in memory
 * o..... It's not encrypted, so anyone who can read your process' memory will be able to see the value of the string
 *          easily.  Also, if your process gets swapped out to disk, the unencrypted contents of the string will be sitting in your swap file.
 * o....  It's not mutable, so whenever you need to modify it, there will be the old version and the new version both in memory
 * Since it's not mutable, there's no effective way to clear it out when you're done using it
 * The recommended solution was to use a byte array.  Since byte arrays can be pinned down, encrypted, and
 * zeroed out, many of the above concerns were solved.
 *
 */
public class PasswordApp {

   public static void main(String argv[]) {
          char password[] = null;
          try {
             password = PasswordField.getPassword(System.in, "Enter your password: ");
          } catch(IOException ioe) {
             ioe.printStackTrace();
          }
          if(password == null ) {
             System.out.println("No password entered");
          } else {
             System.out.println("The password entered is: "+String.valueOf(password));
          }
       }
   }

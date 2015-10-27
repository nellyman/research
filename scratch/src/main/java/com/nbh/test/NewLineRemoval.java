package com.nbh.test;

import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: May 20, 2004
 * Time: 1:34:26 PM
 * To change this template use Options | File Templates.
 */
public class NewLineRemoval {

    private static final String SEP = System.getProperty("line.separator");
    //private static String text=SEP+"df"+SEP;
    String text = "\nOnce upon a time a man went to see another man about a dog. \n\n\nThey met\n in a pub, close to a river. It was a lovely day, the sun was shining and the birds were singing in the lush trees above his head.\n\n";

    public void showText(){
        System.out.println(text);
        System.out.println("to");
        System.out.println(this.removeEOL(text));

    }


    public String removeEOL(String text){
        text=text.trim();
        int offset=1,oldOffSet=0;
        String Str="";
        int len=text.length();
        while(offset>0){
            offset=text.indexOf('\n',oldOffSet);
            if (offset!=-1 & offset<len){
                Str+=text.substring(oldOffSet,offset);
            }
            else{
                Str+=text.substring(oldOffSet);
            }
            oldOffSet=(++offset);
        }

        return Str;
    }



    public static void main(String[] args){

         NewLineRemoval nl = new NewLineRemoval();
         nl.showText();


    }





}

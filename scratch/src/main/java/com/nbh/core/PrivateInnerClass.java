package com.nbh.core;

public class PrivateInnerClass {
    public static void main(String[] args){
        //System.out.println(InnerWrapper.MessageHolder.getMessage());

        // testing of mutating an instance variable...
        StringBuffer msgX =InnerWrapper.getMessage();
        System.out.println(msgX);
        msgX.append("modified ;-)");
        System.out.println(InnerWrapper.getMessage());

        // testing a non-mutaing instance variable...
        String msg1 = InnerWrapper.getString();
        System.out.println(msg1);
        msg1="String modified";
        System.out.println(InnerWrapper.getString());
    }
}


 class InnerWrapper{

     private static int seven=7;

    private static  class MessageHolder{
        private static StringBuffer  msg =new StringBuffer("Hello world.");
        private static String msg1 ="non-mutating String";

        public void testVariableAccess(){
            System.out.println(seven);
        }

        public static StringBuffer getMessage(){
            return(msg);
        }
        public static String getString(){
            return(msg1);
        }
    }

     public static StringBuffer getMessage(){
        return MessageHolder.getMessage();
     }

     public static StringBuffer getMessageCopy(){
         StringBuffer st = new StringBuffer(MessageHolder.getMessage().toString());
         return st;
     }

     public static String getString(){
         return MessageHolder.getString();
     }

}
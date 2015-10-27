package com.nbh.test;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Apr 30, 2004
 * Time: 9:04:35 AM
 * To change this template use Options | File Templates.
 */
public class SimpleTests {

    private String inst1;
    private int inst2;

    public SimpleTests(){
        String local1;
        System.out.println(" instance 1 : "+inst1);
        System.out.println(" instance 2 : "+inst2);
        //System.out.println(" local : "+local1);
    }

    public static void main(String[] args){
        SimpleTests test = new SimpleTests();
    }
}

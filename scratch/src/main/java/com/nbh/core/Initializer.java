package com.nbh.core;

public class Initializer {

    static {
        System.out.println("Static Block");
    }

    {
        System.out.println("Initialization Block");
    }

    Initializer(){
        System.out.println("Constructor");
    }

    public static void main(String[] args){
        System.out.println("Main Method");
        Initializer i= new Initializer();
    }
}

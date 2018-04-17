package com.nbh.core;

public final class Widget {

    static Widget instance = new Widget();

    private Widget(){
        System.out.println(" constructor!");
    }

    static{
        System.out.println(" static init");
    }

    {
        System.out.println("instance init");
    }


    static Widget getInstance(){
        System.out.println("getInstance!");
        return instance;
    }

    public String helloWorld(){
        return "Hello";
    }
}


class Test{
    public static void main(String[] args) {
        System.out.println("main...");
        System.out.println(Widget.getInstance().helloWorld());
    }
}

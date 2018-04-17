package com.nbh.core;

/**
 * can only be declared public or package private
 */
public class OuterClass {

    static int value=1;
    int value2 =2;

    /**
     * a nested class can be declared private, public, protected,
     * or package private
     */
    static class StaticNestedClass{

        String sayHello(){
            System.out.println("value: "+ OuterClass.value);
            return "hello";
        }
    }

    /**
     * a nested class can be declared private, public,
     * protected, or package private
     *
     * an inner class is associated with an instance of its enclosing class and has
     * direct access to that object's methods and fields
     */
    class InnerClass{

        public String sayHello(){
            System.out.println("static value"+OuterClass.value);
            System.out.println("instance value2 "+value2);
            return "Hola";
        }
    }
}


class TestClasses{

    public static void main(String[] args) {
        OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
        System.out.println(nested.sayHello());

        // create the inner class...
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        System.out.println(inner.sayHello());
    }
}
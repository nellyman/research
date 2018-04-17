package com.nbh.core;

public class Fibonacci2 {

    public static void main(String[] args) {
        Fibonacci2 fib = new Fibonacci2();
        System.out.println(fib.calc(0));
        System.out.println(fib.calc(1));
        System.out.println(fib.calc(2));
        System.out.println(fib.calc(3));


    }

    public int calc(int input){
        System.out.println("input = " + input);
        if (input<=1)
            return input;
        if (input>100){
            System.exit(0);
        }
        return calc(input - 2) + calc(input - 1);
    }
}

package com.nbh.core;

public class Fibonacci {

    public static void main(String[] args) {
        int n1 = 0, n2 = 1, loop = 10;
        recursiveFib(n1, n2, loop);
    }


    static void recursiveFib(int n1, int n2, int total) {
        if (total-- > 0) {
            int n3 = n1 + n2;
            System.out.print(" " + n3);
            recursiveFib(n2, n3, total);
        }
    }
}

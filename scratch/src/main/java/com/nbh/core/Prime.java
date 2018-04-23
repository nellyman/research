package com.nbh.core;

public class Prime {


    boolean isPrime(int n) {
        if (n==1){
            return true;
        }
        if (n == 2) {
            return false;
        }
        for(int i=3;i<n;i=i+2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Prime p = new Prime();
        System.out.println(p.isPrime(13));
        System.out.println(p.isPrime(12));
    }


}

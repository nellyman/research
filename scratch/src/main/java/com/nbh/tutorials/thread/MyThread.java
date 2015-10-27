package com.nbh.tutorials.thread;


  public class MyThread implements Runnable {
    private String holdA = "This is ";
    private int[] holdB = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String args[]) {
      MyThread z = new MyThread();
      (new Thread(z)).start();
      (new Thread(z)).start();
    }

    public synchronized void run() {
      for(int w = 0;w < 10;w++) {
        System.out.println(holdA + holdB[w] + ".");
      }
    }
  }
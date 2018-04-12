/*
 * TwoThreads.java
 *
 * Created on 16 May 2003, 14:54
 */

package com.nbh.core.thread;

/**
 *
 * @author  neal and rachel
 */
public class TwoThreads implements Runnable {
    
    public void run() {
        
        while(x<50){
            Thread t= Thread.currentThread();
            t.setPriority(Thread.MAX_PRIORITY); // indeterminate which thread works
            System.out.println(Thread.currentThread().getName()+" has X as "+x++);
            t.setPriority(Thread.MIN_PRIORITY); // indeterminate which thread works
            // Thread.yield(); // without yield thread1 hogs the processing
        }
    }
    
    int x=0;
    /** Creates a new instance of TwoThreads */
    public TwoThreads() {
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TwoThreads t= new TwoThreads();
        // Thread p = new Thread(new TwoThreads());    // 2 objects 2 differing counts
        // Thread q = new Thread(new TwoThreads());    // 2 objects 2 differing counts
        Thread p = new Thread(t);   // one object, 1 count.
        Thread q = new Thread(t); // one object, 1 count.
        p.start();
        q.start();
    }
    
    
}

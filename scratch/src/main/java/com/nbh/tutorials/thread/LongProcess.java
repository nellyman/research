/*
 * Thread2.java
 *
 * Created on 29 April 2003, 11:31
 */

package com.nbh.tutorials.thread;

/**
 *
 * @author  neal and rachel
 */
public class LongProcess implements Runnable {
    
    /** Creates a new instance of Thread2 */
    public LongProcess() {
        this.print(1000);
    }
    
    public LongProcess(int delay) {
        this.print(delay);
    }    
       
    public void run() {
        this.print(1000);
    }
    
    public synchronized void print(int delay){
        
        System.out.println("Long Process started");
        Thread t = Thread.currentThread();
        try{
            t.sleep(delay);
        }
        catch(Exception ioe){
            
        }
        System.out.println("Long Process finished");
        notifyAll();
    }
    
    public static void main(String[] args){
        
        //Thread t = new Thread(new LongProcess());
        //t.start();
        LongProcess lp =new LongProcess();
        
    }
    
}

/*
 * Thread1.java
 *
 * Created on 29 April 2003, 11:26
 *
 *
 * Classes showing wait and notify behaviour. Note that one thread is used throughout, (except threadcount)
 */

package com.nbh.tutorials.thread;

/**
 *
 * @author  neal and rachel
 */
public class Thread1 implements Runnable {
    
    /** Creates a new instance of Thread1 */
    public Thread1() {
    }
    
    /** When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     *
     */
    
    LongProcess lp = new LongProcess();
    ThreadCount tc = new ThreadCount();
    public  void run() {
        
        Thread count = new Thread(tc);
        count.start();
        
        synchronized(lp){
            
            System.out.println("Hello world");
            try{
                lp.print(10);
                System.out.println("Long process is taking its time !!");
                wait();
                
            }
            catch(Exception e){
                
            }
            System.out.println("Both Threads finished !!");
            tc.stop();
        }
    }
    
    public static void main(String[] args){
        
        Thread t = new Thread(new Thread1());
        t.start();
        
    }
    
}

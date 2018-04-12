/*
 * ThreadCount.java
 *
 * Created on 29 April 2003, 12:01
 */

package com.nbh.core.thread;

/**
 *
 * @author  neal and rachel
 */
public class ThreadCount implements Runnable {
    
    boolean loop = true;
    int interval = 500;
    
    public ThreadCount(){
    
    }
    
    
    /** Creates a new instance of ThreadCount */
    public ThreadCount(int _interval) {
        interval = _interval;
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
    public void run() {
        
        while(loop){
            Thread t = Thread.currentThread();
            synchronized(t){
                t.setPriority(Thread.MAX_PRIORITY);
                Runtime rt = Runtime.getRuntime();
                System.out.println("Threads in use "+Thread.activeCount()+" Memory used by JVM is "+(rt.totalMemory()-rt.freeMemory())/1024+" k   available memory is  "+rt.freeMemory()/1024+" k");
                t.setPriority(Thread.MIN_PRIORITY);
                try{
                    t.sleep(interval);
                    t.yield();
                }
                catch(InterruptedException ioe){
                    
                }
                
            }
        }
    }
    
    public void stop(){
        
        loop=false;
    }
    
    public static void main(String[] args){
        
        ThreadCount t =new ThreadCount();
        Thread s = new Thread(t);
        s.start();
        try{
         s.sleep(7000);   
            
        }catch(Exception e){
        
        }
        t.stop();
    }
    
}

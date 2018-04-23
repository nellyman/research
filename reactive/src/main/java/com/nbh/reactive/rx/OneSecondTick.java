package com.nbh.reactive.rx;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class OneSecondTick {

    public static void main(String[] args) throws Exception{
        System.out.println(Thread.currentThread().getName());
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);
        secondIntervals.subscribe(s -> System.out.println(Thread.currentThread().getName()+" = " + s));

        // hold main thread open for 5 seconds in order to fire daemon...
        System.out.println(Thread.currentThread().getName()+" waiting...");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+" stop!");
    }
}

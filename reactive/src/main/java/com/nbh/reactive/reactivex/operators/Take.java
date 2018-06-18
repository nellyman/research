package com.nbh.reactive.reactivex.operators;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Two overloads on this filter.
 * First takes the specified number of emissions
 * Second takes emissions over a specified time interval
 */
public class Take {

    public static void main(String[] args) throws Exception{
        itemCount();
        timeCount();
    }

    public static void itemCount(){
        AbstractSetup.STRING_OBSERVABLE
                .take(3)
                .subscribe(s -> System.out.println("s = " + s));
    }

    public static void timeCount()throws Exception{
        AbstractSetup.TIME_OBSERVABLE
                .take(2, TimeUnit.SECONDS)
                .subscribe(
                        s -> System.out.println("s = " + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("done")
                );
        Thread.sleep(5000);
        System.out.println("complete");
    }
}


package com.nbh.reactive.reactivex.observables;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Disposable  is used to clean up Obserservers for GC.
 */
public class DisposableObserver {

    public static void main(String[] args) throws Exception{

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = seconds.subscribe(l -> System.out.println("l = " + l));

        Thread.sleep(5000);

        System.out.println("disposable.isDisposed() = " + disposable.isDisposed());
        disposable.dispose();
        System.out.println("disposable.isDisposed() = " + disposable.isDisposed());

        Thread.sleep(5000);
    }
}

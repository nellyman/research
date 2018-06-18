package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class DisposableComposite {
    public static void main(String[] args) throws Exception{

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable1 = seconds.subscribe(l -> System.out.println("First = " + l));
        Disposable disposable2 = seconds.subscribe(l -> System.out.println("Second = " + l));

        compositeDisposable.addAll(disposable1, disposable2);
        Thread.sleep(5000);
        System.out.println(compositeDisposable.isDisposed());
        compositeDisposable.dispose();
        Thread.sleep(5000);
        System.out.println(compositeDisposable.isDisposed());
    }
}

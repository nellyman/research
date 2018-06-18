package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObserverVariations {

    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        verboseObserver(source);
        lambdasObserver(source);
        veryOptimisedObserver(source);
    }

    private static void lambdasObserver(Observable<String> source){
        source
                .map(String::length)
                .filter(i -> i >= 5)
                .subscribe(i -> System.out.println("lambdas Observer: " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("done!")
                );
    }

    private static void veryOptimisedObserver(Observable<String> source){
        source.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(i -> System.out.println("veryOptimised = " + i));
    }

    private static void verboseObserver(Observable<String> source){
        Observer<Integer> myObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("verbose: onSubScribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("verbose: OnNext");
                System.out.println("verbose: integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("verbose: onError");
            }

            @Override
            public void onComplete() {
                System.out.println("verbose: OnComplete");
            }
        };

        source
                .map(String::length)
                .filter(i -> i >= 5)
                .subscribe(myObserver);
    }
}

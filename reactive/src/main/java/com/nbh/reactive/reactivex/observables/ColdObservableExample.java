package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;

public class ColdObservableExample {

    public static void main(String[] args) {

        // finite data sources are usually cold
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        // two observers, but they will get all the Observable emissions each
        source.map(String::length)
                .filter(i-> i>=5)
                .subscribe(s -> System.out.println("Observer 1 received: " + s));

        source.subscribe(s -> System.out.println("Observer 2 received: " + s));

        // note all emission go to Observer 1 then Observer 2
        // A hot Observable will cast to all Observers
    }
}

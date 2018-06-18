package com.nbh.reactive.reactivex.observables;


import io.reactivex.Observable;

public class ListContentLength {
    public static void main(String[] args) {
        Observable<String> strings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        strings.map(s -> s.length())
               .subscribe(s -> System.out.println(s));
    }
}

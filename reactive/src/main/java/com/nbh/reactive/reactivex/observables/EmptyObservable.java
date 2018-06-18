package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyObservable {

    static Logger logger = LoggerFactory.getLogger(EmptyObservable.class);

    public static void main(String[] args) {
        Observable<String> empty = Observable.empty();

        empty.subscribe(s -> logger.info("onNext: {}", s),
                Throwable::printStackTrace,
                () -> logger.info("onComplete: Done"));
    }
}

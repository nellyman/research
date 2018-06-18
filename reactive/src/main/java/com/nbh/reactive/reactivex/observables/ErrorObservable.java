package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorObservable {

    static Logger logger = LoggerFactory.getLogger(ErrorObservable.class);

    public static void main(String[] args) {

        Observable.error(new Exception("Crash and Burn!!"))
                .subscribe(i -> logger.info("received: " + i),
                        Throwable::printStackTrace,
                        () -> logger.info("done"));
    }
}

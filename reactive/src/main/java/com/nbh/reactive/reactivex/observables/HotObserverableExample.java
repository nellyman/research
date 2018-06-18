package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HotObserverableExample {

    static Logger logger = LoggerFactory.getLogger(HotObserverableExample.class);

    public static void main(String[] args)throws Exception {
        logger.info("start");
        ConnectableObservable<Long> secondIntervals =
                Observable.interval(1, TimeUnit.SECONDS).publish();

        secondIntervals.subscribe(s -> logger.info(" Observer1 = {}", s));
        secondIntervals.connect();
        Thread.sleep(3000);

        secondIntervals.subscribe(s -> logger.info(" Observer2 = {}", s));
        secondIntervals.connect();
        Thread.sleep(5000);

        // note using connectable Observable the emissions are multicast to all observers
        // at once, and Observer 2 does not get it's own emissions

    }
}

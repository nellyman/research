package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeferObserver {

    static Logger logger = LoggerFactory.getLogger(DeferObserver.class);

    static int start=4;
    static int count=7;

    public static void main(String[] args) {
        Observable<Integer> source = Observable.range(start, count);
        source.subscribe(s -> logger.info("Observer1: {}",s));

        // modify count, is this reflected on next observer?
        count=2;
        source.subscribe(s -> logger.info("Observer2: {}",s));

        logger.info("Lets try again using Defer...");

        count=7;
        Observable<Integer> source2 = Observable
                .defer(
                ()->Observable.range(start, count)
                );
        source2.subscribe(s -> logger.info("Observer3: {}",s));

        // modify count, is this reflected on next observer?
        count=2;
        source2.subscribe(s -> logger.info("Observer4: {}",s));
        logger.info("The defer will create a 'fresh' Observable for each Observer");
    }
}

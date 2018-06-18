package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RangeExample {

    static Logger logger = LoggerFactory.getLogger(RangeExample.class);

    public static void main(String[] args) {
        Observable.range(5, 7)
                .subscribe(s-> logger.info("RECEIVED: {}",s));

    }
}

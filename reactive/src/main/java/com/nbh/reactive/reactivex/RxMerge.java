package com.nbh.reactive.reactivex;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RxMerge {

    private static final Logger logger =
            LoggerFactory.getLogger(RxMerge.class);

    public static void main(String[] args) throws InterruptedException {

        Flowable<String> intervals = Flowable
                .interval(100, TimeUnit.MILLISECONDS,
                        Schedulers.computation())
                .map(tick -> "Tick #" + tick)
                .subscribeOn(Schedulers.computation());

        Flowable<String> strings = Flowable.just(
                "abc", "def", "ghi", "jkl")
                .subscribeOn(Schedulers.computation());

        Flowable<Object> uuids = Flowable
                .generate(emitter -> emitter.onNext(UUID.randomUUID()))
                .subscribeOn(Schedulers.computation());

        logger.info("Merge ====================================");
        //Merging streams provides a single stream that mixes elements
        //from the various sources
        Flowable.merge(intervals, strings, uuids)
                .subscribe(obj -> logger.info("Received: {}", obj));

        Thread.sleep(7000);
    }


}


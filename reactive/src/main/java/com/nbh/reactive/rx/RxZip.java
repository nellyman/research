package com.nbh.reactive.rx;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RxZip {

    private static final Logger logger =
            LoggerFactory.getLogger(RxZip.class);

    public static void main(String[] args) throws InterruptedException {


        Flowable<String> intervals = Flowable
                .interval(250, TimeUnit.MILLISECONDS,
                        Schedulers.computation())
                .limit(10)
                .map(tick -> "Tick #" + tick)
                .subscribeOn(Schedulers.computation());

        Flowable<String> strings = Flowable.just(
                "abc", "def", "ghi", "jkl")
                .subscribeOn(Schedulers.computation());

        Flowable<Object> uuids = Flowable
                .generate(emitter -> emitter.onNext(UUID.randomUUID()))
                .limit(10)
                .subscribeOn(Schedulers.computation());

        logger.info(" Zip ====================================");
        Flowable.zip(intervals, uuids, strings,
                (i, u, s) -> String.format("%s {%s} -> %s", i, u, s))
                .subscribe(obj -> logger.info("Received: {}", obj));

        Thread.sleep(7000);
    }
}

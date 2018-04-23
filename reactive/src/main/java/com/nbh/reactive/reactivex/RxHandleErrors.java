package com.nbh.reactive.reactivex;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RxHandleErrors {

    static Logger logger = LoggerFactory.getLogger(RxHandleErrors.class);

    /**
     * The example above creates an Observable of String values (in other words, a stream of String
     values), where the values are being picked from a predeined list. We also introduced random
     failures.
     * @param args
     */
    public static void main(String[] args) throws  Exception{
        List<String> data =
                Arrays.asList("foo", "bar", "baz");

        Random random = new Random();

        Observable<String> source =
                Observable.create(subscriber -> {
                    for (String s : data) {
                        if (random.nextInt(6) == 0) {
                            subscriber.onError(
                                    new RuntimeException("Bad luck for you..."));
                        }
                        subscriber.onNext(s);
                    }
                    subscriber.onComplete();
                });


        for (int i = 0; i < 10; i++) {
            logger.info("Try: "+i+" ====================================");
            source
                    .retry(3)
                    .subscribe(
                    next -> logger.info("Next: {}", next),
                    error -> logger.error("Whoops"),
                    () -> logger.info("Done"));
        }


        logger.info("Single Thread ====================================");
        Flowable.range(1, 5)
                .map(i -> i * 10)
                .map(i -> {
                    logger.info("map({})", 1);
                    return i.toString();
                })
                .subscribe(logger::info);

        Thread.sleep(1000);

        logger.info("Multi Thread ====================================");
        Flowable.range(1, 5)
                .map(i -> i * 10)
                .map(i -> {
                    logger.info("map({})", i);
                    return i.toString();
                })
                .observeOn(Schedulers.single())
                .subscribeOn(Schedulers.computation())
                .subscribe(logger::info);
        Thread.sleep(1000);
    }
}

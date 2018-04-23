package com.nbh.reactive;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * See
 * http://www.vogella.com/tutorials/RxJava/article.html
 */
public class RxJavaTest {

    Logger logger = LoggerFactory.getLogger(RxJavaTest.class);

    String result = "";

    @Test
    public void simpleObserver() {
        result = "";
        Observable<String> observer = Observable.just("Hello"); // provides datea
        observer.subscribe(s -> result = s); // Callable as subscriber
        assertTrue(result.equals("Hello"));
    }

    @Test
    public void observableEvents() {
        Observable<Integer> stream = Observable.create(subscriber -> {
            System.out.println("Started emitting");

            System.out.println("Emitting 1st");
            subscriber.onNext(1);

            System.out.println("Emitting 2nd");
            subscriber.onNext(2);
            System.out.println("Emitting onComplete");
            subscriber.onComplete();
        });

        stream.subscribe(); // without this nothing happens
    }

    @Test
    public void differentStreams() {
        Flowable<Integer> flowable = Flowable.just(6, 4, 10, 20);
        flowable.subscribe(
                val -> logger.info(val + " "),
                err -> {
                    logger.info("nerror ");
                },
                () -> logger.info("ncompleted"));
        Flowable<String> flowable2 = Flowable.fromIterable(Arrays.asList("red", "green", "blue"));
        flowable2.subscribe(
                val -> logger.info(val + " "),
                err -> {
                    logger.info("nerror ");
                },
                () -> logger.info("ncompleted"));
    }

    @Test
    public void slowMethod() throws Exception {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish
    }
}

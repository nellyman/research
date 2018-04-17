package com.nbh.reactive.rx;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RxHello {

    static Logger logger = LoggerFactory.getLogger(RxHello.class);

    public static void main(String[] args) throws Exception{

        Vertx vertx = Vertx.vertx();

        Single.just(1)
                .map(i -> i * 10)
                .map(Object::toString)
                .subscribe((Consumer<String>) logger::info);

        Maybe.just("Something")
                .subscribe(logger::info);

        Maybe.never()
                .subscribe(o -> logger.info("Something is here..."));

        Completable.complete()
                .subscribe(() -> logger.info("Completed"));

        Flowable.just("foo", "bar", "baz")
                .filter(s -> s.startsWith("b"))
                .map(String::toUpperCase)
                .subscribe(logger::info);
    }
}

package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmitOrConsumeError {

    static Logger logger = LoggerFactory.getLogger(EmitOrConsumeError.class);

    public static void main(String[] args) {
        emitError();
        consumeError();
    }

    static void consumeError(){
        logger.info("\n\nStarting consumeError");
            Observable.just(1 / 0)
                    .subscribe(i -> logger.info("received: {}", i),
                            e -> logger.error("(consumeError) error {}", e));
            // note the error is never emitted to the error observer!
    }

    static void emitError(){
        logger.info("\n\nStarting emitError");
        Observable.fromCallable(() -> 1/0)
                .subscribe(i -> logger.info("received: {}", i),
                        e -> logger.error("(emitError) error {}", e.getMessage()));
        // error is emitted to the error observer!
    }
}

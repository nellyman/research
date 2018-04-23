package com.nbh.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactiveStream {

    public static void main(String[] args) {
        Flux.just("red", "white", "blue")
                .log()
                .map(String::toUpperCase)
                .limitRate(2)
                .subscribe(System.out::println);

        // batches at 2 so output-

        /*Mar 02, 2018 2:38:37 PM reactor.util.Loggers$JdkLogger info INFO: | request(2)
Mar 02, 2018 2:38:37 PM reactor.util.Loggers$JdkLogger info INFO: | onNext(red)
Mar 02, 2018 2:38:37 PM reactor.util.Loggers$JdkLogger info INFO: | onNext(white)
Mar 02, 2018 2:38:37 PM reactor.util.Loggers$JdkLogger info INFO: | request(2)
Mar 02, 2018 2:38:37 PM reactor.util.Loggers$JdkLogger info INFO: | onNext(blue)
Mar 02, 2018 2:38:37 PM reactor.util.Loggers$JdkLogger info INFO: | onComplete()*/

        System.out.println(" now threaded...");

        // now with 2 threads...
        Flux.just("green", "pink", "orange")
                .log()
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .limitRate(2)
                .subscribe(System.out::println);
    }
}

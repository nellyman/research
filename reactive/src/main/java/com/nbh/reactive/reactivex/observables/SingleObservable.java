package com.nbh.reactive.reactivex.observables;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * point - use Single.just(.. instead of Observable.just(...
 * plus - source.first will return a Single
 */
public class SingleObservable {

    static Logger logger = LoggerFactory.getLogger(SingleObservable.class);

    public static void main(String[] args) {
        singleAsLamda();
        singleAsVerbose();
        firstParameter();
    }

    static void firstParameter(){
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.first("default")
                .subscribe(s -> logger.info("onSuccess : {}", s));
        // the first of course is a single !
    }

    static void singleAsLamda(){
        Single.just("Hello")
                .map(String::length)
                .subscribe(i -> logger.info("Length is " + i),
                        Throwable::printStackTrace);
    }

    static void singleAsVerbose(){

        SingleObserver<Integer> mySingleObs =new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("onSubscribe: {}", d.toString());
            }

            @Override
            public void onSuccess(Integer integer) {
                logger.info("onSuccess: length {}", integer.toString());
            }

            @Override
            public void onError(Throwable e) {
                logger.info("onError: {}", e.getMessage());
            }
        };

        Single.just("Hello")
                .map(String::length)
                .subscribe(mySingleObs);
    }
}

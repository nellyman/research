package com.nbh.reactive.reactivex.operators;

import com.nbh.reactive.reactivex.observables.HotObserverableExample;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class AbstractSetup {

    static Logger LOGGER = LoggerFactory.getLogger(AbstractSetup.class);

    static Observable<String> STRING_OBSERVABLE = Observable.just("alpha", "beta", "Gamma", "Delta", "Epsilion");

    static Observable<Long> TIME_OBSERVABLE = Observable.interval(500, TimeUnit.MILLISECONDS);

    static Observable<Integer> COUNT_OBSERVABLE = Observable.range(1, 100);
}

package com.nbh.reactive.reactivex.operators;

import io.reactivex.observables.ConnectableObservable;

/**
 * An Observable can become a ConnectableObservable by using the publish
 * method.
 * It requires the Observable.connect method to make everything go!
 */
public class MixedFilters {

    public static void main(String[] args) {

        ConnectableObservable<String> observable = AbstractSetup.STRING_OBSERVABLE.publish();

        observable.subscribe(s-> AbstractSetup.LOGGER.info(" examining : {}", s));

        observable
                .firstElement()
                .subscribe(s-> AbstractSetup.LOGGER.info(" The first! : {}", s));

        observable
                .take(2)
                .subscribe(s-> AbstractSetup.LOGGER.info(" In the first 2! : {}", s));

        observable
                .filter(s -> s.length()<5)
                .subscribe(s -> AbstractSetup.LOGGER.info(" small length: {}", s));

        observable.filter(s -> s.length()>4)
                .subscribe(s->  AbstractSetup.LOGGER.info(" large length: {}", s));

        observable
                .lastElement()
                .subscribe(s->  AbstractSetup.LOGGER.info(" last one! : {}", s));

        observable.connect();
        /*        source.map(String::length)
                .filter(i-> i>=5)
                .subscribe(s -> System.out.println("Observer 1 received: " + s));

        source.subscribe(s -> System.out.println("Observer 2 received: " + s));*/
    }
}

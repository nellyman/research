package com.nbh.reactive.reactivex.operators;


import io.reactivex.Observable;

/**
 * Filter accepts a predicate from an STRING_OBSERVABLE. Provide a Lambda to
 * qualify each emission. A false will not allow the emission forward
 */
public class Filter {

    public static void main(String[] args) {

        AbstractSetup.STRING_OBSERVABLE
                .filter(s -> s.length() > 4)
                .subscribe(s -> System.out.println("s = " + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done")
                );
    }
}

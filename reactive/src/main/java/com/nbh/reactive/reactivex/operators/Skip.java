package com.nbh.reactive.reactivex.operators;

/**
 * Opposite of Take, it'll skip the specified emissions
 */
public class Skip {

    public static void main(String[] args) {

        AbstractSetup.COUNT_OBSERVABLE
                .skip(98)
                .subscribe(s -> System.out.println("s = " + s));
    }
}

package com.nbh.reactive.reactivex.operators;

/**
 * Filter that examines each Emission and decided based on value
 * whether to proceed or not. When one is encounted that fails
 * the test onComplete is called.
 */
public class TakeWhile {

    public static void main(String[] args) {
        AbstractSetup.COUNT_OBSERVABLE
                .takeWhile(i -> i < 5)
                .subscribe(s -> System.out.println("s = " + s));
    }
}

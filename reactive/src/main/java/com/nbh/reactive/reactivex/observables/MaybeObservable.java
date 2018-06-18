package com.nbh.reactive.reactivex.observables;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Point - Allows 0 or 1 emissions.
 * Maybe.just can be used to emit the single.
 * Maybe.empty will yield no emission
 */
public class MaybeObservable {

    public static void main(String[] args) {
        // an emission
        Maybe<Integer> presentSource = Maybe.just(100);

        presentSource.subscribe(
                s -> System.out.println("process recieved = " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Done ;-) "));

        // no emission...
        Maybe<Integer> emptySource = Maybe.empty();
        emptySource.subscribe(
                s -> System.out.println("process2 recieved = " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Done2 ;-) ")
        );

        Observable<String> nothingSource = Observable.empty();

        nothingSource.firstElement().subscribe(
          s -> System.out.println("s = " + s),
          Throwable::printStackTrace,
          () -> System.out.println("Done 3 ;-) ")
        );
    }
}

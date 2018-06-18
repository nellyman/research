package com.nbh.reactive.reactivex.observables;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * If your Observable is long running then check isDisposed is prudent..
 */
public class DisposableLongRunnerCheck {

    public static void main(String[] args) throws Exception{

        //does not work how I expect it !!
        Observable<Integer> source = Observable.create(observableEmitter ->{
            try{
                for (int i=0;i<100;i++){
                    while (!observableEmitter.isDisposed()){
                        observableEmitter.onNext(i);
                    }
                    if (observableEmitter.isDisposed()){
                        return;
                    }
                    observableEmitter.onComplete();
                }
            }catch (Throwable t){
                System.out.println("t = " + t);
                observableEmitter.onError(t);
            }
        });

        Disposable disposable2 = source.subscribe(l -> System.out.println("emitted " + l),
                Throwable::printStackTrace,
                () -> System.out.println("done"));

        Thread.sleep(5000);
        disposable2.dispose();
    }
}

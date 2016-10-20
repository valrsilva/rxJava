import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Created by valdeci on 07/09/2016.
 */
public class Main {

    public static void hello(String... names) {

        Observable<String> observableStrings = Observable.from(names);

        Scheduler sched = new Scheduler() {
            @Override
            public Worker createWorker() {
                return null;
            }
        };

        observableStrings.subscribeOn(sched);

        observableStrings.subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }
        });

        Observer<String> obs = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext");
            }
        };

        observableStrings.subscribe(obs);

        Observable<String> observableListStrings = Observable.from(Arrays.asList("v","a","l","d","e","c","i"));

        observableListStrings.subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println("Hi " + s + "!");
            }
        });

    }

    public static void main(String[] args) {

        Main.hello("oi", "valdeci", "joao", "nathalia");

        BiFunction<String, String, String> concatFunction = (s, r) -> {
            System.out.println(s + r);
            return s + r;
        };

        callConcatFunction("5","9", concatFunction);

    }

    public static void callConcatFunction(String a, String b, BiFunction<String, String, String> concatFunction){
        concatFunction.apply(a,b);
    }
}

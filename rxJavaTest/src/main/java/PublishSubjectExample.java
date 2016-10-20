import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

/**
 * Created by valdeci on 09/09/2016.
 */
public class PublishSubjectExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        Object signal = new Object();

        synchronized (signal){

            PublishSubject<String> pusblishSubject = PublishSubject.create();

            System.out.println("iniciando subscribe1......");

            pusblishSubject.doOnNext((x) -> {
                System.out.println("o pae");
            }).subscribe((letter) -> {
                System.out.println("subscriber 1 ->" + letter);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (letter.equals("c")) {
                    synchronized (signal) {
                        signal.notify();
                    }
                }
            });

            Observable.from(DataGenerator.generateAlphabet())
                .subscribeOn(Schedulers.computation())
                .subscribe((letter) -> {
                    pusblishSubject.onNext(letter);
                },
                (t) -> {
                    pusblishSubject.onError(t);
                },
                () -> {
                    System.out.println("subscriber 1 completed");
                    pusblishSubject.onCompleted();
                    synchronized (signal) {
                        signal.notify();
                    }
                });

            try {
                signal.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("iniciando subscribe2......");

            pusblishSubject.subscribe((letter) -> {
                System.out.println("subscriber 2 -> " + letter);
            },
            (t) -> {
                pusblishSubject.onError(t);
            },
            () -> {
                System.out.println("subscriber 2 completed");
            });

            try {
                signal.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

import rx.Observable;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class SubscriptionIOThreadExample {

    public static void main(String[] args) {

        Object waitMonitor = new Object();

        synchronized (waitMonitor){

            System.out.println("Example 1");
            System.out.println("Thread Name:" + Thread.currentThread().getName());

            List<Integer> intList = DataGenerator.generateFibonaciNumbers();
            Observable<Integer> observable = Observable.from(intList);

            observable
                    .observeOn(Schedulers.io())
                    .subscribe((i) -> {

                        System.out.println("\n[1]onNext Enter");
                        System.out.println("[2]Thread Name:" + Thread.currentThread().getName());
                        System.out.println("[3]Value:" + i);
                        System.out.println("[4]Thread Name:" + Thread.currentThread().getName());
                        System.out.println("[5]onNext Exit");

                    }, (t) -> {
                        t.printStackTrace();
                    }, () -> {
                        System.out.println("\nonCompleted");
                        synchronized (waitMonitor) {
                            waitMonitor.notify();
                        }
                    });

            try {
                waitMonitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

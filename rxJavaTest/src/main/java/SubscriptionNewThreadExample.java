import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class SubscriptionNewThreadExample {

    public static void main(String[] args) {

        Object waitMonitor = new Object();

        synchronized (waitMonitor){

            System.out.println("Example 1");
            System.out.println("Thread Name:" + Thread.currentThread().getName());

            List<Integer> intList = DataGenerator.generateFibonaciNumbers();
            Observable<Integer> observable = Observable.from(intList);

            observable
                    .subscribeOn(Schedulers.newThread())
                    .subscribe((i) -> {

                        System.out.println("onNext Enter");
                        System.out.println("Thread Name:" + Thread.currentThread().getName());
                        System.out.println(i);
                        System.out.println("Thread Name:" + Thread.currentThread().getName());
                        System.out.println("onNext Exit");

                    }, (t) -> {
                        t.printStackTrace();
                    }, () -> {
                        System.out.println("onCompleted");
                        synchronized (waitMonitor){
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

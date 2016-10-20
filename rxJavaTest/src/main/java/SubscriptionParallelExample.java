import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class SubscriptionParallelExample {

    public static void main(String[] args) {

        Object waitMonitor = new Object();

        synchronized (waitMonitor){

            System.out.println("Example 1");
            System.out.println("Thread Name:" + Thread.currentThread().getName());

            List<Integer> intList = DataGenerator.generateFibonaciNumbers();
            Observable<Integer> observable = Observable.from(intList);

            observable
                .filter((a) -> {
                    return a % 2 == 0;
                })
                .flatMap((item) -> {
                    return doStuff(item).subscribeOn(Schedulers.io());
                })
                .subscribe((x) -> {
                    System.out.println("subscribe...");
                }, (t) -> {
                    t.printStackTrace();
                }, () -> {
                    System.out.println("onCompleted");
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

    public static Observable<Integer> doStuff(Integer integer){
        return Observable.from(Arrays.asList(integer)).doOnNext((x) -> {

            System.out.println("Enter Thread Name:" + Thread.currentThread().getName());

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(x);
            System.out.println("Exist Thread Name:" + Thread.currentThread().getName());
        });
    }
}

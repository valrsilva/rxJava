import rx.Observable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by valdeci on 30/09/2016.
 */
public class MergeExample {

    private static long start = System.currentTimeMillis();

    public static Boolean isSlowTime() {
        return (System.currentTimeMillis() - start) % 30_000 >= 15_000;
    }

    public static void main(String[] args) {

        Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);

        Observable<Long> clock = Observable.merge(
                slow.filter(tick -> isSlowTime()),
                fast.filter(tick -> !isSlowTime())
        );

        clock.subscribe(tick-> System.out.println(new Date()));

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

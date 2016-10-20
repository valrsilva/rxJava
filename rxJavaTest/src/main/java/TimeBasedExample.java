import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by valdeci on 09/09/2016.
 */
public class TimeBasedExample {

    public static void main(String[] args) {

        TimeTicker ticker = new TimeTicker(10);
        ticker.start();

        ticker.toObservable().sample(1, TimeUnit.SECONDS).subscribe((item) -> {
            System.out.println(item);
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

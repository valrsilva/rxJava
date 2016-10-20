import java.util.concurrent.TimeUnit;

/**
 * Created by valdeci on 09/09/2016.
 */
public class TimeBasedTimeoutExample {

    public static void main(String[] args) {

        TimeTicker ticker = new TimeTicker(100);
        ticker.start();

        ticker.toObservable().timeout(3, TimeUnit.SECONDS).subscribe((item) -> {
            System.out.println(item);
        }, (exception) -> {
            exception.printStackTrace();
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ticker.pause();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

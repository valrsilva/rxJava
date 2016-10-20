/**
 * Created by valdeci on 09/09/2016.
 */
public class TimeConditionalTakeUntilExample {

    public static void main(String[] args) {

        GreekAlphabetTicker greekTikker = new GreekAlphabetTicker(50);
        TimeTicker timeTicker = new TimeTicker(3000);

        System.out.println("---------------- exemplo 1: ");

        greekTikker.toObservable()
                .takeUntil(timeTicker.toObservable())
                .subscribe((item) -> {
                    System.out.println(item);
                });

        greekTikker.start();
        timeTicker.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        greekTikker.stop();
        timeTicker.stop();

    }

}

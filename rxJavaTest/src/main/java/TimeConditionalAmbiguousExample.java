import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class TimeConditionalAmbiguousExample {

    public static void main(String[] args) {

        GreekAlphabetTicker greekTikker = new GreekAlphabetTicker(50);
        GreekAlphabetTicker englishTikker = new GreekAlphabetTicker(100);

        System.out.println("---------------- exemplo 1: ");

        Observable
                .amb(greekTikker.toObservable(), englishTikker.toObservable())
                .subscribe((item) -> {
                    System.out.println(item);
                });

        greekTikker.start();
        englishTikker.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        greekTikker.stop();
        englishTikker.stop();


    }

}

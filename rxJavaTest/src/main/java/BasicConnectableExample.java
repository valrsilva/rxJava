import rx.observables.ConnectableObservable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class BasicConnectableExample {

    public static void main(String[] args) {

        TimeTicker timeTicker = new TimeTicker(50);

        ConnectableObservable<Long> connectableObservable = timeTicker.toObservable()
                .publish();

        connectableObservable.subscribe((i) -> {
            System.out.println("tick: " + i + " - " + Thread.currentThread().getName());
        });

        timeTicker.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        connectableObservable.connect();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        timeTicker.stop();

    }

}

import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Created by valdeci on 09/09/2016.
 */
public class SlowParallelObserveOnConnectableExample {

    public static void main(String[] args) {

        TimeTicker timeTicker = new TimeTicker(50);

        ConnectableObservable<Long> connectableObservable = timeTicker.toObservable()
                .publish();

        connectableObservable.observeOn(Schedulers.computation())
            .subscribe((i) -> {
                System.out.println("tick1: " + i + " - " + Thread.currentThread().getName());
            });

        connectableObservable.observeOn(Schedulers.computation())
                .subscribe((i) -> {
                    System.out.println("tick2: " + i + " - " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println("starting tick ******************");
        timeTicker.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        System.out.println("connection observable ******************");
        connectableObservable.connect();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        System.out.println("stopping tick ******************");
        timeTicker.stop();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

    }

}

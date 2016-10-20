import rx.Observable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by valdeci on 09/09/2016.
 */
public class BufferExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        TimeTicker timeTicker = new TimeTicker(100);
        timeTicker.start();

        timeTicker.toObservable()
            .buffer(1, TimeUnit.SECONDS)
            .subscribe((list) -> {
                System.out.println("-------------------");
                int count = 1;
                int size = list.size();
                for(int i=0; i<size; i++){
                    System.out.println("->" + (count++) + " " + list.get(i));
                }
            }, (t) -> {
                t.printStackTrace();
            }, () -> {
                System.out.println("onCompleted");
            });


    }

}

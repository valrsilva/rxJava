import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by valdeci on 09/09/2016.
 */
public class FutureCreationExample {

    public static void main(String[] args) {

        Observable<List<Integer>> observableFutureList;

        FutureTask<List<Integer>> future = new FutureTask(() -> {
            return DataGenerator.generateFibonaciNumbers();
        });

        observableFutureList = Observable.from(future);

        Schedulers.computation().createWorker().schedule(() -> {
            future.run();
        });

        observableFutureList.subscribe((list) -> {
           list.forEach((i) ->{
               System.out.println(i);
           });
        });

    }
}

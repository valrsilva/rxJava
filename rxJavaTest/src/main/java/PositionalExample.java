import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class PositionalExample {

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.from(DataGenerator.generateFibonaciNumbers());

        observable
            .take(2)
            .subscribe((item) -> {
                System.out.println(item);
            });

        observable
                .last()
                .subscribe((item) -> {
                    System.out.println(item);
                });

        observable
                .first()
                .subscribe((item) -> {
                    System.out.println(item);
                });

        observable
                .takeLast(3)
                .subscribe((item) -> {
                    System.out.println(item);
                });

        Observable.empty().firstOrDefault("valor default").subscribe((item) -> System.out.println(item));
        Observable.empty().lastOrDefault("valor default").subscribe((item) -> System.out.println(item));
    }

}

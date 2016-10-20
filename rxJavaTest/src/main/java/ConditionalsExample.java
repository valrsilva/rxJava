import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by valdeci on 09/09/2016.
 */
public class ConditionalsExample {

    public static void main(String[] args) {

        System.out.println("---------------- exemplo 1: ");

        Observable
                .empty()
                .defaultIfEmpty("empty item")
                .subscribe((item) -> {
                    System.out.println(item);
                });

        System.out.println("---------------- exemplo 2: ");

        Observable.from(DataGenerator.generateAlphabet())
                .defaultIfEmpty("empty item")
                .first()
                .subscribe((item) -> {
                    System.out.println(item);
                });

        System.out.println("---------------- exemplo 3: ");

        Observable.from(DataGenerator.generateFibonaciNumbers())
                .skipWhile((i) -> {
                    return i < 8;
                })
                .subscribe((item) -> {
                    System.out.println(item);
                });

        System.out.println("---------------- exemplo 4: ");

        Observable.from(DataGenerator.generateFibonaciNumbers())
                .takeWhile((i) -> {
                    return i < 8;
                })
                .subscribe((item) -> {
                    System.out.println(item);
                });


    }

}

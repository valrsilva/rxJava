import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class PositionalDistinctExample {

    public static void main(String[] args) {

        Observable<String> observable = Observable.from(DataGenerator.generateAlphabetDuplicado());

        System.out.println("exemplo 1..............");

        observable
                .subscribe((item) -> {
                    System.out.println(item);
                });

        System.out.println("exemplo 2..............");

        observable
                .distinct()
                .subscribe((item) -> {
                    System.out.println(item);
                });
    }

}

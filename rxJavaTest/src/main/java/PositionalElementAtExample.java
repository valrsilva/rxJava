import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class PositionalElementAtExample {

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.from(DataGenerator.generateFibonaciNumbers());

        observable
                .elementAt(3)
                .subscribe((item) -> {
                    System.out.println(item);
                });

        observable
                .elementAtOrDefault(100, 99999)
                .subscribe((item) -> {
                    System.out.println(item);
                });
    }

}

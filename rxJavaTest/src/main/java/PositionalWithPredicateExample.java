import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class PositionalWithPredicateExample {

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.from(DataGenerator.generateFibonaciNumbers());

        observable
                .last((p) -> {
                    return p < 50;
                })
                .subscribe((item) -> {
                    System.out.println(item);
                });

        observable
                .first((p) -> {
                    return p > 0;
                })
                .subscribe((item) -> {
                    System.out.println(item);
                });
    }

}

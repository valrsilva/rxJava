import rx.Observable;

/**
 * Created by valdeci on 30/09/2016.
 */
public class ZipOperatorExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        Observable<String> observable = Observable.from(DataGenerator.generateWords());

        observable.flatMap(word -> Observable.from(word.split(""))).distinct()
                .zipWith(
                        Observable.from(DataGenerator.generateFibonaciNumbers())
                        , (string, count) -> String.format("%2d. %s", count, string))
                .subscribe((item) -> {
                    System.out.println(item.toString());
                }, (t) -> {
                    t.printStackTrace();
                }, () -> {
                    System.out.println("onCompleted");
                });

    }
}

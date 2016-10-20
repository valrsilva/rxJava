import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class MapExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        Observable<String> observable = Observable.from(DataGenerator.generateAlphabet());

        observable
            .map((item) -> {
                return item.toUpperCase();
            })
            .subscribe((item) -> {
                System.out.println(item);
            }, (t) -> {
                t.printStackTrace();
            }, () -> {
                System.out.println( "onCompleted");
            });

        System.out.println("inicio exemplo 2: ");

        observable
            .flatMap((item) -> {
                return Observable.from(new String[]{item.toUpperCase(), item.toLowerCase()});
            })
            .subscribe((item) -> {
                System.out.println(item);
            }, (t) -> {
                t.printStackTrace();
            }, () -> {
                System.out.println("onCompleted");
            });


    }

}

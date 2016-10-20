import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class ScanExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        Observable<String> observable = Observable.from(DataGenerator.generateAlphabet());

        observable
            .scan(new StringBuilder(), (buffer, nextValue) ->{
                return buffer.append(nextValue);
            })
            .subscribe((item) -> {
                System.out.println(item.toString());
            }, (t) -> {
                t.printStackTrace();
            }, () -> {
                System.out.println("onCompleted");
            });

        System.out.println("inicio exemplo 2: ");

        observable
            .scan(new StringBuilder(), (buffer, nextValue) -> {
                return buffer.append(nextValue);
            })
            .last()
            .subscribe((item) -> {
                System.out.println(item.toString());
            }, (t) -> {
                t.printStackTrace();
            }, () -> {
                System.out.println("onCompleted");
            });


    }

}

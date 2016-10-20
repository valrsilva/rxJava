import rx.Observable;

/**
 * Created by valdeci on 09/09/2016.
 */
public class SimpleCreationExamples {

    public static void main(String[] args) {

        Observable<Integer> observable = null;

        System.out.println("Exemplo 1...");
        observable = Observable.from(new Integer[]{1,2});
        observable.subscribe((i) -> {
            System.out.println(i);
        });

        System.out.println("Exemplo 2...");
        observable = Observable.from(DataGenerator.generateFibonaciNumbers());
        observable.subscribe((i) -> {
            System.out.println(i);
        });

    }
}

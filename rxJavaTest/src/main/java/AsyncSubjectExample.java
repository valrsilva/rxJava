import rx.Observable;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;

/**
 * Created by valdeci on 09/09/2016.
 */
public class AsyncSubjectExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe((letter) -> {
            System.out.println("->" + letter);
        });

        Observable.from(DataGenerator.generateAlphabet())
            .subscribe((letter) -> {
                asyncSubject.onNext(letter);
            },
            (t) -> {
                asyncSubject.onError(t);
            },
            () -> {
                System.out.println("onCompleted");
                asyncSubject.onCompleted();
            });

    }


}

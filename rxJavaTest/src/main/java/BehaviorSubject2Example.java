import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by valdeci on 09/09/2016.
 */
public class BehaviorSubject2Example {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("Start State");

        behaviorSubject.subscribe((letter) -> {
            System.out.println("->" + letter);
        });
        behaviorSubject.subscribe((letter) -> {
            System.out.println("--->" + letter);
        });

        Observable.from(DataGenerator.generateAlphabet())
            .subscribe((letter) -> {
                        behaviorSubject.onNext(letter);
                    },
                    (t) -> {
                        behaviorSubject.onError(t);
                    },
                    () -> {
                        System.out.println("onCompleted");
                        behaviorSubject.onCompleted();
                    });

    }


}

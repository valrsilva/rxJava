import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by valdeci on 09/09/2016.
 */
public class BehaviorSubjectExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("Start State");

        behaviorSubject.subscribe((letter) -> {
            System.out.println("->" + letter);
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

        behaviorSubject.subscribe((letter) ->{
            System.out.println("--->" + letter);
        }, (t) -> {
            behaviorSubject.onError(t);
        }, () -> {
            System.out.println("second onCompleted");
        });

    }


}

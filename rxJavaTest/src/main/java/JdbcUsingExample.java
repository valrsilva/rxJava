import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import sun.misc.Resource;

/**
 * Created by valdeci on 13/09/2016.
 */
public class JdbcUsingExample {

    public static void main(String[] args) {

        Action1<Throwable> simpleErrorHandler = (t) ->{
            t.printStackTrace();
        };

        TestDataBase.init();

        Func0<ConnectionSubscription> resourceFactory = () ->{
            return new ConnectionSubscription(TestDataBase.createConnection());
        };

        Func1<ConnectionSubscription, Observable<String>> greekAlphabetList = (connectionSubscription) -> {
            return TestDataBase.selectGreekAlphabet(connectionSubscription);
        };

        Observable<String> t = Observable.using(resourceFactory, greekAlphabetList, null);

        t.subscribe( (letter) -> {
            System.out.println(letter);
        }, simpleErrorHandler, () -> {
            System.out.println("onCompleted");
        });


    }
}

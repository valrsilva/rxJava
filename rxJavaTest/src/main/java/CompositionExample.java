import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class CompositionExample {

    public static void main(String[] args) {

        Object waitMonitor = new Object();

        synchronized (waitMonitor){

            UserService userService = new UserService();

            System.out.println("userList: [");

            Observable<User> observable = Observable.from(userService.getAllUsers());

            observable
                .flatMap((user) -> {
                    return doStuff(user).subscribeOn(Schedulers.io());
                })
                .toSortedList(new Func2<User, User, Integer>() {
                    @Override
                    public Integer call(User reservation, User reservation2) {
                        return reservation.getNome().compareTo(reservation2.getNome());
                    }
                })
                .subscribe((list) -> {

                    list.forEach((i) -> {
                        System.out.println(i.toString());
                    });

                }, (t) -> {
                    t.printStackTrace();
                }, () -> {
                    System.out.println( "]");
                    synchronized (waitMonitor) {
                        waitMonitor.notify();
                    }
                });

            try {
                waitMonitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static Observable<User> doStuff(User user){
        return Observable.just(user).filter((a) -> {
           return a.getSalary() > 10;
        });
    }
}

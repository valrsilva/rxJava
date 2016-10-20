import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valdeci on 09/09/2016.
 */
public class GroupByExample {

    public static void main(String[] args) {

        System.out.println("inicio exemplo 1: ");

        Observable<Integer> observable = Observable.from(DataGenerator.generateFibonaciNumbers());

        observable
            .groupBy((item) -> {
                return item % 2 == 0 ? "EVEN" : "ODD";
            })
            .subscribe((groupList) -> {
                System.out.println("key: " + groupList.getKey());
                groupList.subscribe((i) -> {
                    System.out.println("key: " + groupList.getKey() + " - " + i);
                });
            }, (t) -> {
                t.printStackTrace();
            }, () -> {
                System.out.println("onCompleted");
            });

        System.out.println("inicio exemplo 2: ");

        List<Integer> evenList = new ArrayList<>();
        List<Integer> oddList = new ArrayList<>();

        observable
                .groupBy((item) -> {
                    return item % 2 == 0 ? "EVEN" : "ODD";
                })
                .subscribe((groupList) -> {
                    System.out.println("key: " + groupList.getKey());
                    groupList.subscribe((i) -> {
                        if(groupList.getKey().equals("EVEN")){
                            evenList.add(i);
                        }else{
                            oddList.add(i);
                        }
                    });
                }, (t) -> {
                    t.printStackTrace();
                }, () -> {

                    evenList.forEach((x) -> System.out.println("even: " + x));
                    oddList.forEach((x) -> System.out.println("ODD: " + x));

                    System.out.println("onCompleted");
                });


    }

}

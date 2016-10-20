/**
 * Created by valdeci on 30/09/2016.
 */
public class AsyncEmitterExample {

    public static void main(String[] args) {
/*
        SomeFeed<PriceTick> feed = new SomeFeed<>();

        Observable<PriceTick> obs =
                Observable.from((AsyncSubject<PriceTick> emitter) ->
                {
                    SomeListener listener = new SomeListener() {
                        @Override
                        public void priceTick(PriceTick event) {
                            emitter.onNext(event);
                            if (event.isLast()) {
                                emitter.onCompleted();
                            }
                        }

                        @Override
                        public void error(Throwable e) {
                            emitter.onError(e);
                        }
                    };
                    feed.register(listener);
                }, AsyncEmitter.BackpressureMode.BUFFER);

        ConnectableObservable<PriceTick> hotObservable = obs.publish();
        hotObservable.connect();

        hotObservable.take(10).subscribe((priceTick) ->
                System.out.printf("1 %s %4s %6.2f%n", priceTick.getDate(),
                        priceTick.getInstrument(), priceTick.getPrice()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hotObservable.take(10).subscribe((priceTick) ->
                System.out.printf("2 %s %4s %6.2f%n", priceTick.getDate(),
                        priceTick.getInstrument(), priceTick.getPrice()));*/

    }

}

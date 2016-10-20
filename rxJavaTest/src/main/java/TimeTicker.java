import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by valdeci on 10/09/2016.
 */
public class TimeTicker {


    private final BehaviorSubject<Long> tickkerSubject;
    private final long inteval;
    private volatile boolean paused;
    private long lastTick;
    private Thread tickerThread;

    public TimeTicker(long interval){
        lastTick = System.currentTimeMillis();
        tickkerSubject = BehaviorSubject.create();
        tickerThread = null;
        paused = false;
        this.inteval = interval;
    }

    public Observable<Long> toObservable(){
        return tickkerSubject;
    }

    public synchronized void start(){

        if(tickerThread != null){
            return;
        }

        unpause();

        tickerThread = new Thread(() -> {
            try{
                while(!Thread.interrupted()){

                    try{
                        Thread.sleep(500);
                    }catch(Exception e){
                        break;
                    }

                    if(paused) continue;

                    long currentTime = System.currentTimeMillis();
                    if(currentTime - lastTick > inteval){
                        lastTick = currentTime;
                        tickkerSubject.onNext(lastTick);
                    }
                }
            }catch(Throwable t){
                tickkerSubject.onError(t);
            }

            tickkerSubject.onCompleted();

        }, "TickerThread");

        tickerThread.start();

    }

    public synchronized void pause(){
        paused = true;
    }
    public synchronized void unpause(){
        paused = false;
    }

    public synchronized void stop(){

        if(tickerThread == null){
            return;
        }
        tickerThread.interrupt();
        try {
            tickerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

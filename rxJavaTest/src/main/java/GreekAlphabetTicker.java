import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valdeci on 10/09/2016.
 */
public class GreekAlphabetTicker {


    private final BehaviorSubject<String> tickkerSubject;
    private final long inteval;
    private volatile boolean paused;
    private long lastTick;
    private Thread tickerThread;

    private List<String> listAlphabet;
    private int alphPosition = 0;

    public GreekAlphabetTicker(long interval){
        lastTick = System.currentTimeMillis();
        tickkerSubject = BehaviorSubject.create("");
        tickerThread = null;
        paused = false;
        this.inteval = interval;

        listAlphabet = new ArrayList<>();
        listAlphabet.add("ALPHA");
        listAlphabet.add("BETA");
        listAlphabet.add("GAMA");
        listAlphabet.add("TETTA");
        listAlphabet.add("ZETTA");
    }

    public Observable<String> toObservable(){
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

                    Thread.sleep(100);

                    if(paused) continue;

                    long currentTime = System.currentTimeMillis();
                    if(currentTime - lastTick > inteval){
                        lastTick = currentTime;
                        if(alphPosition > listAlphabet.size()-1){
                            alphPosition = 0;
                        }
                        tickkerSubject.onNext(listAlphabet.get(alphPosition));
                        alphPosition++;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.*;

public class Main {
    public static HashMap<Integer, Output> outputs = new HashMap<>();

    public static void main(String[] args) {
        long globalInit = System.currentTimeMillis();
        final int numThreads = Integer.parseInt(args[0]);

        List<Thread> threadList = new ArrayList<>(numThreads);

        for (int i = 0; i < numThreads; ++i) {
            threadList.add(i, new Thread(new OneThread(), Integer.toString(i)));
            threadList.get(i).start();
            Main.outputs.put(i, new Output(System.currentTimeMillis()));
        }

        //For each thread, wait until is ended.
        //Then, this foreach ends when every thread is ended.
        for (Thread t : threadList) {
            try {
                t.join();
                //join() is a function that waits until Thread t is finished.
                //Then, this loop will wait until each thread has ended.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
        //Printing each Thread timing
        for (int i = 0; i < numThreads; i++) {
            System.out.println("Thread " + i + " - "+outputs.get(i).toString());
        }
        */

        //System.out.println("Program of exercise 3 has terminated.");

        //Prints number of threads and total time needed (for statistics)
        System.out.println(args[0] + " " + (System.currentTimeMillis() - globalInit));
    }
}

//Custom Thread class implementing Runnable
class OneThread implements Runnable {

    public void run() { //Code going to be executed by the thread.
        long init = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            double d = tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789))))))))));
            cbrt(d);
        }

        long end = System.currentTimeMillis();

        //Store values into the object
        Main.outputs.get(Integer.parseInt(Thread.currentThread().getName())).setStarted(init);
        Main.outputs.get(Integer.parseInt(Thread.currentThread().getName())).setEnded(end);
    }
}

//Class used for storing each Thread timing
class Output {
    private long queued, started, ended;

    Output(long queued) {
        this.queued = queued;
        this.started = 0;
        this.ended = 0;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public void setEnded(long ended) {
        this.ended = ended;
    }

    @Override
    public String toString() {
        String info = "queued: " + queued + " - started: " + started + " - ended: " + ended;
        String results = "\n\tq-s: " + (started - queued) + " | s-e: " + (ended - started) + " | s-q: " + (ended - queued);
        return info + results;
    }
}

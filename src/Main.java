import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.*;

public class Main {
    public static HashMap<Integer, Output> outputs = new HashMap<>();
    public static int numThreads = 0;

    public static void main(String[] args) {
        long globalInit = System.currentTimeMillis();
        numThreads = Integer.parseInt(args[0]);

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

                long end = System.currentTimeMillis();
                Main.outputs.get(Integer.parseInt(t.getName())).setEnded(end);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Printing each Thread timing for evaluation
        for (int i = 0; i < numThreads; i++) {
            System.out.println("Thread " + i + " - " + outputs.get(i).toString());
        }

        //Prints number of threads and total time needed (for statistics)
        System.out.println("\n" + args[0] + " threads, total time: " + (System.currentTimeMillis() - globalInit));

        //Print final message
        System.out.println("\nProgram of exercise 3 has terminated.");
    }
}

//Custom Thread class implementing Runnable
class OneThread implements Runnable {

    public void run() { //Code going to be executed by the thread.
        //Store values into the object
        long init = System.currentTimeMillis();

        for (int i = 0; i < 1000000 / Main.numThreads; i++) {
            double d = tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789))))))))));
            cbrt(d);
        }

        Main.outputs.get(Integer.parseInt(Thread.currentThread().getName())).setStarted(init);
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.*;

public class Main {
    public static HashMap<Integer, Output> outputs = new HashMap<>(); //Global HashMap with the Output objects.
    public static int numThreads;

    public static void main(String[] args) {
        long globalInit = System.currentTimeMillis();
        numThreads = Integer.parseInt(args[0]);

        List<Thread> threadList = new ArrayList<>(numThreads); //ArrayList for the created Threads.

        //Create and start Threads. As much as selected by the argument value.
        for (int i = 0; i < numThreads; ++i) {
            threadList.add(i, new Thread(new OneThread(), Integer.toString(i)));
            Main.outputs.put(i, new Output(System.currentTimeMillis()));
            threadList.get(i).start();
        }

        for (Thread t : threadList) { //For each thread, wait until is ended.
            try {
                t.join(); //join() is a function that waits until Thread t is finished.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Then, this foreach ends when every thread is ended.

        //Printing each Thread timing for evaluation.
        for (int i = 0; i < numThreads; i++) {
            System.out.println("Thread " + i + " - " + outputs.get(i).toString());
        }

        //Prints number of threads and total time needed (for statistics).
        System.out.println("\n" + args[0] + " threads, total time: " + (System.currentTimeMillis() - globalInit));

        //Print final message
        System.out.println("\nProgram of exercise 3 has terminated.");
    }
}

//Custom Thread class implementing Runnable.
class OneThread implements Runnable {
    //Code going to be executed by the thread.
    public void run() {
        long init = System.currentTimeMillis(); //Start of execution.

        for (int i = 0; i < 1000000 / Main.numThreads; i++) {
            double d = tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789))))))))));
            cbrt(d);
        }

        //Store the init and end values into the object.
        Main.outputs.get(Integer.parseInt(Thread.currentThread().getName())).setStarted(init);
        long end = System.currentTimeMillis(); //End of execution.
        Main.outputs.get(Integer.parseInt(Thread.currentThread().getName())).setEnded(end);
    }
}

//Class used for storing each Thread timing.
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
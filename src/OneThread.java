public class OneThread implements Runnable {

    public void run() { //Code going to be executed by the thread.
        System.out.println("Hello world, I'm a java thread number " + Thread.currentThread().getName());
    }
}
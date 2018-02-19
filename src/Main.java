import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int numThreads = 32;

        List<Thread> threadList = new ArrayList<>(numThreads);

        for (int i = 0; i < numThreads; ++i) {
            threadList.add(i, new Thread(new OneThread(), Integer.toString(i)));
            threadList.get(i).start();
        }
        //Random print of the threads.

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
        1d. Al usar la función join, no hace falta un while que espere a que el mensaje sea false, como en el ejecicio
            anterior. Esta función ya propia de la clase Thread, espera hasta que el Thread finalice antes de devolverle
            la ejecución al Thread que la llamó.
            Por ello, el resultado visual es el mismo (el bucle espera a que acaben todos los hilos), y el mensaje final
            se mostrará una vez todos los hilos hayan acabado.

            De todos modos el orden en que se muestran los "Hello world" sigue siendo aleatorio, como debe de ser.
         */

        System.out.println("Program of exercise 4 has terminated."); //Printed as final message as it should.
    }
}

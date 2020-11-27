/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomicSumaDelArreglo;
import java.util.concurrent.CountDownLatch;//INVESTIGARRRRRRRRRRR!!!!!!!!!!!!!
/**
 *
 * @author jdominguez
 */
public class Test {

    public static void main(String[] args) {
        Arreglo a = new Arreglo();
        a.generar();
        HilosSuma h1 = new HilosSuma(a, 0, 10, 1);
        HilosSuma h2 = new HilosSuma(a, 11, 20, 2);
        HilosSuma h3 = new HilosSuma(a, 21, 30, 3);
        HilosSuma h4 = new HilosSuma(a, 31, 40, 4);
        HilosSuma h5 = new HilosSuma(a, 41, 49, 5);

        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        Thread t3 = new Thread(h3);
        Thread t4 = new Thread(h4);
        Thread t5 = new Thread(h5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();

        } catch (Exception e) {
        }
        System.out.println("Suma: " + a.getSuma().toString());
        System.out.println("Suma sin hilos: " + a.sumar().toString());
    }

}

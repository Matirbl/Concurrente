/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO2;

import FilosofosMesa.Mesa;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Filosofo implements Runnable {

    private Mesa miMesa;
    private Semaphore palilloIzq;
    private Semaphore palilloDer;

    public Filosofo(Semaphore a, Semaphore b) {
        palilloIzq = a;
        palilloDer = b;
    }

    public void setPalilloIzq(Semaphore a) {
        palilloIzq = a;

    }

    public void setPalilloDer(Semaphore b) {
        palilloDer = b;

    }

    public void comer() {

       
        boolean t1 = palilloIzq.tryAcquire();
        boolean t2 = palilloDer.tryAcquire();

        if (t1 && t2) {
            try {
                System.out.println("Filosofo: " + Thread.currentThread().getName() + " ESTA COMIENDO ");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
            palilloIzq.release();
            palilloDer.release();
        } else if (t1) {
            palilloIzq.release();

        } else if (t2) {
            palilloDer.release();
        }

    }

    public void pensar() {
        try {
            System.err.println("FILOSOFO: " + Thread.currentThread().getName() + " ESTA PENSANDO");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            comer();
            pensar();
        }

    }
}

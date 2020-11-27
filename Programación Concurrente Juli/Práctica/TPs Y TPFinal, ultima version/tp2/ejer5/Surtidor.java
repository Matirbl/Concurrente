/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer5;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class Surtidor {

    Semaphore mutex;

    public Surtidor() {
        mutex = new Semaphore(1, true);

    }

    public void recargar(Auto a) {

        try {
            System.err.println(a.getPatente() + " ARRIBÓ al surtidor");
            Thread.sleep(100);
            mutex.acquire();
            System.err.println(a.getPatente() + " ESTÁ recargargando");
            Thread.sleep(3500);
            a.setKmFaltantesParaElService((int) (Math.random() * 100));
            Thread.sleep(2500);
            a.incrementarRecarga();
            System.err.println(a.getPatente() + " RECARGÓ " + a.getKmFaltantesParaElService() +" y SALE del surtidor");

            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Surtidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaux;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class Taxi {
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore arrancar = new Semaphore(0);
    private Semaphore llegada = new Semaphore(0);

    public void subirse(String nombre) {
        try {
            mutex.acquire();
            System.out.println(nombre+" se subio al taxi");
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
        arrancar.release();
    }

    public void bajarse(String nombre) {
        try {
            llegada.acquire();
            System.out.println("El pasajero" +nombre+" llegó a su destino");
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
        mutex.release();
    }

    public void andar() {
        try {
            arrancar.acquire();
            System.out.println("El taxista va a llevar a un pasajero");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destino() {
        System.out.println("El taxista llegó");
        llegada.release();
    }

}

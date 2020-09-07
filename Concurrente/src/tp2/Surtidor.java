/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Surtidor {

    private int numero;
    private Semaphore sem;

    public Surtidor(int num) {
        numero = num;
        sem = new Semaphore(1);
    }

    public void cargarNafta(String pat) {

        try {
            sem.acquire();
            System.out.println("el auto con patente:" + pat + " empezo a cargar nafta");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Surtidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("el auto con patente:" + pat + " dejo de cargar nafta");
        sem.release();
    }
}

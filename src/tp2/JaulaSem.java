/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class JaulaSem {

    private Semaphore semComida;
    private Semaphore semRueda;

    public JaulaSem() {
        semComida = new Semaphore(3);
        semRueda = new Semaphore(1);
    }

    public void comenzarAcomer(String nombreH) {
        try {
            semComida.acquire();
            System.out.println("El hamster: " + nombreH + " ha COMENZADO a COMER");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JaulaSem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("El hamster: " + nombreH + " ha TERMINADO a COMER");
        semComida.release();

    }

    public void comenzarAjugar(String nombreH) {
        try {
            semRueda.acquire();
            System.out.println("El hamster: " + nombreH + " ha comenzado a JUGAR");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JaulaSem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("El hamster: " + nombreH + " ha TERMINADO a JUGAR");
        semRueda.release();

    }
}

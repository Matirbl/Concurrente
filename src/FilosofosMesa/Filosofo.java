/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilosofosMesa;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Filosofo extends Thread {

    private Mesa miMesa;
    private int lugar;

    public int getLugar() {
        return this.lugar;
    }

    public Filosofo(Mesa m, int n) {
        miMesa = m;
        lugar = n;

    }

    public void pensar() {
        try {
            System.out.println("Filosofo: "+Thread.currentThread().getName()+"ESTOY PENSANDO");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            miMesa.comer(this);
            pensar();
        }
    }
}

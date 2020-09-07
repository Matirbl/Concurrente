/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilosofosMesa;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mesa {

    private Semaphore palillos[];
    private int lugares;

    public Mesa(int n) {
        lugares = n;
        palillos = new Semaphore[lugares];
        for (int p = 0; p < palillos.length; p++) {
            palillos[p] = new Semaphore(1);

        }

    }

    public void comer(Filosofo filosofo) {
        Semaphore palilloIzq = palillos[filosofo.getLugar()];
        Semaphore palilloDer = palillos[(filosofo.getLugar() + 1) % lugares];

        boolean t1 = palilloIzq.tryAcquire();
        boolean t2 = palilloDer.tryAcquire();

        if (t1 && t2) {
            try {
                System.out.println("Filosofo: " + Thread.currentThread().getName() + " ESTA COMIENDO ");
                Thread.sleep(3000);
                System.out.println("Filosofo: " + Thread.currentThread().getName() + "TERMINA DE COMER");
            } catch (InterruptedException ex) {
                Logger.getLogger(TPO2.Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
            palilloIzq.release();
            palilloDer.release();
        } else if (t1) {
            palilloIzq.release();

        } else if (t2) {
            palilloDer.release();
        }

    }
}

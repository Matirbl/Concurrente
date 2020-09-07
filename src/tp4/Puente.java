/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Puente {

    public int cantNorte;
    public int cantSur;
    public int cantPuente;
    Semaphore norteAsur;
    Semaphore surAnorte;
    Semaphore mutex;

    public Puente() {
        cantNorte = 0;
        cantSur = 0;
        cantPuente = 0;
        norteAsur = new Semaphore(10);
        surAnorte = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void entrarNorte() {

        try {

            mutex.acquire();
            cantNorte++;
            mutex.release();
            norteAsur.acquire();
            mutex.acquire();
//            if (cantPuente < 10) {
            cantNorte--;
            cantPuente++;
            System.out.println("\u001B[33mEntra un vehiculo por el norte");

            //   }
            mutex.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salirSur() {
        try {
            mutex.acquire();
            cantPuente--;
            System.out.println("\u001B[33mSale un vehiculo por el sur " + cantPuente);

            if (cantPuente == 0) {
                if (cantSur > 0) {
                    surAnorte.release(cantSur);
                    System.out.println("SALEN DE SUR A NORTE");
                }
            } else if (cantNorte > 0) {
                norteAsur.release(cantNorte);
                System.out.println("SALEN DE NORTE A SUR");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
        mutex.release();
    }

    public void cruzando() {
        try {
            System.out.println(Thread.currentThread().getName() + " esta cruzando");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entrarSur() {

        try {

            mutex.acquire();
            cantSur++;
            mutex.release();
            surAnorte.acquire();
            mutex.acquire();
            //    if (cantPuente < 10) {
            cantSur--;
            cantPuente++;
            System.out.println("\u001B[31mEntra un vehiculo por el sur");

            //  }
            mutex.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salirNorte() {
        try {
            mutex.acquire();
            cantPuente--;
            System.out.println("\u001B[31mSale un vehiculo por el norte "+ cantPuente);

            if (cantPuente == 0) {
                if (cantNorte > 0) {
                    norteAsur.release(cantNorte);System.out.println("\u001B[30mSALEN DE NORTE A SUR");
                }
            } else if (cantSur > 0) {
                surAnorte.release(cantSur); System.out.println("\u001B[30mSALEN DE SUR A NORTE");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
        mutex.release();
    }

}

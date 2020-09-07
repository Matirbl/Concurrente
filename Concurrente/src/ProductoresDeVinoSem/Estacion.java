/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoresDeVinoSem;

import java.util.concurrent.Semaphore;

public class Estacion {

    public int jarras, levadura, jugo;
    public Semaphore mutex;
    public Semaphore reponer;
    public Semaphore estacion;
    public Semaphore tomarVino;
    public Semaphore hayIngredientes;
    public Semaphore decantacion;

    public Estacion() {
        mutex = new Semaphore(1);
        reponer = new Semaphore(0);
        estacion = new Semaphore(2);
        hayIngredientes = new Semaphore(0);
        decantacion = new Semaphore(7);
        jarras = 6;
        levadura = 20;
        jugo = 15;

    }

    public void entrarSala() throws InterruptedException {

        mutex.acquire();

        if (!(hayIngredientes())) {
            mutex.release();
            reponer.release();
            hayIngredientes.acquire();
        } else if (decantacion.tryAcquire()) {
            jarras = jarras - 2;

            levadura--;
            jugo = jugo - 2;
            mutex.release();
            estacion.acquire();
        } else {
            mutex.release();
            decantacion.acquire();
            jarras = jarras - 2;
            levadura--;
            jugo = jugo - 2;
            mutex.release();
            estacion.acquire();
        }
    }

    public void reponer() throws InterruptedException {
        reponer.acquire();
        levadura = 20;
        jugo = 15;
        hayIngredientes.release();
    }

    public boolean hayIngredientes() {
        return (jarras > 2 && levadura > 1 && jugo > 2);
    }

    public void entrarEstacion() throws InterruptedException {
        System.out.println("El sujeto mezcla");
        Thread.sleep(2000);
    }

    public void salirEstacion() throws InterruptedException {
        mutex.acquire();
        jarras++;
        mutex.release();
        estacion.release();
    }

    public void filtrarVino() throws InterruptedException {

        System.out.println("Decanto vino");
        Thread.sleep(2000);
    }

    public void probarVino() throws InterruptedException {
        System.out.println("Prueba los vinos");
    }
}

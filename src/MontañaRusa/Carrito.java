/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MontañaRusa;

import java.util.concurrent.Semaphore;

public class Carrito {

    private int capacidad, enCarrito, cantVueltas;
    private Semaphore arrancar;
    private Semaphore subirCarrito;
    private Semaphore bajarCarrito;

    public Carrito(int cap) {
        cantVueltas = 0;
        capacidad = cap;
        arrancar = new Semaphore(0);
        subirCarrito = new Semaphore(1, true);
        bajarCarrito = new Semaphore(0);

    }

    public void subirCarrito(Persona p) throws InterruptedException {
        subirCarrito.acquire();
        System.out.println("Se sube " + p.id + " cant en Carrito: " + enCarrito);
        enCarrito++;
        if (capacidad == enCarrito) {
            arrancar.release();
        } else {
            subirCarrito.release();
        }

    }

    public void arrancar() throws InterruptedException {
            arrancar.acquire();
            System.out.println("Arranca la vuelta");
            Thread.sleep(2000);
            System.out.println("Termina la vuelta");
            cantVueltas++;
            bajarCarrito.release();
    }

    public void bajarCarrito(Persona p) throws InterruptedException {
        bajarCarrito.acquire();
        System.out.println("Baja " + p.id);
        enCarrito--;
        if (enCarrito == 0) {
            subirCarrito.release();
        } else {
            bajarCarrito.release();
        }

    }
}

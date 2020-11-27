/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LosCani;

import java.util.concurrent.Semaphore;

/**
 *
 * @author 38493269
 */
public class Olla {
    
    /*
    Julián Domínguez 
    FAI 974
    */
    /*
     recurso compartido entre el cocinero y los canibales
     metodología de sincronización: SEMÁFOROS DE JAVA
     
     */
    private Semaphore lleno;//semaforo genérico que representa la cantidad de raciones, se inicializa en n
    private Semaphore mutex;//semaforo que garantiza la exclusion mutua sobre la olla
    private Semaphore vacio;//se inicializa en en 0 y cuando un canibal termina de comer le suma un periso hasta llegar a n; cuando llega a n el cocinero puede cocinar
    private int cantidadDeRaciones;

    public Olla(int cantidadDeRaciones) {
        this.cantidadDeRaciones = cantidadDeRaciones;
        this.lleno = new Semaphore(0, true);//los canibales son caniables educados y por lo tanto esperar su turno ordenadamente para comer
        this.mutex = new Semaphore(1);//cuando un canibal agarra la olla, solo ese canibal va a comer, por lo tanto necesito garantizar la exclusion mutua sobre la olla
        this.vacio = new Semaphore(cantidadDeRaciones);
    }

    public void comer(int id) {

        try {
            System.out.println("El canibal " + id + " quiere comer.");
            lleno.acquire();
            System.out.println("El canibal " + id + " espera su turno para servirse.");
            mutex.acquire();
            System.out.println("El canibal " + id + " se está sirviendo.");
            Thread.sleep(7000);
            System.out.println("El canibal " + id + " terminó de servirse y soltó la olla.");
            mutex.release();
            vacio.release();
            System.out.println("El canibal " + id + " está comiendo.");
            Thread.sleep(90000);
            System.out.println("El canibal " + id + " terminó de comer.");
        } catch (InterruptedException e) {
        }
    }

    public void cocinar(String nombre) {
        try {
            System.out.println("El chef " + nombre + " quiere cocinar.");
            vacio.acquire(cantidadDeRaciones);
            mutex.acquire();
            System.out.println("El chef " + nombre + " está cocinando.");
            Thread.sleep(4000);
            System.out.println("El chef " + nombre + " terminó de cocinar.");
            mutex.release();
            lleno.release(cantidadDeRaciones);

        } catch (InterruptedException e) {
        }

    }

//public void
}

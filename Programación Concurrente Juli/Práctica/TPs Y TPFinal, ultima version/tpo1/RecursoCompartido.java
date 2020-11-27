/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class RecursoCompartido {
    /*
     clase emcargada de la sincronizacion entre los hilos
    
     tendra un atributo tipo int valor donde accederan los hilos
    
     la sincronizacion se realizara a traves de semaforos
     */

    private int valor;
    private Semaphore mutex;

    public RecursoCompartido() {
        valor = 3;
        mutex = new Semaphore(1,true);
    }

    public void incrementar() {

        try {
            mutex.acquire();

            System.out.println("el hilo A recibio: " + valor);
            Thread.sleep(2500);
            valor++;
            System.err.println("el hilo A lo dejo en: " + valor);
            Thread.sleep(2500);

            mutex.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(RecursoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void multiplicar() {

        try {
            mutex.acquire();

            System.out.println("el hilo B recibio: " + valor);
            Thread.sleep(2500);
            valor = valor * 2;
            System.err.println("el hilo B lo dejo en: " + valor);
            Thread.sleep(2500);

            mutex.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(RecursoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

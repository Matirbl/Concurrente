/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberoDormilonSem;

import BarberoDormilon.*;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Chino
 */
public class Barberia {
//hola, esta es una prueba

    private Semaphore sillasDisponibles;
    private Semaphore sillon;
    private Semaphore barbero;
    private Semaphore irse;

    public Barberia() {
        sillasDisponibles = new Semaphore(4);
        sillon = new Semaphore(1);
        barbero = new Semaphore(0);
        irse = new Semaphore(0);
    }

    public boolean entrar() throws InterruptedException {

        return sillasDisponibles.tryAcquire();

    }

    public void SentarseEnSillon(String nombre) throws InterruptedException {
        sillon.acquire();
        sillasDisponibles.release();
        System.out.println("\u001B[32m El cliente: " + nombre + " se sienta en el sillon");
        barbero.release();
    }

    public void irseBarberia(String nombre) throws InterruptedException {
        irse.acquire();
        System.out.println("\u001B[31m El cliente: " + nombre + " se va y libera el sillon");
        sillon.release();
    }

    public void afeitarCliente() throws InterruptedException {
        barbero.acquire();
        System.out.println("\u001B[33m Llega un cliente, el barbero se despierta");
        Thread.sleep(2000);
        System.out.println("\u001B[33m El barbero termina de afeitar");
        irse.release();
    }

}

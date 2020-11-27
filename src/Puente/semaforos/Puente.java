/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.semaforos;

import java.util.concurrent.Semaphore;

public class Puente {

    private Semaphore mutex, cruzaNorte, cruzaSur;
    private int cruzando, esperando, yaCruzaron, cantMax;
    private boolean sentido;

    public Puente() {
        mutex = new Semaphore(1);
        sentido = true;
        esperando = 0;
        cruzando = 0;
        yaCruzaron = 0;
        cantMax = 10;
    }

    public void cruzarNorte(String patente) throws InterruptedException {
        mutex.acquire();

        if (yaCruzaron <= 10 && !sentido || !sentido && esperando == 0) {
            System.out.println("El auto con patente " + patente + " pudo cruzar desde norte");
            cruzando++;
        } else {
            System.out.println("El auto con patente " + patente + " espera para cruzar desde norte");
            esperando++;
        }
        mutex.release();
    }

    public void salirNorte(String patente) throws InterruptedException {
        mutex.acquire();
        yaCruzaron++;
        cruzando--;
        System.out.println("El auto con patente " + patente + " sale del puente ");

        if (yaCruzaron == cantMax) {
            sentido = !sentido;
            yaCruzaron = 0;
            System.out.println("SE CAMBIA SENTIDO");
        }
        mutex.release();
    }

    public void cruzarSur(String patente) throws InterruptedException {
        mutex.acquire();

        if (yaCruzaron <= 10 && sentido || sentido && esperando == 0) {
            System.out.println("El auto con patente " + patente + " pudo cruzar desde sur");
            cruzando++;
        } else {
            System.out.println("El auto con patente " + patente + " espera para cruzar desde sur");
            esperando++;
        }
        mutex.release();
    }

    public void salirSur(String patente) throws InterruptedException {
        mutex.acquire();
        yaCruzaron++;
        cruzando--;
        System.out.println("El auto con patente " + patente + " sale del puente ");

        if (yaCruzaron == cantMax) {
            sentido = !sentido;
            yaCruzaron = 0;
            System.out.println("SE CAMBIA SENTIDO");
        }
        mutex.release();
    }

}

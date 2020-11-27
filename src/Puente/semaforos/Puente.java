/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.semaforos;

import java.util.concurrent.Semaphore;

public class Puente {

    private Semaphore mutex, norte, sur;
    private int esperandoNorte, esperandoSur, cantMax, yaCruzaron, cruzando;
    private boolean sentido;

    public Puente() {
        cantMax = 10;
        mutex = new Semaphore(1);
        norte = new Semaphore(cantMax);
        sur = new Semaphore(cantMax);
        esperandoNorte = 0;
        esperandoSur = 0;
        sentido = true;
        cruzando = 0;
    }

    public void cruzarNorte(String patente) throws InterruptedException {
        mutex.acquire();
        if (norte.tryAcquire() && !sentido) {
            System.out.println("El auto con patente " + patente + " entra desde norte " + " esperan " + esperandoSur + " autos de sur");
            cruzando++;
            if (esperandoNorte > 0) {
                esperandoNorte--;
            }
        } else {
            System.out.println("El auto con patente " + patente + " no puede cruzar espera para cruzar desde norte");
            esperandoNorte++;
        }
        mutex.release();
    }

    public void salirNorte(String patente) throws InterruptedException {
        mutex.acquire();
        yaCruzaron++;
        cruzando--;
        System.out.println("El auto con patente " + patente + " sale del puente ");

        if (yaCruzaron == cantMax) {
            System.out.println("Cruzaron " + cantMax + " desde norte y cambia sentido");
            yaCruzaron = 0;
            sentido = !sentido;
        }
        mutex.release();

    }

    public void cruzando(String patente) throws InterruptedException {
        System.out.println("El auto con patente " + patente + " está cruzando");
        Thread.sleep(2000);
    }

    public void cruzarSur(String patente) throws InterruptedException {
        mutex.acquire();
        if (sur.tryAcquire() && !sentido) {
            System.out.println("El auto con patente " + patente + " entra desde norte " + " esperan " + esperandoNorte + " autos de norte");
            cruzando++;
            if (esperandoSur > 0) {
                esperandoSur--;
            }
        } else {
            System.out.println("El auto con patente " + patente + " espera para cruzar desde sur");
            esperandoNorte++;
        }
        mutex.release();
    }

    public void salirSur(String patente) throws InterruptedException {
        mutex.acquire();
        yaCruzaron++;
        cruzando--;
        System.out.println("El auto con patente " + patente + " sale del puente ");

        if (yaCruzaron == cantMax) {
            System.out.println("Cruzaron " + cantMax + " desde sur");
            yaCruzaron = 0;
            sentido = !sentido;
        }
        mutex.release();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaHemoterapia;

import java.util.concurrent.Semaphore;

public class Sala {

    protected Semaphore camillas;
    protected Semaphore mutex;
    protected Semaphore revistaSem;
    protected Semaphore sillasDisp;

    public Sala() {
        camillas = new Semaphore(4, true);
        revistaSem = new Semaphore(9);
        mutex = new Semaphore(1);
        sillasDisp = new Semaphore(12);

    }

    public void entrarSala(Paciente p) throws InterruptedException {
        mutex.acquire();

        if (camillas.tryAcquire()) {
            mutex.release();
        } else {
            mutex.release();
            esperar(p.id);
        }

    }

    public void salirSala(Paciente p) throws InterruptedException {

        System.out.println("El hilo: " + p.id + " Se va y deja una camilla libre");
        camillas.release();

    }

    public void esperar(int i) throws InterruptedException {

        if (sillasDisp.tryAcquire()) {
            if (revistaSem.tryAcquire()) {
                System.out.println("El paciente " + i + " esta sentado y tomo la revista ");
                camillas.acquire();
                System.out.println("El paciente " + i + " devuelve la silla y la revista ");
                revistaSem.release();
                sillasDisp.release();
            } else {
                System.out.println("El paciente " + i + " esta sentado y mira la tv");

                camillas.acquire();
                System.out.println("El paciente " + i + " devuelve la silla");
                sillasDisp.release();
            }
        } else if (revistaSem.tryAcquire()) {
            System.out.println("El paciente " + i + " esta parado y tomo la revista ");
            camillas.acquire();
            System.out.println("El paciente " + i + " devuelve la revista ");
            revistaSem.release();
        } else {
            System.out.println("El paciente " + i + " esta parado y mira la tv");
            camillas.acquire();

        }
    }

    public void donar(Paciente p) throws InterruptedException {
        System.out.println("El hilo: " + p.id + " empieza a donar");
        Thread.sleep(2000);
    }

}

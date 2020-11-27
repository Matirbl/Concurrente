/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial;

import java.util.concurrent.Semaphore;

public class Pista {

    protected Semaphore pista, despegar, aterrizar;
    protected int quiereAterrizar, quiereDespegar, aterrizaron;

    public Pista() {
        pista = new Semaphore(1);
        despegar = new Semaphore(1);
        aterrizar = new Semaphore(0);
        quiereAterrizar = 0;
        quiereDespegar = 0;
        aterrizaron = 0;
    }

    public void aterrizar() throws InterruptedException {
        pista.acquire();
        quiereAterrizar++;
        pista.release();

        aterrizar.acquire();

        pista.acquire();
        quiereAterrizar--;
        pista.release();
    }

    public void despegar() throws InterruptedException {
        pista.acquire();
        quiereDespegar++;
        pista.release();

        despegar.acquire();

        pista.acquire();
        quiereDespegar--;
        pista.release();
    }

    public void aterrizando() throws InterruptedException {
        System.out.println("Un avion esta aterrizando");
        System.out.println("QUIEREN DESPEGAR: " + quiereDespegar + " QUIEREN ATERRIZAR: " + quiereAterrizar);
        Thread.sleep(100);
    }

    public void despegando() throws InterruptedException {
        System.out.println("Un avion esta despegando");
        System.out.println("QUIEREN DESPEGAR: " + quiereDespegar + " QUIEREN ATERRIZAR: " + quiereAterrizar);
        Thread.sleep(100);
    }

    public void terminarDeAterrizar() throws InterruptedException {
        pista.acquire();
        if (quiereAterrizar > 0) {
            aterrizar.release();

        } else if (quiereDespegar > 0) {
            despegar.release();
        }
        pista.release();
    }

    public void terminarDeDespegar() throws InterruptedException {
        pista.acquire();
        if (quiereAterrizar > 0) {
            aterrizar.release();
        } else if (quiereDespegar > 0) {
            despegar.release();
        }
        pista.release();
    }

    public void terminarDeAterrizar2() throws InterruptedException {
        pista.acquire();
        if (quiereAterrizar > 0 && (aterrizaron < 10 || quiereDespegar == 0 )){
            aterrizaron++;
            aterrizar.release();
        } else if (quiereDespegar > 0) {
            despegar.release();
            aterrizaron = 0;

        }
        pista.release();
    }

}

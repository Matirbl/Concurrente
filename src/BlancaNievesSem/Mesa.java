/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNievesSem;

import java.util.concurrent.Semaphore;

public class Mesa {

    private Semaphore sillas, servir, comer; //mutex
    private int enMesa;

    public Mesa() {
        sillas = new Semaphore(4);
        servir = new Semaphore(0);
        comer = new Semaphore(0);
        //  enMesa = 0;
    }

    public void sentarse(String id) throws InterruptedException {
        sillas.acquire();
        // mutex.acquire();
        //enMesa++;
        //mutex.release();
        System.out.println("El enano " + id + " se sienta en la silla");
        servir.release();
        comer.acquire();
    }

    public boolean servir() throws InterruptedException {
        boolean res;
        if (servir.tryAcquire()) {
            System.out.println("Blanca nieves sirve un plato en la mesa");
            comer.release();
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public void pasear() throws InterruptedException {
        System.out.println("Blanca nieves pasea con el principe");
        Thread.sleep(2000);
    }

    public void comiendo(String id) throws InterruptedException {
        System.out.println("El enano " + id + " come ");
        Thread.sleep(2000);
    }

    public void irATrabajar(String id) {
        sillas.release();
        System.out.println("El enano " + id + " termina de comer");
    }

    public void trabajando(String id) throws InterruptedException {
        System.out.println("El enano " + id + " esta trabajando");
        Thread.sleep(4000);
    }
}

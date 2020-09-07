/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CenaConMirta;

import java.util.concurrent.Semaphore;

public class Mesa {

    protected int invitados, mozos, lugaresEnMesa;
    private Semaphore mirtaSentarse;
    private Semaphore mozoServirComidaMirta;
    private Semaphore preguntaPolemica;
    private Semaphore mozoServirComidaInvitado;
    private Semaphore invitadoSentarse;
    private Semaphore mirtaComer;
    private Semaphore respuestaPolemica;
    private Semaphore invitadoComer;
    private boolean puedeResponder;
    protected boolean yaSeRespondio;

    public Mesa(int lugares) {
        invitados = 0;
        lugaresEnMesa = lugares;
        mozos = lugares / 2;
        puedeResponder = false;
        mirtaSentarse = new Semaphore(0);
        mozoServirComidaMirta = new Semaphore(0);
        mirtaComer = new Semaphore(0);
        preguntaPolemica = new Semaphore(0);
        respuestaPolemica = new Semaphore(0);
        mozoServirComidaInvitado = new Semaphore(0);
        invitadoComer = new Semaphore(0);
        invitadoSentarse = new Semaphore(1);
        yaSeRespondio = false;

    }

    public boolean soyUltimo() {

        return (lugaresEnMesa == invitados);

    }

    public void sentarse(int i) throws InterruptedException {

        invitadoSentarse.acquire();
        System.out.println("invitado " + i + " se sienta");
        invitados++;
        if (soyUltimo()) {
            mirtaSentarse.release();
        } else {
            invitadoSentarse.release();
        }
    }

    public void sentarseMirta() throws InterruptedException {
        mirtaSentarse.acquire();
        System.out.println("Mirta se sienta");
        Thread.sleep(250);
        mozoServirComidaInvitado.release(invitados);
        mozoServirComidaMirta.release();
    }

    public void servirComida() throws InterruptedException {
        if (mozoServirComidaMirta.tryAcquire()) {
            System.out.println("El mozo sirve comida a Mirta");
            Thread.sleep(500);
            mirtaComer.release();
        } else {
            mozoServirComidaInvitado.acquire();
            System.out.println("El mozo sirve comida para un invitado");
            Thread.sleep(500);
            invitadoComer.release();
        }

    }

    public void comerMirta() throws InterruptedException {
        mirtaComer.acquire();
        System.out.println("Mirta empiza a comer");
        Thread.sleep(2000);
        System.out.println("Mirta termina de comer");
        preguntaPolemica.release();
    }

    public void comerInvitado(int i) throws InterruptedException {
        invitadoComer.acquire();
    }

    public void comiendo(int i) throws InterruptedException {
        System.out.println("El invitado " + i + " esta comiendo");
        Thread.sleep(2000);
    }

    public void terminarDeComer(int i) {
        System.out.println("El invitado " + i + " termina de comer");
        puedeResponder = true;

    }

    public void preguntaPolemica() throws InterruptedException {
        preguntaPolemica.acquire();
        System.out.println("Mirta hace una pregunta Polemica");
        Thread.sleep(1000);
        respuestaPolemica.release();
    }

    public void respuestaPolemica(int i) throws InterruptedException {

        respuestaPolemica.acquire();
        if (puedeResponder && !yaSeRespondio) {
            System.out.println("El invitado " + i + " responde la pregunta de Mirta");
            yaSeRespondio = true;
        } else {
            System.out.println("El invitado " + i + " escucha atento");
        }
        respuestaPolemica.release();

    }

}

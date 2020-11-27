/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class MesaRedonda {

    private Tenedor[] cubiertos;

    public MesaRedonda() {
        cubiertos = new Tenedor[5];
        for (int i = 0; i < cubiertos.length; i++) {
            cubiertos[i] = new Tenedor();
        }

    }

    public boolean comer(int posFilosofo, boolean turnoDelFiloso) {
        boolean comi = false; //retorna si comio o no
        try {

            System.out.println("El filosofo: " + posFilosofo + " desea comer");
            Thread.sleep(1250);
            if (turnoDelFiloso) {

                cubiertos[posFilosofo].getTenedor().acquire();
                System.out.println("El filosofo: " + posFilosofo + " tomo el tenedor de su izq");
                Thread.sleep(1250);

                cubiertos[(posFilosofo + 1) % cubiertos.length].getTenedor().acquire();
                System.out.println("El filosofo: " + posFilosofo + " tomo el tenedor de su der");
                Thread.sleep(1250);
                System.err.println("El filosofo: " + posFilosofo + " ESTÁ COMIENDO");
                Thread.sleep(4000);
                comi = true;//si ya comi entonces ya no es mi turno en la siguiente "ronda"
                System.err.println("El filosofo: " + posFilosofo + " terminó de comer y deja los cubiertos");
                Thread.sleep(1250);
                /*
                libero los tenedores una vez que termine de comer
                */
                cubiertos[posFilosofo].getTenedor().release();
                cubiertos[(posFilosofo + 1) % cubiertos.length].getTenedor().release();
            } else {

                System.err.println("El filosofo: " + posFilosofo + " no pudo comer porque no era su turno");
                Thread.sleep(2250);
            }

        } catch (Exception ex) {
            Logger.getLogger(MesaRedonda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return comi;
    }

}

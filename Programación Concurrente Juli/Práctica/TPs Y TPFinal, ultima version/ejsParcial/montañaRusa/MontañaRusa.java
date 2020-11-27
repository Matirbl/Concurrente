/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.montañaRusa;
/*
PREGUNTARRR
    si el sem mutex en andar es necesario
*/

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class MontañaRusa {

    private Semaphore lugaresDisponibles;//requisito para la fila de pasajeros
    private Semaphore iniciar;//requisito para q el carrito arranque
    private Semaphore mutex;//requisito para generar consistencia
    private Semaphore bajar;//requisito para que el pasajero pueda bajar
    private int capacidad;

    public MontañaRusa(int capacidad) {
        this.capacidad = capacidad;
        lugaresDisponibles = new Semaphore(capacidad, true);
        iniciar = new Semaphore(0);
        bajar = new Semaphore(0);
        mutex = new Semaphore(1, true);
    }

    public void subir(int idPs) {
        try {
            System.out.println(idPs + " desea subirse");
            Thread.sleep(1250 * intAleatorio(2, 3));
            lugaresDisponibles.acquire();
            mutex.acquire();

            System.err.println(idPs + " se subio");
            Thread.sleep(1000 * intAleatorio(1, 2));
            mutex.release();
            iniciar.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(MontañaRusa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void bajar(int idPs) {
        try {
            bajar.acquire();
            mutex.acquire();
            System.err.println(idPs + " se bajó");
            Thread.sleep(1000 * intAleatorio(1, 2));
            mutex.release();
            lugaresDisponibles.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(MontañaRusa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void andar() {
        try {

            iniciar.acquire(capacidad);
            System.err.println("carro listo");
            Thread.sleep(1000 * intAleatorio(2, 3));
            System.out.println("inició su recorrido");
            Thread.sleep(1000 * intAleatorio(3, 4));

            mutex.acquire();
            System.out.println("finalizó, decienden pasajeros");
            bajar.release(capacidad);
            Thread.sleep(1000 * intAleatorio(2, 5));
            mutex.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(MontañaRusa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

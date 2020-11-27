/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ejer6;

import static utiles.Aleatorio.nombreAnimalAleatorio;

/**
 *
 */
public class Test {

    public static void main(String[] args) {

        Mesa mesita = new Mesa();

        Thread[] hilos = new Thread[4];
        Agente a = new Agente(nombreAnimalAleatorio(), mesita);
        hilos[0] = new Thread(a);

        for (int i = 1; i < hilos.length; i++) {
            switch (i) {
                case 1:
                    hilos[i] = new Thread(new Fumador(nombreAnimalAleatorio(), mesita, 'p'));
                    break;
                case 2:
                    hilos[i] = new Thread(new Fumador(nombreAnimalAleatorio(), mesita, 't'));
                    break;
                case 3:
                    hilos[i] = new Thread(new Fumador(nombreAnimalAleatorio(), mesita, 'f'));
                    break;
                default:
                    throw new AssertionError();
            }
        }
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
    }

}

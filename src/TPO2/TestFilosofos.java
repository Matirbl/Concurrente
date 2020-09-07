/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Chino
 */
public class TestFilosofos {

    public static void main(String[] args) {

        int cantidad = 5;

        Semaphore palillos[] = new Semaphore[cantidad];
        Thread filosofos[] = new Thread[palillos.length];

        for (int t = 0; t < palillos.length; t++) {
            palillos[t] = new Semaphore(1);

        }

        for (int i = 0; i < filosofos.length; i++) {
            filosofos[i] = new Thread(new Filosofo(palillos[i], palillos[(i + 1) % palillos.length]), i + "");

        }
        for (int j = 0; j < filosofos.length; j++) {
            filosofos[j].start();

        }

    }
}

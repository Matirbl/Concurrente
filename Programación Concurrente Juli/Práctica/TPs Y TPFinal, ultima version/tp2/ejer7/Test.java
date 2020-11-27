/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejer7;

/**
 *
 */
public class Test {

    public static void main(String[] args) {

        Jaula j1 = new Jaula();

        Hamster[] hams = new Hamster[3];
        Thread hilo;
        for (int i = 0; i < hams.length; i++) {
            hams[i] = new Hamster("hams " + (i + 1), j1);

            hilo = new Thread(hams[i]);
            hilo.start();

        }

    }
}

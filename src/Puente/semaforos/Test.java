/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.semaforos;

/**
 *
 * @author Chino
 */
public class Test {

    public static void main(String[] args) {
        Puente puente = new Puente();
        int cant1 = 20;

        Thread[] coche1 = new Thread[cant1];
        for (int i = 0; i < coche1.length; i++) {
            coche1[i] = new Thread(new CocheSem((i % 2), "id - " + i, puente));
        }

        for (int i = 0; i < coche1.length; i++) {
            coche1[i].start();
        }

    }
}

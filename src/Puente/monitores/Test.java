/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.monitores;

public class Test {
    public static void main(String[] args) {
        int cant = 7;
        Coche[] coches1 = new Coche[cant];
        Coche[] coches2 = new Coche[cant];
        int maxCochesPasando = 5;
        Puente puente = new Puente(maxCochesPasando);

        for (int i = 0; i < coches1.length; i++) {
            coches1[i] = new Coche(puente, true, i);
        }

        for (int i = 0; i < coches1.length; i++) {
            coches2[i] = new Coche(puente, false, i + cant);
        }

        for (int i = 0; i < coches1.length; i++) {
            coches1[i].start();
        }

        for (int i = 0; i < coches1.length; i++) {
            coches2[i].start();
        }
        try {
            for (int i = 0; i < coches1.length; i++) {
                coches1[i].join();
            }

            for (int i = 0; i < coches1.length; i++) {
                coches2[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

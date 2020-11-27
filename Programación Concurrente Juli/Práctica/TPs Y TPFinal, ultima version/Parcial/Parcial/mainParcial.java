/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial;

public class mainParcial {

    public static void main(String[] args) {
        Pista unaPista = new Pista();
        Thread aviones[] = new Thread[100];

        for (int i = 0; i < aviones.length; i++) {
            aviones[i] = new Thread(new Avion(i % 2, unaPista));

        }
        for (int j = 0; j < aviones.length; j++) {
            Thread avione = aviones[j];
            aviones[j].start();

        }

    }
}

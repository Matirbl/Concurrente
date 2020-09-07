/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

public class main {

    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento(10);
        Balsa unaBalsa = new Balsa(estacionamiento);
        Thread hiloBalsa = new Thread(unaBalsa);
        hiloBalsa.start();

        for (int i = 0; i < 20; i++) {
            Auto auto = new Auto(estacionamiento);
            Thread hilo = new Thread(auto, "auto " + i);
            hilo.start();
        }

    }
}

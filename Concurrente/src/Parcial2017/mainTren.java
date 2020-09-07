/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

import java.util.logging.Level;
import java.util.logging.Logger;

public class mainTren {

    public static void main(String[] args) {

        Vias unasVias = new Vias();

        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(mainTren.class.getName()).log(Level.SEVERE, null, ex);
            }
            Tren tren = new Tren(unasVias);
            Thread hilo = new Thread(tren, "tren " + i);
            hilo.start();
        }
    }
}

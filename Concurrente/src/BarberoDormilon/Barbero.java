/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberoDormilon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Barbero implements Runnable {

    Barberia miBarberia;

    public Barbero(Barberia b) {
        miBarberia = b;
    }

    public void run() {

        try {
            while (true) {
                miBarberia.afeitarCliente();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

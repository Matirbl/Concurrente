/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberoDormilon;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable {

    private Barberia miBarberia;
    private int id;

    public Cliente(Barberia b, int i) {
        miBarberia = b;
        id = i;

    }

    public void run() {
        try {

            while (true) {
                miBarberia.entrar(id);
                miBarberia.SentarseEnSillon(id);
                Thread.sleep(2000);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CenaConMirta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mozo implements Runnable {

    private Mesa miMesa;

    public Mozo(Mesa m) {
        miMesa = m;
    }

    public void run() {
        try {
            for (int i = 0; i < miMesa.lugaresEnMesa; i++) {

                miMesa.servirComida();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mozo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

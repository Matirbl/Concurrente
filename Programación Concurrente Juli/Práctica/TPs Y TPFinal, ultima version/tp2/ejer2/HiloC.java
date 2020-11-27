/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class HiloC implements Runnable {

    private Turno miT;

    public HiloC(Turno miT) {
        this.miT = miT;
    }

    @Override
    public void run() {
        while (true) {
            if (miT.esMiTurno('c')) {
                System.out.print("CCC ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloA.class.getName()).log(Level.SEVERE, null, ex);
                }
                miT.pasarTurno();
            }
        }

    }
}

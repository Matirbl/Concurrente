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
public class HiloB implements Runnable {

    private Turno miT;

    public HiloB(Turno miT) {
        this.miT = miT;
    }

    @Override
    public void run() {
        while (true) {
            if (miT.esMiTurno('b')) {
                System.out.print("BB ");
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

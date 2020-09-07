/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNieves;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class BlancaNieves implements Runnable {

    protected Mesa miMesa;

    public BlancaNieves(Mesa m) {
        miMesa = m;
    }

    public void run() {
        while (true) {
            try {

                miMesa.pasear();
                miMesa.servirComida();
            } catch (InterruptedException ex) {
                Logger.getLogger(BlancaNieves.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}

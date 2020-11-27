/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNievesSem;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BlancaNievesSem implements Runnable {

    private Mesa miMesa;

    public BlancaNievesSem(Mesa unaMesa) {
        miMesa = unaMesa;
    }

    public void run() {
        while (true) {
            try {
                boolean servir = true;
                while (servir) {
                    try {
                        servir = miMesa.servir();

                    } catch (InterruptedException ex) {
                        Logger.getLogger(BlancaNievesSem.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                miMesa.pasear();
            } catch (InterruptedException ex) {
                Logger.getLogger(BlancaNievesSem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

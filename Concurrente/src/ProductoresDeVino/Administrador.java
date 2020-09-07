/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoresDeVino;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador implements Runnable {

    Sala miSala;

    public Administrador(Sala s) {
        miSala = s;
    }

    public void run() {
        while (true) {
            try {
                miSala.reponerIngredientes();
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

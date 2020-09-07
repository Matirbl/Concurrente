/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CenaConMirta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mirta implements Runnable {

    private Mesa miMesa;

    public Mirta(Mesa m) {
        miMesa = m;
    }

    public void run() {

        try {
            miMesa.sentarseMirta();
            miMesa.comerMirta();
            miMesa.preguntaPolemica();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mirta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

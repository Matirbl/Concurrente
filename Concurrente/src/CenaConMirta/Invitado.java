/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CenaConMirta;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Invitado implements Runnable {

    private Mesa miMesa;
    private int id;

    public Invitado(Mesa m, int i) {
        miMesa = m;
        id = i;
    }

    public void run() {
        try {
            miMesa.sentarse(id);
            miMesa.comerInvitado(id);
            miMesa.comiendo(id);
            miMesa.terminarDeComer(id);
            miMesa.respuestaPolemica(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Invitado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaHemoterapia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Paciente implements Runnable {

    protected Sala miSala;
    protected int id;

    public Paciente(Sala s, int i) {
        miSala = s;
        id = i;
    }

    @Override
    public void run() {

        try {
            miSala.entrarSala(this);
            miSala.donar(this);
            miSala.salirSala(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

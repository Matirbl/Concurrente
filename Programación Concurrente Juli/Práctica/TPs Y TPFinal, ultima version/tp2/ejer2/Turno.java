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
public class Turno {

    private char turno;

    public Turno() {
        this.turno = 'a';
    }

    public synchronized void pasarTurno() {
        if (turno == 'a') {
            turno = 'b';
        } else if (turno == 'b') {
            turno = 'c';
        } else {
            turno = 'a';
        }
        this.notifyAll();
    }

    public synchronized boolean esMiTurno(char hilo) {
        try {
            while (this.turno != hilo) {
                this.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Turno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public synchronized char getTurno() {
        return this.turno;
    }

}

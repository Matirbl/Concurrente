/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class CocheSem implements Runnable {

    private boolean sentidoSur;
    private String patente;
    private Puente miPuente;

    public CocheSem(int a, String pat, Puente p) {

        if (a == 0) {
            sentidoSur = true;
        } else {
            sentidoSur = false;
        }
        patente = pat;
        miPuente = p;
    }

    public void run() {

        if (sentidoSur) {
            try {
                miPuente.cruzarSur(patente);
                miPuente.cruzando(patente);
                miPuente.salirSur(patente);
            } catch (InterruptedException ex) {
                Logger.getLogger(CocheSem.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                miPuente.cruzarNorte(patente);
                miPuente.cruzando(patente);
                miPuente.salirNorte(patente);
            } catch (InterruptedException ex) {
                Logger.getLogger(CocheSem.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}

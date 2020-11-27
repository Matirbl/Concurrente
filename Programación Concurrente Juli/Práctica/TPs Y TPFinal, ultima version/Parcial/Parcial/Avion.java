/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Avion implements Runnable {

    protected Pista miPista;
    protected boolean despega;

    public Avion(int i, Pista p) {
        if (i == 0) {
            despega = true;
        } else {
            despega = false;
        }
        miPista = p;
    }

    public void run() {
     try {
        if (despega) {
          
                miPista.despegar();
            
            miPista.despegando();
            miPista.terminarDeDespegar();
          }else {
            miPista.aterrizar();
            miPista.aterrizando();
            miPista.terminarDeAterrizar2();
        }
        } catch (InterruptedException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}

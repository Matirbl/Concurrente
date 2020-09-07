/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Estacion {

    boolean libre;

    public Estacion() {
        libre = true;
    }

    public void mezclar() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        synchronized(this)
        {
            libre=true;
        }
    }

    public synchronized boolean estaLibre() {
        return libre;
    }
    
    public synchronized void tomarEstacion(){
        libre = false;        
    }

}

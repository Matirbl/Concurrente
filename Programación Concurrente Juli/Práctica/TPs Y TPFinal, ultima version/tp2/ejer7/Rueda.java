/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejer7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Rueda {
    
    public synchronized void correr(String nombreHamster) {
        try {
            System.out.println(nombreHamster + " se encuentra corriendo");
            Thread.sleep(2500);
            System.out.println(nombreHamster + " termino de correr");

        } catch (InterruptedException ex) {
            Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

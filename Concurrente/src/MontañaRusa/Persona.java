/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MontañaRusa;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona implements Runnable {

    protected int id;
    protected Carrito miCarro;

    public Persona(int i, Carrito c) {
        miCarro = c;
        id = i;
    }

    public void run() {
        while (true) {
            try {
                miCarro.subirCarrito(this);
                miCarro.bajarCarrito(this);
                Thread.sleep(2000);
            } catch (InterruptedException ex) {

                Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}

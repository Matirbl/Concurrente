/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MontañaRusa;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MontañaRusa implements Runnable {

    protected Carrito miCarro;
    protected int vueltas;

    public MontañaRusa(Carrito c) {
        miCarro = c;
    }

    public void run() {
        while (vueltas < 7) {
            try {
                miCarro.arrancar();
            } catch (InterruptedException ex) {
                Logger.getLogger(MontañaRusa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

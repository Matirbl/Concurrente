/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNieves;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enano implements Runnable {
    
    protected Mesa miMesa;
    int id;
    
    public Enano(Mesa m, int i) {
        miMesa = m;
        id = i;
    }
    
    public void run() {
        
        while (true) {
            try {
                miMesa.sentarse(this);
                miMesa.EsperarComida(this);
                miMesa.comiendo(this);
                miMesa.terminarDeComer(this);
                miMesa.trabajando(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enano.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNievesSem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Enano implements Runnable {
    
    private Mesa miMesa;
    private String nombre;
    
    public Enano(String name, Mesa unaMesa) {
        
        miMesa = unaMesa;
        nombre = name;
    }
    
    public void run() {
        while (true) {
            try {
                miMesa.sentarse(nombre);
                miMesa.comiendo(nombre);
                miMesa.irATrabajar(nombre);
                miMesa.trabajando(nombre);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enano.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.vinitos;

/**
 *
 */
public class Reloj implements Runnable {
    
    private Almacen rc;

    public Reloj(Almacen rc) {
        this.rc = rc;
    }
    
    
    
    @Override
    public void run() {
        while (true) {
            try {
                rc.pasarSemana();
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            
        }
        
    }
    
}

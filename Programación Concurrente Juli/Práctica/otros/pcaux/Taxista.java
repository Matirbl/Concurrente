/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaux;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Taxista implements Runnable{
    private Taxi t;
    private String nombre;

    public Taxista(Taxi t, String nombre) {
        this.t = t;
        this.nombre = nombre;
    }
    
    @Override
    public void run() {
        while(true){
        try {
            t.andar();
            Thread.sleep(1000);
            t.destino();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxista.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    public Taxi getT() {
        return t;
    }

    public void setT(Taxi t) {
        this.t = t;
    }
    






}

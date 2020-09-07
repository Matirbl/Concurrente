/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO2;

/**
 *
 * @author Chino
 */
public class Reloj implements Runnable{
    private boolean esActivo;
    private int timer;
    
    public Reloj(int t){
        this.esActivo = true;
        this.timer = t;
    }
    
    public void run(){
        Tiempo.esperar(timer);
        this.esActivo = false;
    }
    
    public boolean esActivo(){
        return esActivo;
    }
}

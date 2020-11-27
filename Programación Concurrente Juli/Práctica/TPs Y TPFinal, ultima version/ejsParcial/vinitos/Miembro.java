/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.vinitos;

/**
 *
 */
public class Miembro implements Runnable {

    private int id;
    private Almacen recComp;
    
    public Miembro(int id, Almacen recComp) {
        this.id = id;
        this.recComp = recComp;
    }
    
    @Override
    public void run() {
        while (true) {
            recComp.hacerVino(id);
            recComp.tomarVino(id);
        }
        
    }
    
}

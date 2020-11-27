/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer4;

/**
 *
 * @author jdominguez
 */
public class HiloA implements Runnable {

    private RecursoCompartido v;

    public HiloA(RecursoCompartido v) {
        this.v = v;
    }
    
    

    @Override
    public void run() {

        while (true) {
            v.incrementar();

        }

    }

}

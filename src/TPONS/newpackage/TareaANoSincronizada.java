/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPONS.newpackage;

/**
 *
 * @author Chino
 */
public class TareaANoSincronizada implements Runnable {

    private ValorNoSincronizado v1;

    public TareaANoSincronizada(ValorNoSincronizado v) {

        this.v1 = v;
    }

    public void run() {
        v1.sumar();
    }

}

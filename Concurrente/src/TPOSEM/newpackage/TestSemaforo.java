/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPOSEM.newpackage;

import TPO.Test;
import TPOSEM.newpackage.ValorSem;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class TestSemaforo {

    public static void main(String[] args) {

        ValorSem total = new ValorSem();

        TareaASemaforo hilo1 = new TareaASemaforo(total);

        TareaBSemaforo hilo2 = new TareaBSemaforo(total);

        Thread t1 = new Thread(hilo1);

        Thread t2 = new Thread(hilo2);

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El valor total en el main queda: " + total.getTotal());

    }

}

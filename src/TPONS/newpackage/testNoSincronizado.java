/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPONS.newpackage;

import TPO.Test;
import TPONS.newpackage.TareaBNoSincronizada;
import TPONS.newpackage.TareaANoSincronizada;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testNoSincronizado {

    public static void main(String[] args) {

        ValorNoSincronizado total = new ValorNoSincronizado();
        TareaANoSincronizada hilo1 = new TareaANoSincronizada(total);
        TareaBNoSincronizada hilo2 = new TareaBNoSincronizada(total);

        Thread t1 = new Thread(hilo1);
        Thread t2 = new Thread(hilo2);
        t1.start();
        t2.start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("El valor del recurso compartido es: " + total.getTotal());
    }

}

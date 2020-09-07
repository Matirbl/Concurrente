/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class Test {

    public static void main(String[] args) {

        Valor total = new Valor();
        TareaA hilo1 = new TareaA(total);
        TareaB hilo2 = new TareaB(total);
        Thread t1 = new Thread(hilo1);

        Thread t2 = new Thread(hilo2);

        t1.start();
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El valor del recurso compartido es: " + total.getTotal());
    }
}

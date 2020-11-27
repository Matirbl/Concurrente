/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej4Puente;

/**
 *
 */
public class Test {

    public static void main(String[] args) {
        PuenteSemaph p = new PuenteSemaph();
        Thread t;
        for (int i = 1; i <= 5; i++) {
            if (i % 2 == 1) {
                //impar
                t = new Thread(new Coche(i, true, p));
            } else {
                //par
                t = new Thread(new Coche(i, false, p));
            }
            t.start();
        }
    }
}

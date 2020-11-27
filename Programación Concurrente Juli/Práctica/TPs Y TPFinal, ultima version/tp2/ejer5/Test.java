/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class Test {

    public static void main(String[] args) {
        Surtidor s = new Surtidor();

        Auto[] autos = new Auto[5];
        int r;
        Thread t;
        for (int i = 0; i < 5; i++) {
            r = (int) (Math.random() * 100);
            autos[i] = new Auto("" + (i + 1), "Model " + 2019, "Aslan", r, s);

            t = new Thread(autos[i]);
            t.start();
        }

    }

}

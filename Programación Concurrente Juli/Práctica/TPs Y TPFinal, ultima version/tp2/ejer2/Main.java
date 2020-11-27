/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer2;

/**
 *
 * @author jdominguez
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Turno t = new Turno();

        HiloA a = new HiloA(t);
        HiloB b = new HiloB(t);
        HiloC c = new HiloC(t);

        Thread thA = new Thread(a);
        Thread thB = new Thread(b);
        Thread thC = new Thread(c);

        thA.start();
        thB.start();
        thC.start();
        
        thC.join();
    }
}

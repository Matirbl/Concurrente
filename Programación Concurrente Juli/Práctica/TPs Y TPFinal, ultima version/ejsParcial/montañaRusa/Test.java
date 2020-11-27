/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.montañaRusa;

/**
 *
 */
public class Test {

    public static void main(String[] args) {

        int capacity = 4;

        MontañaRusa m = new MontañaRusa(capacity);
        Carrito c = new Carrito(m);
        Pasajero p;
        Thread t = new Thread(c);
        t.start();
        for (int i = 1; i <= 8; i++) {
            p = new Pasajero(i, m);
            t = new Thread(p);
            t.start();
        }

    }
}

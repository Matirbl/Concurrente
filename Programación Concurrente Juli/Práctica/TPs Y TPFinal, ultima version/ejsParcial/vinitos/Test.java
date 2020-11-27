/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.vinitos;

/**
 *
 */
public class Test {

    public static void main(String[] args) {
        Almacen a = new Almacen();
        Thread t;
        t = new Thread(new Admin(a));
        t.start();
        t = new Thread(new Reloj(a));
        t.start();
        for (int i = 1; i < 9; i++) {
            t = new Thread(new Miembro(i, a));
            t.start();
        }

    }

}

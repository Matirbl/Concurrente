/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.BlancaNieves;

/**
 *
 */
public class Test {
    public static void main(String[] args) {
        Casa c = new Casa(4);
        Thread t;
        t = new Thread(new Blancanieves (c));
        t.start();
        for (int i = 1; i <= 7; i++) {
            t = new Thread(new Enanito (i,c));
            t.start();
        }
    }
}

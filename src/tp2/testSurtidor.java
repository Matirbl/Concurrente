/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

public class testSurtidor {

    public static void main(String[] args) {
        Surtidor s = new Surtidor(1);

        Thread a1 = new Thread(new Auto("abc",s));
        Thread a2 = new Thread(new Auto("def",s));
        Thread a3 = new Thread(new Auto("fgh",s));
        Thread a4 = new Thread(new Auto("ijk",s));
        
        a1.start();
        a2.start();
        a3.start();
        a4.start();
    }
}

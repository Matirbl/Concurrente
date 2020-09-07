/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

public class TestHamsterSem {

    public static void main(String[] args) {

        JaulaSem jaula = new JaulaSem();
        Thread arrH[] = new Thread[8];
        for (int i = 0; i < 7; i++) {

            arrH[i] = new Thread(new HamsterSem("h" + i, jaula));

        }
        for (int j = 0; j < 7; j++) {
            arrH[j].start();

        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import utiles.Aleatorio;

public class Hamster implements Runnable {

    private String name;
    private Jaula cage;

    public Hamster(String nombre, Jaula j) {
        this.name = nombre;
        cage = j;
    }

    public void run() {
        while (true) {

            // switch (Aleatorio.intAleatorio(1, 4)) {
            //  case 1:
            cage.hamarcarse(name);
//                    break;
            //case 2:
            cage.comer(name);
//                    break;
            //case 3:
            cage.irAlaRueda(name);
            //break;
            // default:
//                    break;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

public class Tren implements Runnable {

    private Vias misVias;

    public Tren(Vias unasVias) {
        misVias = unasVias;
    }

    public void run() {
        misVias.trenCruzarVias();
    }
}

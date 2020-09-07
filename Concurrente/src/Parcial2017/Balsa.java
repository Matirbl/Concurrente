/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

import java.util.concurrent.Semaphore;

public class Balsa implements Runnable {
    

    private Estacionamiento miEstacionamiento;

    public Balsa(Estacionamiento e) {

        miEstacionamiento = e;
    }

    public void run() {
        while (true) {
            miEstacionamiento.cruzarBalsa();
        }
    }
}

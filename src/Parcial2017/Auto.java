/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

/**
 *
 * @author Chino
 */
public class Auto implements Runnable {

    Estacionamiento miEstacionamiento;

    public Auto(Estacionamiento e) {
        miEstacionamiento = e;
    }

    public void run() {
      miEstacionamiento.subirAuto();
    }
}

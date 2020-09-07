/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Chino
 */
public class Estacionamiento {

    private int cantAutos;
    private final int CAPACIDAD;
    private Semaphore subirAuto;
    private Semaphore bajarAuto;
    private Semaphore balsaIr;
    private Semaphore balsaVolver;

    public Estacionamiento(int cap) {
        cantAutos = 0;
        CAPACIDAD = cap;
        subirAuto = new Semaphore(0);
        bajarAuto = new Semaphore(0);
        balsaIr = new Semaphore(0);
        balsaVolver = new Semaphore(0);
    }

    public boolean hayLugar() {
        return (cantAutos < CAPACIDAD);
    }

    public boolean esVacio() {
        return (cantAutos == 0);
    }

    public void subirAuto() {
        try {

            subirAuto.acquire();
            System.out.println("Se sube un auto");
            cantAutos++;
            if (hayLugar()) {
                subirAuto.release();
            } else {
                balsaIr.release();
            }
            bajarAuto.acquire();
            cantAutos--;
            if (esVacio()) {
                balsaVolver.release();
            } else {
                System.out.println("Se baja un auto");
                bajarAuto.release();
            }

        } catch (Exception e) {

        }
    }

    public void cruzarBalsa() {
        try {
            subirAuto.release();
            balsaIr.acquire();
            System.out.println("La balsa esta cruzando el rio");
            Thread.sleep(2000);
            System.out.println("sadasdasdasa");
            bajarAuto.release();
            balsaVolver.acquire();
            System.out.println("La balsa esta volviendo");
            Thread.sleep(2000);

        } catch (Exception e) {

        }

    }

}

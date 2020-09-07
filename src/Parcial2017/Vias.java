/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpo2_2dointento.Aleatorio;

public class Vias {

    private int cantTrenesDer;
    private int cantTrenesIzq;
    private Semaphore trenesDer;
    private Semaphore trenesIzq;
    private Semaphore viasDisp;
    private boolean esPrimero;

    public Vias() {
        esPrimero = true;
        cantTrenesDer = 0;
        cantTrenesIzq = 0;
        trenesDer = new Semaphore(1);
        trenesIzq = new Semaphore(0);
        viasDisp = new Semaphore(1);
    }

    public void trenCruzarViasDer() {
        try {
            cantTrenesDer++;
            System.out.println("Un tren esta esperando a la derecha");
            trenesDer.acquire();
            cantTrenesDer--;
            viasDisp.acquire();
            Thread.sleep(500);
            System.out.println("Un tren de la derecha comenzo a cruzar");
            Thread.sleep(10000);
            viasDisp.release();
            if (cantTrenesIzq > 0) {
                System.out.println("Un tren de la derecha avisa a uno de la izq ");
                trenesIzq.release();
            } else {
                System.out.println("Un tren de la derecha avisa a uno de la der ");
                trenesDer.release();
            }
        } catch (InterruptedException e) {
        }
    }

    public void trenCruzarViasIzq() {
        try {
            cantTrenesIzq++;
            System.out.println("Un tren esta esperando a la izq");
            trenesIzq.acquire();
            cantTrenesIzq--;
            viasDisp.acquire();
            Thread.sleep(500);
            System.out.println("Un tren de la izq comenzo a cruzar  ");
            Thread.sleep(10000);
            viasDisp.release();
            if (cantTrenesDer > 0) {
                System.out.println("Un tren de la izq avisa a uno de la derecha que cruce");
                trenesDer.release();
            } else {
                System.out.println("Un tren de la izq avisa a uno de la izq que cruce");
                trenesIzq.release();
            }
        } catch (InterruptedException e) {
        }
    }

    public void trenCruzarVias() {
        if (esPrimero || Aleatorio.intAleatorio(0, 2) == 0) {
            if (esPrimero) {
                esPrimero = false;
            }
            trenCruzarViasDer();
        } else {
            trenCruzarViasIzq();
        }
    }

}

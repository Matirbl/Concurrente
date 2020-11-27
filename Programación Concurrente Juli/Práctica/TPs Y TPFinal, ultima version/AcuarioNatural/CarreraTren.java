/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class CarreraTren implements Runnable {

    /*
    hilo encargado de llevar las personas al inicio de la carrera
     */
    private Carrera c;
    private Reloj r;

    public CarreraTren(Carrera c, Reloj r) {
        this.c = c;
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            if (r.carreraDisp()) {
                c.arrancarRecorrido();
                c.recorrido();
                c.finalizarRecorrido();
            } else {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
            }

        }

    }

}

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
public class CarreraCamioneta implements Runnable {

    /*
    hilo encargado de llevar las pertenencias de las personas al final de la carrera
     */
    private Carrera c;
    private Reloj r;

    public CarreraCamioneta(Carrera c, Reloj r) {
        this.c = c;
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            c.llevarPertenencias();
            c.recorrido();
            c.depositarPertenencias();

        }

    }

}

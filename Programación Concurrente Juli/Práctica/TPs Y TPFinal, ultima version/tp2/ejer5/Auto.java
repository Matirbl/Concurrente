/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class Auto extends Vehiculo implements Runnable {

    private Surtidor miSurtidor;
    private int recargas;

    public Auto(String patente, String Modelo, String Marca, int kmFaltantesParaElService, Surtidor s) {
        super(patente, Modelo, Marca, kmFaltantesParaElService);
        miSurtidor = s;
        recargas = 0;

    }

    @Override
    public void run() {
        while (recargas < 3) {
            circular();
        }

    }

    public void circular() {
        try {
            int j = (int) (Math.random() * 10) + 1;
            if (kmFaltantesParaElService > j * 3) {
                System.out.println(patente + " está circulando con " + kmFaltantesParaElService);
                Thread.sleep(2500);

                kmFaltantesParaElService = kmFaltantesParaElService - j * 3;
                System.out.println(patente + " tiene " + kmFaltantesParaElService + "km RESTANTES");
                

            } else {
                kmFaltantesParaElService = 1;
                miSurtidor.recargar(this);

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void incrementarRecarga() {
        this.recargas++;
    }

}

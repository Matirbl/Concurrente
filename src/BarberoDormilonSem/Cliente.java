/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberoDormilonSem;

import BarberoDormilon.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable {

    private Barberia miBarberia;
    private String nombre;

    public Cliente(Barberia b, String name) {
        miBarberia = b;
        nombre = name;

    }

    public void run() {
        try {
            
            if (miBarberia.entrar()) {
                System.out.println("El cliente " + nombre + " entra a la barberia");
                miBarberia.SentarseEnSillon(nombre);
                miBarberia.irseBarberia(nombre);

            } else {
                System.out.println("Barbería llena " + nombre + " se va ");
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

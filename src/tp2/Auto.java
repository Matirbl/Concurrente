/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Auto implements Runnable {

    private String patente;
    private int cuentaKm;
    private String modelo;
    private boolean disponible;
    private Surtidor unSurtidor;

    public Auto() {
        patente = null;
        cuentaKm = 0;
        modelo = null;
        disponible = true;

    }
//constructores

    public Auto(String pat, Surtidor surt) {
        patente = pat;
        cuentaKm = 0;
        modelo = null;
        disponible = true;
        unSurtidor = surt;
    }

    public Auto(String pat, int km, String mod, boolean disponibilidad) {
        patente = pat;
        cuentaKm = km;
        modelo = mod;
        disponible = disponibilidad;

    }
//modificadores

    public void setCuentaKm(int km) {
        this.cuentaKm = km;
    }

    public void setModelo(String mod) {

        this.modelo = mod;

    }

    public void setDisponible(boolean dispo) {
        disponible = dispo;

    }

//observadores
    public String getPatente() {
        return this.patente;

    }

    public int getKm() {
        return this.cuentaKm;
    }

    public String getModelo() {
        return this.modelo;
    }

    public boolean getDisponible() {
        return this.disponible;
    }

    public String toString() {

        String cadena = "El auto de modelo" + this.getModelo() + " que tiene patente " + this.getPatente() + " y tiene " + this.getKm() + " esta disponible? " + this.getDisponible();
        return cadena;
    }

    public boolean equals(Auto a) {
        return (this.patente.equals(a.patente));
    }

    public void run() {
        while (cuentaKm <= 20) {
            cuentaKm = cuentaKm + 2;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        unSurtidor.cargarNafta(patente);
        cuentaKm = 0;
    }

 
}

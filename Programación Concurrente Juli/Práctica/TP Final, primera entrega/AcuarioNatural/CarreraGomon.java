/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import utiles.TextoColorido;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class CarreraGomon {

    private int id;
    private int capacidad;//va de 1 a 2
    private int asiento;//indica donde se ubicara
    private boolean lleno;//indica si ya no le quedan lugares;
    private boolean llego;
    private int puesto;
    private Persona[] lugar;
    private TextoColorido color;

    public CarreraGomon(int id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
        asiento = 0;
        lleno = false;
        llego = false;
        puesto = 0;
        lugar = new Persona[capacidad];
        color = new TextoColorido();
    }

    public synchronized void subirse(Persona p) {

        lugar[asiento] = p;//se subio
        asiento++;
        System.out.println(color.getAzul() +p.getIdPersona() + " se subio al gomon " + id + " capacidad " + capacidad);
        if (capacidad == asiento) {
            lleno = true;
        }
       
    }

    public void llegaron() {
        String s = "";
        llego = true;
        if (capacidad == 2) {
            for (int i = 0; i < lugar.length; i++) {
                s += lugar[i].getIdPersona() + " ";
            }
            System.out.println(color.getAzul() +s + " llegaron en el puesto " + puesto + " en el gomon " + id);
        } else {
            System.out.println(color.getAzul() +lugar[0].getIdPersona() + " llego en el puesto " + puesto + " en el gomon " + id);
        }
    }

    public synchronized void bajarse(int idPersona) {
        lleno = false;
        System.out.println(color.getAzul() +idPersona + " se bajo del gomon " + id);
        asiento--;
        
    }


    public synchronized boolean seEncuentraLleno() {
        return (capacidad == asiento);
    }

    public synchronized boolean lleno() {
        return lleno;
    }

    public synchronized  boolean llego() {
        return llego;
    }

    public synchronized  void setLlego(boolean llego) {
        this.llego = llego;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

}

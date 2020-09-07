/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HamsterSem implements Runnable {

    private JaulaSem cage;
    private String nombre;

    public HamsterSem(String n, JaulaSem j) {
        this.nombre = n;
        this.cage = j;
    }

    public void comer() {
        this.cage.comenzarAcomer(nombre);

    }

    public void jugar() {
        this.cage.comenzarAjugar(nombre);
    }

    public void dormir() {
        try {
            System.out.println("El Hamster:" + this.nombre + " ha comenzado a DORMIR");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HamsterSem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El Hamster:" + this.nombre + " ha DESPERTADO");
    }

    public void run() {
        this.comer();
        this.jugar();
        this.dormir();
    }

}

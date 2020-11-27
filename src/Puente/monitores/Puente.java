/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.monitores;

 
public class Puente {
    private int capacidad, cruzando;
    private boolean direccion;

    public Puente(int capacidad) {
        this.capacidad = capacidad;
        this.cruzando = 0;
        this.direccion = true;
    }

    public synchronized void entrarCoche(boolean d) {
        while (cruzando == capacidad || d != direccion) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cruzando++;
    }

    public synchronized void salirCoche(boolean d) {
        cruzando--;
        if (cruzando == 0) {
            direccion = !direccion;
        }
        notifyAll();
    }
}
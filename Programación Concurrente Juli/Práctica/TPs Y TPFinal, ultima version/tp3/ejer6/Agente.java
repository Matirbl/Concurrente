/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ejer6;

/**
 *
 */
public class Agente implements Runnable {

    private String nombre;
    private Mesa m;

    public Agente(String nombre, Mesa m) {
        this.nombre = nombre;
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            m.depositar(nombre);
        }
    }

}

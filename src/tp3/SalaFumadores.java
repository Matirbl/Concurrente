/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class SalaFumadores {

    private int elementoFaltante;

    public SalaFumadores() {

    }

    public synchronized void colocar(int i) throws InterruptedException {
        elementoFaltante = i;
        System.out.println("Ahora fuma " + i);
        notifyAll();
        wait();
    }

    public synchronized void entrafumar(int id) throws Exception {

        while (id != elementoFaltante) {

            System.out.println("El fumador: " + id + " no puede fumar");
            wait();

        }

        Thread.sleep(2000);
        System.out.println("el fumador: " + id + " entra a fumar");

    }

    synchronized void terminafumar() {
        System.out.println("El fumador con ingrediente: " + elementoFaltante + "termina de fumar");
        elementoFaltante = -1;
        notifyAll();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNieves;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mesa {

    private int sillasDisponibles;
    public int enanoSinComida;

    public Mesa() {
        sillasDisponibles = 4;
        enanoSinComida = 0;
       
    }

    public synchronized void sentarse(Enano en) throws InterruptedException {

        while (sillasDisponibles < 1) {

            System.out.println("Enano: " + en.id + " espera lugar en la mesa");
            wait();
        }
        System.out.println("Enano: " + en.id + " se sienta a la mesa");
        enanoSinComida++;
        sillasDisponibles--;
        notifyAll();
    }

    public synchronized void EsperarComida(Enano en) throws InterruptedException {
        while (enanoSinComida > 0) {
            System.out.println("\u001B[33mEnano: " + en.id + " espera un plato para comer");
            wait();
        }
    }

    public void comiendo(Enano en) {
        System.out.println("\u001B[32mEl enano: " + en.id + "esta comiendo");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void terminarDeComer(Enano en) {
        System.out.println("\u001B[34mEl enano: " + en.id + " termina de comer");
        sillasDisponibles++;
        notifyAll();

    }

    public synchronized void servirComida() throws InterruptedException {

        while (enanoSinComida < 1) {
            wait();
        }
        System.out.println("Blanca Nieves sirve comida a los enanos");
        enanoSinComida = 0;
        notifyAll();
    }

    public synchronized void pasear() throws InterruptedException {

        System.out.println("Blanca nieves esta caminando con el principe");
        Thread.sleep(2000);

    }

    public void trabajando(Enano en) {
        System.out.println("\u001B[35mEl enano: " + en.id + " esta trabajando");
    }

}

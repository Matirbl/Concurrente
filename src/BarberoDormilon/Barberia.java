/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberoDormilon;

/**
 *
 * @author Chino
 */
public class Barberia {
//hola, esta es una prueba
    int sillasDisponibles;

    int sillonOcupado;

    public Barberia() {
        sillasDisponibles = 4;
        sillonOcupado = -1;
    }

    public synchronized void entrar(int i) throws InterruptedException {
        while (sillasDisponibles == 0) {
            System.out.println("Cliente: " + i + " No entra a la barberia");
            wait();
        }
        sillasDisponibles--;
        System.out.println("Cliente: " + i + " se sienta en una silla " + " Sillas disponibles: " + sillasDisponibles);

    }

    public synchronized void SentarseEnSillon(int i) throws InterruptedException {
        while (sillonOcupado != -1) {
            System.out.println("Sillon ocupado");
            wait();
        }
        System.out.println("\u001B[32mcliente: " + i + " Se sienta en el sillon");
        sillasDisponibles++;
        sillonOcupado = i;
        notifyAll();
    }

    public synchronized void afeitarCliente() throws InterruptedException {
        while (sillonOcupado < 0) {
            wait();
        }
        System.out.println("\u001B[33mBarbero afeita al cliente: " + sillonOcupado);
        Thread.sleep(2000);
        sillonOcupado = -1;
        notifyAll();

    }

}

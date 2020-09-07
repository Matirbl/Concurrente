/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberoDormilon;

public class MainBarberia {

    public static void main(String[] args) {

        Barberia unaBarberia = new Barberia();
        Thread Clientes[] = new Thread[10];
        Thread Barbero = new Thread(new Barbero(unaBarberia));

        for (int i = 0; i < Clientes.length; i++) {
            Clientes[i] = new Thread(new Cliente(unaBarberia,i));
        }
        for (int j = 0; j < Clientes.length; j++) {
            Clientes[j].start();

        }
        Barbero.start();

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CenaConMirta;

import tp3.FumadorLock;

public class MainMirta {

    public static void main(String[] args) {

        int cant = 6;
        Mesa unaMesa = new Mesa(cant);
        Thread invitados[] = new Thread[cant];
        Thread mozo = new Thread(new Mozo(unaMesa));
        Thread Mirta = new Thread(new Mirta(unaMesa));

        for (int i = 0; i < invitados.length; i++) {
            invitados[i] = new Thread(new Invitado(unaMesa, i));

        }
        for (int j = 0; j < invitados.length; j++) {
            invitados[j].start();

        }
        mozo.start();
        Mirta.start();

    }
}

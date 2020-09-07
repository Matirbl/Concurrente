/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilosofosMesa;

public class TestFilosofos {

    public static void main(String[] args) {
        int cant = 5;
        Mesa mesa = new Mesa(cant);
        Filosofo filosofos[] = new Filosofo[cant];

        for (int i = 0; i < cant; i++) {
            filosofos[i] = new Filosofo(mesa, i);

        }

        for (int j = 0; j < cant; j++) {
            filosofos[j].start();

        }
    }
}

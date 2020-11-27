/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaux;

/**
 *
 * @author jdominguez
 */
public class Test {

    public static void main(String[] args) {

        Taxi taxi = new Taxi();
        Taxista taxista = new Taxista(taxi, "Charly");
        Pasajero[] pasajeros = new Pasajero[5];
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Pasajero(taxi, "pasajero: " + (i + 1) +" .");
        }

        for (int i = 0; i < pasajeros.length; i++) {
            new Thread(pasajeros[i]).start();
        }
        new Thread(taxista).start();

    }
}

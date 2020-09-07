/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

public class testPuente {

    public static void main(String[] args) {

        Puente unPuente = new Puente();
        Thread autos[] = new Thread[200];

        for (int i = 0; i < autos.length; i++) {
            autos[i] = new Thread(new Vehiculo(unPuente, i % 2));

        }
        for (int i = 0; i < autos.length; i++) {
            autos[i].start();
        }

    }

}

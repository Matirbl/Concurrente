/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

public class Vehiculo implements Runnable {

    private Puente miPuente;
    private int sentido;

    public Vehiculo(Puente p, int b) {
        miPuente = p;
        sentido = b;
    }

    public void run() {

        if (sentido == 0) {
            miPuente.entrarNorte();
            miPuente.cruzando();
            miPuente.salirSur();
        } else {
            miPuente.entrarSur();
            miPuente.cruzando();
            miPuente.salirNorte();
        }

    }
}

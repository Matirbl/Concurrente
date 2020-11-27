/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejer7;

public class Hamster implements Runnable {

    private String miNombre;
    private Jaula miJaula;

    public Hamster(String n, Jaula jau) {
        miNombre = n;
        miJaula = jau;

    }

    @Override
    public void run() {
        while (true) {
            miJaula.getP().comer(miNombre);
            miJaula.getR().correr(miNombre);
            miJaula.getH().dormir(miNombre);

        }

    }

}

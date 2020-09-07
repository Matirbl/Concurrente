/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoresDeVino;

public class Productor implements Runnable {

    protected Sala miSala;
    protected int id;

    public Productor(Sala s, int i) {
        miSala = s;
        id = i;

    }

    public void run() {
        while (true) {
            miSala.hacerVino(id);
            miSala.iniciarPreparacion(id);
            miSala.mezclar(id);
            miSala.terminarDeMezclar(id);
            miSala.tomarVino(id);
        }
    }

}

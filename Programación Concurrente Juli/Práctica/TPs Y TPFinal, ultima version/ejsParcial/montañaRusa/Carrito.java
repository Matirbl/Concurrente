/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.montañaRusa;

/**
 *
 */
public class Carrito implements Runnable {

    private MontañaRusa m;

    public Carrito(MontañaRusa m) {
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            m.andar();
        }
    }

    public MontañaRusa getM() {
        return m;
    }

    public void setM(MontañaRusa m) {
        this.m = m;
    }

}

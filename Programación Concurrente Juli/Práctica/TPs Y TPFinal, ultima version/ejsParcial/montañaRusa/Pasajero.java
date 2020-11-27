/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.montañaRusa;

/**
 *
 */
public class Pasajero implements Runnable {

    private int id;
    private MontañaRusa m;

    public Pasajero(int id, MontañaRusa m) {
        this.id = id;
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            m.subir(id);
            m.bajar(id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MontañaRusa getM() {
        return m;
    }

    public void setM(MontañaRusa m) {
        this.m = m;
    }

}

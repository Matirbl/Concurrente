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
public class Pasajero implements Runnable {

    private Taxi t;
    private String nomb;

    public Pasajero(Taxi t, String nomb) {
        this.t = t;
        this.nomb = nomb;
    }

    @Override
    public void run() {
        while (true) {
            try {
                t.subirse(nomb);
                t.bajarse(nomb);
            } catch (Exception e) {
            }
        }
    } 

    public String getNomb() {
        return nomb;
    }

    public Taxi getT() {
        return t;
    }

    public void setT(Taxi t) {
        this.t = t;
    }

}

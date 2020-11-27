/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.BlancaNieves;

/**
 *
 */
public class Blancanieves implements Runnable {

    private Casa rc;

    public Blancanieves(Casa rc) {
        this.rc = rc;
    }

    @Override
    public void run() {
        while (true) {
            if (rc.getServirComida() == 0) {
                rc.pasearConElPrincipe();
            } else {
                rc.empezarAServir();
                rc.servir();
                rc.terminarAServir();
            }
        }

    }

}

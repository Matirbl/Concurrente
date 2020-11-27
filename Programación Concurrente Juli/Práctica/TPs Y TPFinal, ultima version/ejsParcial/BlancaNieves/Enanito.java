/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.BlancaNieves;

/**
 *
 */
public class Enanito implements Runnable{
    private int id;
    private Casa rc;

    public Enanito(int id, Casa rc) {
        this.id = id;
        this.rc = rc;
    }

    @Override
    public void run() {
        while (true) {
            rc.sentarse(id);
            rc.empezarAComer(id);
            rc.comer(id);
            rc.terminarDeComer(id);
            rc.trabajar(id);
        }
    }
    
    
    
}

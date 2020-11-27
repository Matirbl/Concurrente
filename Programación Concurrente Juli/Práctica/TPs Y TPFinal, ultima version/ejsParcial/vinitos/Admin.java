/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.vinitos;

/**
 *
 */
public class Admin implements Runnable{
    private Almacen rc;

    public Admin(Almacen rc) {
        this.rc = rc;
    }

    @Override
    public void run() {
        while (true) {
            rc.reponerIngredientes();
        }
    }
    
    
    
    
}

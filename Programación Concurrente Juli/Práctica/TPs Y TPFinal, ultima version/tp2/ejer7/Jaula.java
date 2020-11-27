/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejer7;


/**
 *
 */
public class Jaula {

    private Plato p;
    private Hamaca h;
    private Rueda r;
    
    public Jaula(){
        p  = new Plato();
        h = new Hamaca();
        r = new Rueda();
        
        
        
    }

    public Plato getP() {
        return p;
    }

    public void setP(Plato p) {
        this.p = p;
    }

    public Hamaca getH() {
        return h;
    }

    public void setH(Hamaca h) {
        this.h = h;
    }

    public Rueda getR() {
        return r;
    }

    public void setR(Rueda r) {
        this.r = r;
    }
    

    

}

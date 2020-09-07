/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPOSEM.newpackage;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class ValorSem {

    protected int total;
    private Semaphore sem;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ValorSem() {
        /*Inicializo la variable con el valor 3, y creo un permiso
         que va a servir para ambos metodos, sumar y multiplicar*/
        this.total = 3;
        this.sem = new Semaphore(1);
    }

    public void sumar() {
        try {
            sem.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ValorSem.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.total = total + 1;
        System.out.println("VALOR DESPUES DE SUMAR: " + this.getTotal());
        sem.release();
    }

    public void multiplicar() {
        try {
            sem.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ValorSem.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.total = total * 2;
        System.out.println("VALOR DESPUES DE MULTIPLICAR: " + this.getTotal());
        sem.release();
    }

}

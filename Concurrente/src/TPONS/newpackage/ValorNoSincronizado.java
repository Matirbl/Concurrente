/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPONS.newpackage;

/**
 *
 * @author Chino
 */
public class ValorNoSincronizado {

    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ValorNoSincronizado() {
        this.total = 3;
    }

    public void sumar() {
        this.total = total + 1;
        System.out.println("El valor despues de sumar es: " + this.getTotal());
    }

    public void multiplicar() {
        this.total = total * 2;
        System.out.println("El valor despues de multiplicar es: " + this.getTotal());
    }

}

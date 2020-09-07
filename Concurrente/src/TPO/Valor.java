/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO;

public class Valor {

    int total;

    public synchronized int getTotal() {
        return total;
    }

    public synchronized void setTotal(int total) {
        this.total = total;
    }

    public Valor() {
        this.total = 3;
    }

    public synchronized void sumar() {
        this.total = total + 1;
    }

    public synchronized void multiplicar() {
        this.total = total * 2;
    }
}

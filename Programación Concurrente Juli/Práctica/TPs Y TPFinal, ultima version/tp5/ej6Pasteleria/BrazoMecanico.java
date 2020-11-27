/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej6Pasteleria;

/**
 *
 * @author Jhoke
 */
public class BrazoMecanico implements Runnable {

    private BufferLimitado b;

    public BrazoMecanico(BufferLimitado b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            b.retirarCaja();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer2MuseoConTemperatura;

/**
 *
 * @author Jhoke
 */
public class Reloj implements Runnable {

    private Museo m;

    public Reloj(Museo m) {
        this.m = m;
    }

    
    @Override
    public void run() {
        try {
            while (true) {
                System.err.println("Temp Previa: " + m.getTemperatura());
                System.err.println("Cap Previa: " + m.getCapacidad());
                m.modificarTemperatura();
                System.err.println("Temp Actual: " + m.getTemperatura());
                System.err.println("Cap Actual: " + m.getCapacidad());
                Thread.sleep(8000);
            }
        } catch (Exception e) {
        }

    }

}

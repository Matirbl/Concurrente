/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgenteLock implements Runnable {

    private SalaLock sala;
    private Random r;

    public AgenteLock(SalaLock sala) {
        this.sala = sala;
        r = new Random();
    }

    public void run() {
        while (true) {
            try {
                sala.colocar(r.nextInt(3) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AgenteLock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

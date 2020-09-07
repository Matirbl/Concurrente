/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

/**
 *
 * @author Chino
 */
public class DisparaSalaLock {

    public static void main(String[] args) {
        SalaLock sala = new SalaLock();
        Thread hilosFumadores[] = new Thread[24];

        for (int i = 0; i < hilosFumadores.length; i++) {
            hilosFumadores[i] = new Thread(new FumadorLock((i % 3) + 1, sala));

        }

        Thread ag = new Thread(new AgenteLock(sala));
        ag.start();
        for (int j = 0; j < hilosFumadores.length; j++) {
            hilosFumadores[j].start();

        }
    }
}

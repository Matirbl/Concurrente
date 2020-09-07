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
public class FumadorLock implements Runnable {

    private int id;
    private SalaLock sala;

    public FumadorLock(int id, SalaLock sala) {
        this.id = id;
        this.sala = sala;
    }

    public void run() {
        while (true) {
            try {
                sala.entrafumar(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

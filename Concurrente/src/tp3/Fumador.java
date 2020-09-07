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
public class Fumador implements Runnable {

    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }

    public void run() {
        while (true) {
            try {
                sala.entrafumar(id);
                System.out.println("Fumador " + id + " está fumando.");
                Thread.sleep(1000);
                sala.terminafumar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

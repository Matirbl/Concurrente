/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laExposición;

/**
 *
 */
public class Test {

    public static void main(String[] args) {

        Sala s = new Sala(3);
        Thread[] visitantes = new Thread[5];
        Thread[] responsables;
        responsables = new Thread[2];
        Thread critico;

        for (int i = 0; i < visitantes.length - 1; i++) {
            visitantes[i] = new Thread(new Visitante(i + 1, s));
            visitantes[i].start();
        }
        
        
        for (int i = 0; i < responsables.length - 1; i++) {
            responsables[i] = new Thread(new Responsable(i + 1, s));
            responsables[i].start();
        }

        critico = new Thread(new Critico(s));
        critico.start();
    }

}

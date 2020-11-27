/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer2MuseoConTemperatura;

import static utiles.Aleatorio.intAleatorio;

/**
 *
 * @author Jhoke
 */
public class Test {

    public static void main(String[] args) {
        Museo m = new Museo(10);
        Thread[] personas = new Thread[10];
        
        for (int i = 0; i < 4; i++) {
            personas[i] = new Thread(new Persona(i + 1, intAleatorio(40, 80), m));
            personas[i].start();
        }
        Thread r = new Thread(new Reloj(m));
        r.start();

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej6Pasteleria;

import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class Empaquetador implements Runnable {

    private int id;
    private BufferLimitado b;

    public Empaquetador(int id, BufferLimitado b) {
        this.id = id;
        this.b = b;
    }

    @Override
    public void run() {
        double p;
        while (true) {
            p = 200.50 * intAleatorio(1, 3);
            agarrarPastel(id, p);
            b.colocarPastel(id, p);

        }

    }

    public static void agarrarPastel(int idEmpaq, double p) {
        try {
            System.out.println("Empaq: " + idEmpaq + " se encuentra agarrando un pastel");
            Thread.sleep(1000 * intAleatorio(1, 3));
            System.out.println("Empaq: " + idEmpaq + " desea empaquetar un pastel con "+ p);
        } catch (Exception e) {
        }
    }
}

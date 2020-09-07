/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5_concurrente.Ej2;

import Utiles.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tpo2_2dointento.Aleatorio.intAleatorio;

public class EscritorMonitor implements Runnable {

    private LibroMonitor miLibro;

    public EscritorMonitor(LibroMonitor libro) {
        miLibro = libro;
    }

    public int agregarPagina() {
        int agregar;
        if (miLibro.getCantPagEscritas() < miLibro.getTotalPag() - 3) {
            agregar = intAleatorio(1, 4);
        } else {
            agregar = 1;
        }
        return agregar;

    }

    public void run() {

        while (!miLibro.finalizado()) {

            try {
                miLibro.empezarEscribir(this, this.agregarPagina());
              //  miLibro.escribiendo();
              Thread.sleep(4000);
                miLibro.terminarEscribir();
            } catch (InterruptedException ex) {
                Logger.getLogger(LectorMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}

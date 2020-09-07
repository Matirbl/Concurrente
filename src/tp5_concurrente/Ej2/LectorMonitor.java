/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5_concurrente.Ej2;

import java.util.logging.Level;
import java.util.logging.Logger;
import static tpo2_2dointento.Aleatorio.intAleatorio;

public class LectorMonitor implements Runnable {

    private LibroMonitor miLibro;
    private int hojasLeidas;

    public LectorMonitor(LibroMonitor libro) {
        miLibro = libro;
        hojasLeidas = 0;
    }

    public int cantHojasLeer() {
        int hojasLeer;
        if (miLibro.getCantPagEscritas() < miLibro.getTotalPag() - 3) {
            hojasLeer = intAleatorio(2, 4);
        } else {
            hojasLeer = 1;
        }
        return hojasLeer;
    }

    public void run() {

        while (hojasLeidas < miLibro.getTotalPag()) {

            //System.out.println(Thread.currentThread().getName() + "entra a leer");
            hojasLeidas = miLibro.empezarLeer(hojasLeidas, this.cantHojasLeer());
            miLibro.leyendo();
            miLibro.terminarLeer();
            System.out.println("\u001B[34mLeí "+ hojasLeidas+ "hojas");

        }
    }
}

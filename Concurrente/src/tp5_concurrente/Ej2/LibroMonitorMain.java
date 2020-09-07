/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5_concurrente.Ej2;

public class LibroMonitorMain {

    public static void main(String[] args) {

        LibroMonitor elLibro = new LibroMonitor(30);
        Thread hiloEscritor = new Thread(new EscritorMonitor(elLibro));

        Thread hilosLectores[] = new Thread[3];

        for (int i = 0; i < hilosLectores.length; i++) {
            hilosLectores[i] = new Thread(new LectorMonitor(elLibro));

        }
        for (int i = 0; i < hilosLectores.length; i++) {
            hilosLectores[i].start();

        }
        hiloEscritor.start();
    }
}

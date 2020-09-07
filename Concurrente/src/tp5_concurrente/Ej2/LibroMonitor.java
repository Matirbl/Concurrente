package tp5_concurrente.Ej2;

import java.util.logging.Level;
import java.util.logging.Logger;
import static tpo2_2dointento.Aleatorio.intAleatorio;

public class LibroMonitor {

    private int nLectores;
    private int nEscritores;
    private int cantPagEscritas;
    private final int totalPag;

    public LibroMonitor(int cantPaginas) {
        this.nLectores = 0;
        this.cantPagEscritas = 0;
        this.totalPag = cantPaginas;
    }

    public synchronized int empezarLeer(int cantHojasLeer, int cantHojasLeidas) {

        while (nEscritores > 0 || ((cantHojasLeidas + cantHojasLeer) > cantPagEscritas)) {
            System.out.println("El " + Thread.currentThread().getName() + " espera para leer");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(LibroMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantHojasLeidas = cantHojasLeer + cantHojasLeidas;

        nLectores++;

        return cantHojasLeidas;
    } 

    public synchronized void terminarLeer() {

        System.out.println("El " + Thread.currentThread().getName() + " termino de leer");
        nLectores--;
        this.notifyAll();

    }

    public synchronized void empezarEscribir(EscritorMonitor unEscritor, int nuevasHojas) throws InterruptedException {
        while ((nEscritores > 0 || nLectores > 0) || ((this.cantPagEscritas + nuevasHojas > totalPag))) {
            System.out.println("El escritor " + Thread.currentThread().getName() + " espera para escribir");
            this.wait();
        }
        nEscritores++;
        this.cantPagEscritas = this.cantPagEscritas + nuevasHojas;
        System.out.println("\u001B[35mEl " + Thread.currentThread().getName() + "\u001B[35mcomenzo a escribir");
        System.out.println("\u001B[31mTotal de paginas escritas: " + this.cantPagEscritas);
    }

    public void leyendo() {
        try {

            System.out.println("\u001B[32m El " + Thread.currentThread().getName() + "\u001B[32m esta leyendo");
            Thread.sleep(intAleatorio(1000, 3000));

        } catch (InterruptedException ex) {
            Logger.getLogger(LibroMonitor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void terminarEscribir() {
        System.out.println("\u001B[32mEl " + Thread.currentThread().getName() + "\u001B[32m termino de escribir");
        nEscritores--;
        this.notifyAll();
    }

    public synchronized boolean finalizado() {
        return (totalPag == cantPagEscritas);
    }

    public int getCantPagEscritas() {
        return cantPagEscritas;
    }

    public int getTotalPag() {
        return totalPag;
    }

}

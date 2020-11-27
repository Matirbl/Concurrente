package tpo2_2dointento;

public class Cajera implements Runnable {

    private boolean disponible;
    private Cinta caja;
    private Reloj reloj;

    public Cajera(Cinta caja, Reloj reloj) {
        this.disponible = true;
        this.caja = caja;
        this.reloj = reloj;
    }

    public synchronized void cerrar() {
        // desactiva la cajera
        this.disponible = false;
    }

    public void run() {
        while (this.disponible) {
            this.caja.sacarProducto();
            this.caja.finalizarCompra();
        }

        System.err.println("FIN " + Thread.currentThread().getName());
    }

}

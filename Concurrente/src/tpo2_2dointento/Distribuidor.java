package tpo2_2dointento;

public class Distribuidor implements Runnable {

    private Deposito unDeposito;
    private boolean esActivo;

    public Distribuidor(Deposito unDep) {
        this.esActivo = true;
        unDeposito = unDep;
    }

    public synchronized void cerrar() {
        // desactiva el distribuidor
        this.esActivo = false;
    }

    public void run() {
        while (esActivo) {
            this.unDeposito.sacarProducto();
            Tiempo.esperar(5);
        }

        System.err.println("FIN " + Thread.currentThread().getName());
    }
}

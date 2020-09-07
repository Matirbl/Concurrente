package tpo2_2dointento;

public class Reloj implements Runnable {

    private boolean esActivo;
    private int timer;
    private int contadorDias;

    public Reloj(int t) {
        this.timer = t;
        this.esActivo = true;
        this.contadorDias = 0;
    }

    public synchronized void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public synchronized void cerrar() {
        this.esActivo = false;
    }

    public synchronized boolean esActivo() {
        return esActivo;
    }
    
    public synchronized void incrementar() {
            this.contadorDias++;
    }

    public synchronized int getContadorDias() {
        return contadorDias;
    }
    
    public synchronized void resetear() {
        this.contadorDias = 0;
    }

    
    
    public void run() {
        this.incrementar();
        Tiempo.esperar(timer);
        this.cerrar();
        System.err.println("FIN " +Thread.currentThread().getName());
    }
}

package TPO2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposito {

    private boolean necesitoProductos;
    private Semaphore mutex;

    public Deposito() {
        this.necesitoProductos = false;
        this.mutex = new Semaphore(0);
    }

    public void llenarDeposito() {
        if (this.necesitoProductos) {
            try {
                this.mutex.acquire();
                this.necesitoProductos = false;
            } catch (InterruptedException ex) {
                Logger.getLogger(Deposito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void avisarDistribuidor() {
        this.necesitoProductos = true;
        this.mutex.release();
    }
    
    public boolean getNecesitoProductos() {
        return this.necesitoProductos;
    }
}

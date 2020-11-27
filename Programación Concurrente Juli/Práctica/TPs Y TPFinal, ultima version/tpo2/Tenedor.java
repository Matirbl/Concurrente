package tpo2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author jdominguez
 */
public class Tenedor {

    private Semaphore tenedor;

    public Tenedor() {
        this.tenedor = new Semaphore(1, true);
    }

    public Semaphore getTenedor() {
        return tenedor;
    }

    public void setTenedor(Semaphore tenedor) {
        this.tenedor = tenedor;
    }
}

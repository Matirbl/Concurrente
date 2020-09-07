package TPO2;

import java.util.concurrent.Semaphore;

public class Gerente implements Runnable {

    private Producto[] productos;
    private boolean esActivo;
    private Deposito deposito;

    public Gerente(Producto[] unosProductos, Deposito dep) {
        productos = unosProductos;
        esActivo = true;
        deposito = dep;
    }

    public void desactivar() {
        esActivo = false;
    }

    public boolean verificarStock() {
        //devuelve True si existe al menos un producto sin stock, false en caso conrtario
        int i = 0, longitud = productos.length;
        boolean parar = false;

        while (i < longitud && !parar) {
            if (productos[i].getStock() <= 0) {
                parar = true;
            }
            i++;
        }
        return parar;
    }

    public void run() {
        while (esActivo) {
            if (this.verificarStock()) {
                System.out.println("GERENTE >> NO HAY STOCK!!!!");
                this.deposito.avisarDistribuidor();
            } 
        }
        
    }
}

package TPO2;
import utiles.Aleatorio;

public class Distribuidor implements Runnable {

    private Producto[] productos;
    private boolean esActivo;
    private Deposito deposito;

    public Distribuidor(Producto[] arreglo, Deposito dep) {
        productos = arreglo;
        esActivo = true;
        this.deposito = dep;

    }

    private void entregarMercaderia() {
        // Repone stock de todos los productos sin stock
        System.out.println("El distribuidor: " + Thread.currentThread().getName() + " comenzo a entregar mercaderia");
        for (int i = 0; i < this.productos.length; i++) {
            if (productos[i].getStock() <= 0) {
                int delay = (Aleatorio.intAleatorio(1, 5));
                int cantidadProductos = (Aleatorio.intAleatorio(1, 5));
                productos[i].sumarStock(cantidadProductos);
                // simulo la entrega del producto al supermercado
                Tiempo.esperar(delay);
            }

        }
        System.out.println("El distribuidor: " + Thread.currentThread().getName() + "dejo de entregar mercaderia");
    }

    public void desactivar() {
        esActivo = false;
    }

    public void run() {
        while (esActivo) {
            if (this.deposito.getNecesitoProductos()) {
                this.deposito.llenarDeposito();
                this.entregarMercaderia();
            }
        }
    }

}

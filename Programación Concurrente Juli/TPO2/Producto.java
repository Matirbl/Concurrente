package TPO2;

import utiles.Aleatorio;

public class Producto {

    private int precio;
    private int delay;
    private boolean enOferta;
    private int stock;

    public Producto(int unDelay, boolean unaOferta, int unPrecio) {
        this.delay = unDelay;
        this.precio = unPrecio;
        this.enOferta = unaOferta;
        this.stock = Aleatorio.intAleatorio(5, 20);

    }

    public synchronized int tomarPrecio() {
        int temporal = 0;
        if (this.enOferta) {
            temporal = 10 * (precio / 100);//10 es el porcentaje de descuento
        }
        return precio - temporal;
    }

    public void pasarProducto() {
        Tiempo.esperar(delay);
    }

    public synchronized boolean isEnOferta() {
        return enOferta;
    }

    public synchronized void setPrecio(int precio) {
        this.precio = precio;
    }

    public synchronized void setEnOferta(boolean enOferta) {
        this.enOferta = enOferta;
    }

    public synchronized void setStockMinimo(int stock) {
        this.stock = Aleatorio.intAleatorio(5, 20);
    }

    public synchronized Producto obtenerUnidad() {
        
        this.stock = stock - 1;
        return this;
    }

    public synchronized void sumarStock(int cantidad) {
        this.stock += cantidad;
    }

    public int getStock() {
        return stock;
    }

}

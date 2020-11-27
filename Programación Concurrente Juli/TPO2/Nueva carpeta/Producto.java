package tpo2_2dointento;

public class Producto {

    private int precio;
    private int delay;
    private boolean enOferta;
    private int stock;
    private final int STOCK_MINIMO;

    public Producto(int precio, int delay, boolean enOferta, int cantidad, int minimo) {
        this.precio = precio;
        this.delay = delay;
        this.enOferta = enOferta;
        this.stock = cantidad;
        this.STOCK_MINIMO = minimo;
    }

    public int getStockMinimo() {
        // no necesita sincronizacion, es una variable estatica
        return STOCK_MINIMO;
    }

    public synchronized int getPrecio() {
        int desc = 0;
        if (this.enOferta) {
            desc = (precio * 10) / 100;
        }

        return (this.precio - desc);
    }

    public synchronized int getDelay() {
        return delay;
    }

    public synchronized int getStock() {
        return stock;
    }

    public synchronized Producto tomarProducto() {
        Producto p = null;
        if (this.stock - 1 >= 0) {
            p = this;
        }

        return p;
    }

    public synchronized void setStockMinimo(int stock) {
        this.stock = Aleatorio.intAleatorio(5, 20);
    }

    public synchronized void sumarStock(int cant) {
        this.stock += cant;

    }

    public synchronized void setEnOferta(boolean enOferta) {
        this.enOferta = enOferta;
    }
    
    

}

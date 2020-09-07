package tpo2_2dointento;

public class Gerente implements Runnable {

    private Producto[] productos;
    private boolean esActivo;
    private Deposito unDeposito;
    private Reloj reloj;

    public Gerente(Producto[] productos, Deposito deposit, Reloj r) {
        this.esActivo = true;
        this.productos = productos;
        this.unDeposito = deposit;
        this.reloj = r;
    }

    public void verStock() {
        //devuelve True si existe al menos un producto sin stock, false en caso conrtario

        for (int i = 0; i < productos.length; i++) {
            if (productos[i].getStock() <= productos[i].getStockMinimo()) {
                unDeposito.llamarDistribuidor(productos[i]);
            }
        }

    }

    public synchronized void cerrar() {
        // desactiva el gerente 
        this.esActivo = false;
    }

    public synchronized void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public synchronized void actualizarOferta() {
        // cargo el supermercado con nuevas ofertas
        for (int i = 0; i < productos.length; i++) {
            productos[i].setEnOferta(Aleatorio.intAleatorio(0, 2) == 1);
        }
    }
    
    public void run() {
        while (esActivo) {
            this.verStock();
            if (reloj.getContadorDias() >= 7) {
                System.err.println(Thread.currentThread().getName() +" >> ES HORA DE ACTUALIZAR LAS OFERTAS");
                this.actualizarOferta();
                this.reloj.resetear(); // vuelve el contador a 0
            }
            Tiempo.esperar(2);
        }
        System.err.println("FIN " + Thread.currentThread().getName());
    }
}

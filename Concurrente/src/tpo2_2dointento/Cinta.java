package tpo2_2dointento;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cinta {

    private Semaphore mutex;
    private int cantClientesEnFila;
    private final int nroCaja;
    private boolean disponible;
    private Producto[] cinta;
    private int prodsEnCinta;
    private int precio;
    private boolean cargaCompleta;

    public Cinta(int id) {
        this.nroCaja = id;
        this.disponible = true;
        this.cinta = new Producto[10];
        this.prodsEnCinta = -1;
        this.mutex = new Semaphore(1);
        this.cargaCompleta = false;
        this.cantClientesEnFila = 0;
    }

    public int getNroCaja() {
        return nroCaja;
    }

    public void entrar() throws InterruptedException {
        // adquiere el permiso para bloquear la cinta
        // suma un contador para tener referencia de los clientes que estan en la fila
        this.enEspera();
        this.mutex.acquire();
    }
    
    private synchronized void enEspera() {
        this.cantClientesEnFila++;
    }

    public synchronized boolean estaLleno() {
        // devuelve true si la cinta esta lleno o falso en caso contrario
        boolean res = false;
        if (prodsEnCinta == cinta.length - 1) {
            res = true;

        }
        return res;
    }

    public synchronized boolean estaVacio() {
        // devuelve true si la cinta esta vacia o falso en caso contrario
        boolean res = false;
        if (prodsEnCinta < 0) {
            res = true;

        }
        return res;
    }

    private synchronized void esperarCajera() {
        try {
            System.out.println(Thread.currentThread().getName() + " >> ESPERO CAJERA");
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void dejarProducto(Producto p) throws InterruptedException {
        // coloca un producto en la cinta 
        while (estaLleno() && this.disponible) { 
            this.esperarCajera();
        }
        if (disponible) {
            prodsEnCinta++;
            this.cinta[prodsEnCinta] = p;
            Tiempo.esperar(this.cinta[prodsEnCinta].getDelay());
            System.out.println(Thread.currentThread().getName() + " PUSO PRODUCTO EN CINTA " + this.nroCaja + ". TOTAL= " + (this.prodsEnCinta + 1));
            this.notifyAll();  // avisa a las cajeras
        }
    }

    public synchronized void finalizarCarga() {
        // notifica a la cajera que termino de cargar el carro en la cinta
        cargaCompleta = true;
    }

    public synchronized void finalizarCompra() {
        // notifica al cliente que termino de procesar su carro
        // tambien libera el permiso para que otro cliente pueda entrar a la caja
        if (cargaCompleta && this.estaVacio()) {
            System.out.println("El total de la compra es " + precio);
            this.precio = 0;
            this.cargaCompleta = false;
            this.cantClientesEnFila--;
            this.mutex.release();
        } 
    }

    private synchronized void esperarCliente() {
        try {
            System.out.println(Thread.currentThread().getName() + " >> ESPERO CLIENTE");
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void sacarProducto() {
        // consume mientras hayan productos
        // tambien notifica a los clientes para que coloquen mas productos
        while (estaVacio() && this.disponible) {
            this.esperarCliente();
        }
        if (disponible) {
            System.out.println(Thread.currentThread().getName() + " >> SAQUE UN PRODUCTO DE LA CINTA " + this.nroCaja);
            this.precio += this.cinta[prodsEnCinta].getPrecio();
            Tiempo.esperar(this.cinta[prodsEnCinta].getDelay());
            prodsEnCinta--;
            this.notifyAll();//aviso al cliente
        }
    }

    public synchronized void cerrar() {
        // cerro el supermercado
        // notifica a las cajeras y a los clientes que deben irse 
        this.disponible = false;
        for (int i = 0; i < this.cantClientesEnFila; i++) {
            System.err.println("SUELTO UN PERMISO PARA QUE EL CLIEnTE SE VAYA");
            this.mutex.release();
        }
        this.cantClientesEnFila = 0;
        this.notifyAll();  // notifico a las cajeras que cierren sus cajas
    }

    public synchronized void abrir() {
        this.disponible = true;
    }

}

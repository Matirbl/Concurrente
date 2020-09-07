/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo2_2dointento;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposito {

    private boolean disponible;
    private Producto[] productos;
    private int cantProductos;

    public Deposito() {
        this.disponible = true;
        productos = new Producto[10];
        cantProductos = -1;
    }

    public boolean estaLleno() {
        // devuelve true si el buffer esta lleno o falso en caso contrario
        boolean res = false;
        if (cantProductos == productos.length - 1) {
            res = true;

        }
        return res;
    }

    public boolean estaVacio() {
        // devuelve true si la cinta esta vacia o falso en caso contrario
        boolean res = false;
        if (cantProductos < 0) {
            res = true;

        }
        return res;
    }

    private synchronized void esperarDistribuidor() {
        try {
            System.out.println(Thread.currentThread().getName() + " >> ESPERO DISTRIBUIDOR");
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void llamarDistribuidor(Producto p) {
        // coloca productos sin stock en el buffer
        // notifica al distribuidor para que reponga stock
        while (estaLleno() && disponible) {
            this.esperarDistribuidor();
        }
        if (disponible) {
            cantProductos++;
            this.productos[cantProductos] = p;
            Tiempo.esperar(this.productos[cantProductos].getDelay());
            System.out.println(Thread.currentThread().getName() + " PUSO PRODUCTO REQUERIDO EN DEPOSITO, TOTAL: " + (this.cantProductos+1));

            this.notifyAll();
        }
    }

    private synchronized void esperarGerente() {
        try {
            System.out.println(Thread.currentThread().getName() + " >> ESPERO GERENTE");
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void sacarProducto() {
        // consume mientras hayan productos
        // renuva stock aleatoriamente
        while (estaVacio() && this.disponible) {
            this.esperarGerente();
        }
        if (this.disponible) {
            System.out.println(Thread.currentThread().getName() + " >> LE SUMO STOCK A UN PRODUCTO ");
            
            this.productos[cantProductos].sumarStock(Aleatorio.intAleatorio(1, 5));
            Tiempo.esperar(this.productos[cantProductos].getDelay());
            cantProductos--;
            this.notifyAll();//aviso al cliente
        }
    }

    public synchronized void cerrar() {
        // notifica al gerente y a los distribuidores que cierra el super
        this.disponible = false;
        this.notifyAll();
    }

    public synchronized void abrir() {
        this.disponible = true;
    }
}

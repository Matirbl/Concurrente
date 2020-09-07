package tpo2_2dointento;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable {

    private Producto[] catalogo;
    private Producto[] miCarro;
    private Cinta caja;

    public Cliente(Producto[] catalogo, Cinta caja) {
        this.catalogo = catalogo;
        this.miCarro = new Producto[3];
        this.caja = caja;
    }

    private boolean comprar() {
        int longitud = this.miCarro.length;
        int i = 0;
        boolean parar = false;

        System.out.println(Thread.currentThread().getName() + " >> COMENZE A COMPRAR");
        while (i < longitud && !parar) {
            Producto p = this.catalogo[Aleatorio.intAleatorio(1, catalogo.length)].tomarProducto();
            if (p == null) {
                parar = true;
            } else {
                this.miCarro[i] = p;
                i++;
            }
        }

        System.out.println(Thread.currentThread().getName() + " >> TERMINE DE COMPRAR. TOTAL= " + this.miCarro.length);

        return !parar;
    }

    public void colocarProductos() {
        int i = 0;
        while (i < miCarro.length) {
            try {
                Producto p = miCarro[i];
                this.caja.dejarProducto(p);
                i++;
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(Thread.currentThread().getName() + " TERMINO DE COLOCAR SUS PRODUCTOS");
    }

    private void entrar() {
        try {
            this.caja.entrar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        boolean pudeComprar = this.comprar();
        if (pudeComprar) {
            this.entrar();  // tomo el permiso de la cinta 
            this.colocarProductos();  //  coloco los productos en la cinta
            this.caja.finalizarCarga();  // aviso a la cajera que termine de cargar mis productos
        } else {
            System.out.println(Thread.currentThread().getName() +" >> ME FUI,nO EnCOnTRE LO QUE BUSCABA ");
        }
        System.err.println("FIN " + Thread.currentThread().getName());
    }
}

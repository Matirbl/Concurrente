package TPO2;

import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cajera implements Runnable {

    private Cliente unCliente;
    private String nombre;
    private boolean esActivo;
    private boolean estoyProcesando;
    
    public Cajera(String nombre) {
        this.nombre = nombre;
        this.unCliente = null;
        this.esActivo = true;
        this.estoyProcesando = false;
        
    }

    public void desactivar() {
        esActivo = false;
    }

    public void setCliente(Cliente c) {
        if (!this.estoyProcesando) {
            this.unCliente = c;
        }
    }
    
    public synchronized void esperarCliente() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cajera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (this.esActivo) {
            if (this.unCliente != null && this.unCliente.yaCompre()) {
                this.estoyProcesando = true;
                
                //Atiende y espera
                System.out.println("La " + this.nombre + " comenzó a procesar la compra del cliente" + this.unCliente.getNombre());
                
                int carroLong = this.unCliente.getCarroCompra().length;
                int total = 0;
                for (int i = 0; i < carroLong; i++) {
                    unCliente.getCarroCompra()[i].pasarProducto();

                    total += this.unCliente.getCarroCompra()[i].tomarPrecio();
                }
                this.estoyProcesando = false;
                System.out.println("El total de la compra del cliente " + this.unCliente.getNombre() + "es: " + total);
                System.out.println("La " + this.nombre + " termino de procesar la compra de "
                        + this.unCliente.getNombre());
            } else 
                System.out.println("NO ESTOY LISTA " +this.nombre);
                this.esperarCliente();
            }
        }
    }


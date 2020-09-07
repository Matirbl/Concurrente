/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class CajeraThread extends Thread {

    private String nombre;
    private ClienteSuper cliente;
    private long initialTime;

    public CajeraThread(String nombre, ClienteSuper cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    public String getNombre() {
        return nombre;
    }

    public ClienteSuper getCliente() {
        return cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }
// Constructor, y metodos de acceso

    public void run() {
        System.out.println("La cajera " + this.nombre
                + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + " del cliente "
                    + this.cliente.getNombre() + "->Tiempo: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000
                    + "seg");
        }
        System.out.println("La cajera" + this.nombre + "HA TERMINADO DE PROCESAR" + this.cliente.getNombre() + " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
    }

    private void esperarXsegundos(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(CajeraThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

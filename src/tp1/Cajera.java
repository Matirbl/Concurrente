/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cajera {

    private String nombre;
    int[] carroCompra;
// Agregar Constructor, y métodos de acceso

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    public void procesarCompra(ClienteSuper cliente, long timeStamp) {
        System.out.println("La cajera " + this.nombre
                + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + " ->Tiempo: "
                    + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        }
        System.out.println("La cajera " + this.nombre
                + " HA TERMINADO DE PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - timeStamp) / 1000
                + "seg");
    }

    public void esperarXsegundos(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cajera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }
}

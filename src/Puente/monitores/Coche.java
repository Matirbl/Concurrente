/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puente.monitores;


import java.util.Random;

public class Coche extends Thread {
    /**
     * true: norte
     * false: sur
     */
    private int idCoche;
    private Puente puente;
    private boolean direccion;

    public Coche(Puente puente, boolean direccion, int idCoche) {
        this.direccion = direccion;
        this.idCoche = idCoche;
        this.puente = puente;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        int t = r.nextInt(1000);
        while (true) {
            if (direccion) { // si pasa por el norte
                puente.entrarCoche(direccion);
                System.out.println("Coche " + idCoche + " entro por NORTE");
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Coche " + idCoche + " saliendo por NORTE");
                puente.salirCoche(direccion);
            } else { // si pasa por el sur
                puente.entrarCoche(direccion);
                System.out.println("Coche " + idCoche + " entro por SUR");
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Coche " + idCoche + " saliendo por SUR");
                puente.salirCoche(direccion);
            }
            direccion = !direccion;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    @Override
    public String toString() {
        return "idCoche = " + idCoche + ", direccion = " + direccion;
    }
}


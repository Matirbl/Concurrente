/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utiles.Aleatorio.intAleatorio;
import utiles.TextoColorido;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Colectivo {

    private CyclicBarrier ir;
    private CyclicBarrier llegar;
    private int capacidad;
    private TextoColorido color;

    public Colectivo(int capacidad) {
        this.capacidad = capacidad;
        //se le suma uno para el hilo conductor
        ir = new CyclicBarrier(capacidad + 1);
        llegar = new CyclicBarrier(capacidad + 1);
        color = new TextoColorido();
    }

    //metodos utilizados por los hilos pasajeros
    public void subir(Persona p) {
        try {
            ir.await();
        } catch (Exception e) {
        }

    }

    public void bajar(Persona p) {
        try {
            llegar.await();
        } catch (Exception e) {
        }
    }

    //metodos utilizados por el hilo conductor
    public void iniciarRecorrido(ColectivoConductor c) {

        try {
            System.out.println(color.getCyan()
                    + "El colectivo manejado por el chofer "
                    + c.getIdConductor() + " espera a los pasajeros");
            ir.await();
        } catch (Exception e) {
        }

    }

    public void recorrido(ColectivoConductor c) {
        try {
            System.out.println(color.getCyan()
                    + "El colectivo manejado por el chofer "
                    + c.getIdConductor() + " INICIO EL RECORRIDO");
            Thread.sleep(3000);
        } catch (Exception e) {
        }

    }

    public void volverALaEstacion(ColectivoConductor c) {
        try {
            System.out.println(color.getCyan()
                    + "El colectivo manejado por el chofer "
                    + c.getIdConductor() + " SE ENCUENTRA VOLVIENDO");
            Thread.sleep(3000);
        } catch (Exception e) {
        }

    }

    public void finalizarRecorrido(ColectivoConductor c) {
        try {
            System.out.println(color.getCyan()
                    + "El colectivo manejado por el chofer "
                    + c.getIdConductor() + " llegó al acuario natural");

            llegar.await();
        }catch (Exception e) {
        }
    }

}

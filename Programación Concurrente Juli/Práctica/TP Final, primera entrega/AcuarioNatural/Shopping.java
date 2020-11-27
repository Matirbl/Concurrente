/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.Semaphore;
import utiles.TextoColorido;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Shopping {

    private TextoColorido color;
    private boolean abierto;
    private Semaphore capacidad;
    private Semaphore mutex;
    private Semaphore mutexCajaA;
    private Semaphore mutexCajaB;
    private Semaphore mutexIngresar;
    private Semaphore mutexSalir;

    public Shopping(int capacidad) {
        //la capacidad es necesaria para no saturar esta actividad,
        //y lograr que las personas disfruten todo el parque
        abierto = true;
        mutex = new Semaphore(1, true);
        this.capacidad = new Semaphore(capacidad);
        this.mutexIngresar = new Semaphore(1, true);
        this.mutexSalir = new Semaphore(1, true);
        this.mutexCajaA = new Semaphore(1, true);
        this.mutexCajaB = new Semaphore(1, true);
        color = new TextoColorido();

    }

    public void irAlShoping(Persona p) {
        if (getAbierto() && capacidad.tryAcquire()) {
            ingresar(p);
            comprar(p);
            salir(p);
        } else {
            System.out.println(color.getRojo() + p.getIdPersona() + " quiso ingresar al Shopping pero no está disponible");
        }
    }

    public void ingresar(Persona p) {
        try {
            mutexIngresar.acquire();
            System.out.println(color.getRojo() + p.getIdPersona() + " ingresó al SHOPPING");
            Thread.sleep(1000);
            mutexIngresar.release();
        } catch (Exception e) {
        }
    }

    public void comprar(Persona p) {
        int compra = intAleatorio(2, 25);
        try {
            if (compra < 10) {
                //caja A (rapida)
                this.mutexCajaA.acquire();
                System.out.println(color.getRojo() + p.getIdPersona() + " abonando en caja A");
                Thread.sleep(1000);
                this.mutexCajaA.release();
            } else {
                //caja B
                this.mutexCajaB.acquire();
                System.out.println(color.getRojo() + p.getIdPersona() + " abonando en caja B");
                Thread.sleep(3000);
                this.mutexCajaB.release();
            }
        } catch (Exception e) {
        }
    }

    private void salir(Persona p) {
        try {
            this.mutexSalir.acquire();
            System.out.println(color.getRojo() + p.getIdPersona() + " está saliendo del SHOPPING");
            Thread.sleep(1000);
            this.mutexSalir.release();
        } catch (Exception e) {
        }
    }

    public boolean getAbierto() {
        boolean salida = false;
        try {
            mutex.acquire();
            salida = abierto;
            mutex.release();
        } catch (Exception e) {
        }
        return salida;
    }

    public void setAbierto(boolean abierto) {
        try {
            mutex.acquire();
            this.abierto = abierto;
            mutex.release();
        } catch (Exception e) {
        }
    }

}

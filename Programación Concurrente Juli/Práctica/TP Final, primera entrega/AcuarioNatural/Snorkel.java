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
public class Snorkel {

    private Semaphore equiposCompletos;//cant de equipos completos de snorkel para realizar la actividad
    private Semaphore solicitarEquipo; //para avisar al hilo SnorkelAsistente que necesitan equipo
    private Semaphore mutexEquiparse; //para garantizar consistencia al momento de solicitar el equipo requerido para realizar la actividad
    private Semaphore mutex; 
    private Semaphore snorkel;
    private Semaphore salvavidas;
    private Semaphore patasDeRana;
    private boolean abierto;

    private final TextoColorido color;//para diferenciar los mensajes de los otros ejercicios

    public Snorkel(int equiposCompletos) {
        mutex = new Semaphore(1, true);
        this.equiposCompletos = new Semaphore(equiposCompletos);
        this.solicitarEquipo = new Semaphore(0);
        this.mutexEquiparse = new Semaphore(1, true);
        this.snorkel = new Semaphore(0);
        this.salvavidas = new Semaphore(0);
        this.patasDeRana = new Semaphore(0);
        abierto = true;
        color = new TextoColorido();

    }

    public void realizarSnorkel(Persona p) {
        if (getAbierto() && equiposCompletos.tryAcquire()) {
            System.out.println(color.getVioleta() + p.getIdPersona() + " se prepara para realizar Snorkel");
            prepararse(p);
            nadar(p);
            salir(p);
        } else {
            System.out.println(color.getVioleta() + p.getIdPersona() + " se va a disfrutar del parque porque todos los equipos de snorkel están siendo ocupados");
        }
    }

    public void prepararse(Persona p) {
        System.out.println(color.getVioleta() + p.getIdPersona() + " le pide el equipo al asistente");
        solicitarEquipo.release();
        try {
            mutexEquiparse.acquire();
            snorkel.acquire();
            salvavidas.acquire();
            patasDeRana.acquire();
            System.out.println(color.getVioleta() + p.getIdPersona() + " se coloco el equipo");
            mutexEquiparse.release();
        } catch (Exception e) {
        }

    }

    public void nadar(Persona p) {
        try {
            System.out.println(color.getVioleta() + p.getIdPersona() + " se encuentra nadando en la laguna con su equipo de snorkel");
            Thread.sleep(1000 * intAleatorio(2, 5));

        } catch (Exception e) {
        }

    }

    public void darEquipo(int idAsistente) {
        try {
            solicitarEquipo.acquire();
            System.out.println(color.getVioleta() + "El Asistente de snorkel " + idAsistente + " se encuentra dando el equipo para hacer snorkel en la laguna");
            Thread.sleep(1000);
            snorkel.release();
            salvavidas.release();
            patasDeRana.release();
        } catch (Exception e) {
        }
    }

    public void salir(Persona p) {
        try {
            System.out.println(color.getVioleta() + p.getIdPersona() + " termino de nadar ahora devuelve el equipo");
            Thread.sleep(1000 * intAleatorio(1, 2));
            equiposCompletos.release();
            System.out.println(color.getVioleta() + p.getIdPersona() + " se va a disfrutar del parque luego de nadar en la laguna");
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

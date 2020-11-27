/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.Semaphore;
import static utiles.Aleatorio.intAleatorio;
import utiles.TextoColorido;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Faro {

    private TextoColorido color;
    private int capacidad;
    private Semaphore escaleraCaracol;
    private Semaphore solicitarTobogan;//cuando la persona llega a la cima libera un permiso avisando al asistente del faro
    private Semaphore puedoTirarme;
    private int asignacion;
    private Semaphore mutex;
    private Semaphore mutexAsignacion;
    private Semaphore mutexToboganA;
    private Semaphore mutexToboganB;
    private boolean abierto;

    public Faro(int capacidadEscaleras) {
        boolean abierto = true;
        this.color = new TextoColorido();
        this.capacidad = capacidadEscaleras;
        this.escaleraCaracol = new Semaphore(capacidadEscaleras);
        this.solicitarTobogan = new Semaphore(0);
        this.puedoTirarme = new Semaphore(0);
        this.asignacion = 0;
        this.mutex = new Semaphore(1, true);//en este semaforo se usa para ingresar
        this.mutexAsignacion = new Semaphore(1, true);//en este semaforo "hacen la fila" por eso se declara en true, para respetar el orden de llegada
        this.mutexToboganA = new Semaphore(1, true);
        this.mutexToboganB = new Semaphore(1, true);
    }

    public void adminitrarToboganes() {
        try {
            solicitarTobogan.acquire();//liberan el permiso al llegar a la cima
            System.out.println(color.getAmarillo() + "El administrador de toboganes está viendo por donde se tirara");
            mutexAsignacion.acquire();
            asignacion = intAleatorio(0, 1);//0 se tira por A, 1 se tira por B
            mutexAsignacion.release();
            puedoTirarme.release();
        } catch (Exception e) {
        }
    }

    public void irAlFaro(Persona p) {
        if (getAbierto() && escaleraCaracol.tryAcquire()) {
            subirEscaleras(p);
            admirarDesdeLoAlto(p);
            avisarAlAdmin(p);
            tirarsePorTobogan(p);
            nadarEnLaPile(p);
        } else {
            //esto es para no sobrecargar una actividad
            System.out.println(color.getAmarillo() + p.getIdPersona() + " ve que el faro está muy lleno, y probará con otra actividad");
        }

    }
    

    public void subirEscaleras(Persona p) {
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se encuentra subiendo las escaleras");
            Thread.sleep(1000);
            escaleraCaracol.release();
        } catch (Exception e) {
        }

    }

    public void admirarDesdeLoAlto(Persona p) {
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se encuentra admirando desde lo alto el esplendor de una maravilla natural");
            Thread.sleep(1000 * intAleatorio(2, 5));
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se dirige a los toboganes luego de admirar el esplendor");
        } catch (Exception e) {
        }
    }

    public void avisarAlAdmin(Persona p) {
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " le avisa al administrador de toboganes que desea tirarse");
            Thread.sleep(1000);//simulo que le aviso
            solicitarTobogan.release();
        } catch (Exception e) {
        }
    }

    public void tirarsePorTobogan(Persona p) {
        try {
            puedoTirarme.acquire();//lo libera el admin cuando termina de realizar la asignacion
            System.out.println(color.getAmarillo() + p.getIdPersona() + " ya le fue asignado un tobogan, ahora procede a hacer la fila");
            if (meLargoPor()) {
                //asignacion == 0
                tirarsePorA(p);
            } else {
                //asignacion == 1
                tirarsePorB(p);
            }
        } catch (Exception e) {
        }
    }

    public void tirarsePorA(Persona p) {
        try {
            mutexToboganA.acquire();
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se largo por el tobogan A");
            Thread.sleep(1500);//simulo el tiempo que tarda en el tobogan hasta llegar a la pileta
            System.out.println(color.getAmarillo() + p.getIdPersona() + " llego a la pileta de abajo desocupando el tobogan A");
            mutexToboganA.release();
        } catch (Exception e) {
        }
    }

    public void tirarsePorB(Persona p) {
        try {
            mutexToboganB.acquire();
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se largo por el tobogan B");
            Thread.sleep(1500);//simulo el tiempo que tarda en el tobogan hasta llegar a la pileta
            System.out.println(color.getAmarillo() + p.getIdPersona() + " llego a la pileta de abajo desocupando el tobogan B");
            mutexToboganB.release();
        } catch (Exception e) {
        }
    }

    public boolean meLargoPor() {
        boolean salida = true;
        try {
            mutexAsignacion.acquire();
            if (asignacion == 1) {
                salida = false;//se larga por el tobogan B, caso contrario (asignacion == 0) se larga por A
            }
            mutexAsignacion.release();
        } catch (Exception e) {
        }
        return salida;
    }

    public void nadarEnLaPile(Persona p) {
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se encuentra nadando en la pileta");
            Thread.sleep(1000);//simulo el tiempo que disfruta de la pileta
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se va a disfrutar del resto del parque");
        } catch (Exception e) {
        }
    }

    public boolean getAbierto() {
        boolean salida=false;
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

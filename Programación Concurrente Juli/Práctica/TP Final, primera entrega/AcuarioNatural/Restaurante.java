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
public class Restaurante {

    private Reloj miReloj;
    private String nombre;
    private int ingresaron;
    private int capacidad;
    private TextoColorido color;

    private Semaphore mutex;
    private Semaphore lugarDisp;

    public Restaurante(String nombre, int capacidad, Reloj r) {
        this.nombre = nombre;
        miReloj = r;
        this.capacidad = capacidad;
        color = new TextoColorido();
        mutex = new Semaphore(1, true);
        lugarDisp = new Semaphore(capacidad, true);
    }

    public void ingresar(Persona p, int horaLlegada) {
        try {
            System.out.println(color.getVerde() + p.getIdPersona()
                    + " quiere ir al restaurante " + nombre
                    + " a las " + miReloj.obtenerHora());

            if (lugarDisp.tryAcquire()) {
                mutex.acquire();
                ingresaron++;
                System.out.println(color.getVerde() + p.getIdPersona()
                        + " es el " + ingresaron + "º en ingresar al restaurante: " + nombre);
                mutex.release();//la persona ya ingreso y va a ser atendida

                serAtendidoYComer(p, horaLlegada);
            } else {
                System.out.println(color.getVerde() + p.getIdPersona()
                        + " va a hacer la fila en el restaurante " + nombre);
                lugarDisp.acquire();//duerme en este sem o "se va a la cola de espera"
                /*
                esta es la cola en donde se queda esperando afuera del restaurante
                 */
                //ya ingresa si estamos aqui xq alguien salio y le dio un permiso
                mutex.acquire();
                System.out.println(p.getIdPersona()
                        + " SALIO DE LA COLA E INGRESARA al restaurante " +nombre
                        + " a las " + miReloj.obtenerHora());
                ingresaron++;
                System.err.println(nombre + " ingresaron " + ingresaron);
                mutex.release();
                serAtendidoYComer(p, horaLlegada);

            }
        } catch (Exception e) {
        }
    }

    private void serAtendidoYComer(Persona p, int horaLlegada) {
        /*
        de acuerdo a la hora es el mensaje que llamo
         */
        try {
            System.out.println(color.getVerde() + p.getIdPersona()
                    + " está SIENDO ATENDIDA en el restaurante "
                    + nombre + " a las " + miReloj.obtenerHora());
            if (horaLlegada <= 14) {

                p.setPuedeAlmorzar(false);
                almorzar(p.getIdPersona());
            } else {
                if (horaLlegada <= 17) {

                    p.setPuedeMerendar(false);
                    merendar(p.getIdPersona());

                }

            }
        } catch (Exception e) {
        }
    }

    private void merendar(int idPersona) {
        try {
            System.out.println(color.getVerde() + idPersona +
                    " MERENDANDO en el restaurante " + nombre +
                    " a las " + miReloj.obtenerHora());
            Thread.sleep(1000 * intAleatorio(1, 2));
            System.out.println(color.getVerde() + idPersona +
                    " TERMINO DE MERENDAR en el restaurante " 
                    + nombre + "a las " + miReloj.obtenerHora());
        } catch (Exception e) {
        }
    }

    private void almorzar(int idPersona) {
        try {
            System.out.println(color.getVerde() + idPersona +
                    " ALMORZANDO en el restaurante " + nombre);
            Thread.sleep(1000 * intAleatorio(2, 3));
            System.out.println(color.getVerde() + idPersona +
                    " TERMINO DE ALMORZAR en el restaurante " + nombre);
        } catch (Exception e) {
        }

    }

    public void salir(Persona p) {
        try {
            mutex.acquire();
            System.out.println(color.getVerde() + p.getIdPersona()
                    + " SALIENDO del restaurante " + nombre
                    + " a las " + miReloj.obtenerHora());

            ingresaron--;
            mutex.release();
            lugarDisp.release();

        } catch (Exception e) {
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIngresaron() {
        return ingresaron;
    }

    public void setIngresaron(int ingresaron) {
        this.ingresaron = ingresaron;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.ArrayBlockingQueue;
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
    private ArrayBlockingQueue puerta;
    private int cantEspEnLaPuerta;//cantidad de personas esperando fuera de la puerta

    private Semaphore mutex;
    private Semaphore mutexPuerta;
    private Semaphore lugarDisp;

    public Restaurante(String nombre, int capacidad, Reloj r) {
        this.nombre = nombre;
        miReloj = r;
        this.capacidad = capacidad;
        color = new TextoColorido();
        mutex = new Semaphore(1, true);
        lugarDisp = new Semaphore(capacidad, true);
        
        cantEspEnLaPuerta = 0;
        mutexPuerta = new Semaphore(1, true);
        puerta = new ArrayBlockingQueue(1, true);
        

    }

    public void hacerFila(Persona p) {
        /*
        ArrayBlockingQueue implementa la interfaz BlockingQueue, entre otras.
        es una estructura tipo FIFO, el primero en ingresar es el primero en salir
        Los metodos utilizados de ArrayBlockingQueue son put(Object e) y take()
        put inserta un elemento especifico al final de la cola,
        esperando si es necesario si la cola se encuentra llena
        
        take obtiene y remueve el elemento que se encontraba en el frente de la cola,
        espererando si es necesario si la cola se encouentra vacia
        
        se respeta el orden de llegada si se declara en true, como es el caso..
        
        La instancia de ArrayBlockingQueueen instanciada con una capacidad de 1 
        y decalara en true se utiliza para representar la puerta y que
        solo puede estar parado una persona en frente de ella, 
        el resto de las personas esperan atras de la primera que llego 
        respetando el orden de llegada ya que de declara en true.
        */
        try {
            mutexPuerta.acquire();
            cantEspEnLaPuerta++;
            if (cantEspEnLaPuerta == 1) {
                System.out.println(color.getRojo() + p.getIdPersona()
                        + " se encuentra en la PUERTA del restaurante haciendo la fila " + nombre);
            } else {
                System.out.println(color.getRojo() + p.getIdPersona()
                        + " se encuentra en la fila del restaurante " + nombre);

            }
            mutexPuerta.release();
            puerta.put(this);
            
            lugarDisp.acquire();//espero a que se desocupe un lugar
            //una vez aqui hay un lugar disponible por lo que salgo de la puerta
            //y voy a merendar/almorzar
            mutexPuerta.acquire();
            cantEspEnLaPuerta--;
            if (cantEspEnLaPuerta == 0) {
                System.out.println(color.getRojo() + p.getIdPersona()
                        + " era el ultimo que se encontraba en la fila del restaurante " + nombre);
            }
            mutexPuerta.release();
            System.out.println(color.getRojo() + p.getIdPersona()
                    + " SALIO DE LA COLA E INGRESARA al restaurante " + nombre
                    + " a las " + miReloj.obtenerHora());
            puerta.take();//sale de la puerta
        } catch (Exception e) {
        }
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
                hacerFila(p);
                mutex.acquire();
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
            System.out.println(color.getVerde() + idPersona
                    + " MERENDANDO en el restaurante " + nombre
                    + " a las " + miReloj.obtenerHora());
            Thread.sleep(1000 * intAleatorio(1, 2));
            System.out.println(color.getVerde() + idPersona
                    + " TERMINO DE MERENDAR en el restaurante "
                    + nombre + "a las " + miReloj.obtenerHora());
        } catch (Exception e) {
        }
    }

    private void almorzar(int idPersona) {
        try {
            System.out.println(color.getVerde() + idPersona
                    + " ALMORZANDO en el restaurante " + nombre);
            Thread.sleep(1000 * intAleatorio(2, 3));
            System.out.println(color.getVerde() + idPersona
                    + " TERMINO DE ALMORZAR en el restaurante " + nombre);
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

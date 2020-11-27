/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utiles.Aleatorio.intAleatorio;
import utiles.TextoColorido;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Carrera {

    private int h;//cantidad de gomones que se necesitan para iniciar la carrera
    //determina la cantidad de personas que participan, y por lo tanto el tamaño de la barrera
    private CarreraGomon[] gomones;

    private final TextoColorido color;//para diferenciar los mensajes de los otros ejercicios

    private ReentrantLock lock;
    private Condition esperandoGomones;
    private Condition personasEsperandoPertenencias;
    private Condition camionetaEsperando;
    private Condition trenEsperando;
    private boolean carreraDisponible;
    private boolean puedoDepositarPertenencias;
    private boolean pertenenciasDisponibles;
    private boolean abierto;

    private CyclicBarrier carrera;//barrera para iniciar la carrera al mismo tiempo
    private CyclicBarrier subirseAlTren;//barrera para esperar 15 pasajeros
    private CyclicBarrier bajarseDelTren;//barrera para esperar 15 pasajeros

    private int cantPersABordoDelTren;
    private int puesto;
    private int correspondiente;
    private int pertenencias;
    private int cantPreparandose;
    private int ingresaron;

    public Carrera() {
        lock = new ReentrantLock(true);
        esperandoGomones = lock.newCondition();
        personasEsperandoPertenencias = lock.newCondition();
        camionetaEsperando = lock.newCondition();
        trenEsperando = lock.newCondition();
        carreraDisponible = true;
        puedoDepositarPertenencias = false;
        pertenenciasDisponibles = false;
        cantPreparandose = 0;//va de 0 hasta 11
        ingresaron = 0;//va de 0 hasta 22
        abierto = true;
        carrera = new CyclicBarrier(11);//para las 11 personas, que largan la carrera al mismo tiempo
        subirseAlTren = new CyclicBarrier(15 + 1);//15 para los pasajeros, 1 para el tren
        bajarseDelTren = new CyclicBarrier(15 + 1);//15 para los pasajeros, 1 para el tren
        cantPersABordoDelTren = 0;
        /*
         h es la cantidad de gomonoes listos para iniciar la carrera,
         no puede ser mayor a la suma de ambos tipos de gomones
         la barrera se inicializa en h+1 ya que la caminioneta va a esperar
         a que la carrera se inicie para llevar las pertenencias al final
         */
        this.h = 7;//cantidad de gomones listos para iniciar la carrera
        //instancio y creo los gomones
        gomones = new CarreraGomon[h];
        for (int i = 0; i < gomones.length; i++) {
            if (i % 2 == 0) {
                gomones[i] = new CarreraGomon(i + 1, 2);//creo un gomon para dos con su id
            } else {
                gomones[i] = new CarreraGomon(i + 1, 1);//creo un gomon para uno con su id
            }
        }
        correspondiente = 0;
        puesto = 1;
        pertenencias = 0;
        color = new TextoColorido();
    }

    public void realizarCarrera(Persona p) {
        if (puedoRealizarCarrera()) {
            /*por dia solo da tiempo a 2 carreras*/
            irAlInicio(p);
            llegarAlInicio(p);
            descenderPorElRio();
            llegarAlFinal(p);
            retirarPertenencias(p);
            salir(p);
        } else {
            System.out.println(color.getAzul() + p.getIdPersona() + " se va a disfrutar del resto del Parque, porque la carrera no se encuetra disponible");
        }
    }

    public boolean puedoRealizarCarrera() {
        boolean salida;
        lock.lock();
        salida = ingresaron < 23 && abierto;
        lock.unlock();
        return salida;
    }

    public void ingresar() {
        lock.lock();
        ingresaron++;
        if (ingresaron == 23) {
            //en la carrera solo se permite que un grupo de 11 esté esperando
            //para que no todos vengan a hacer la fila y que puedan disfrutar
            //del resto del parque, una vez que el ultimo sale
            //se habilita de nuevo la carrera para otros dos grupos
            abierto = false;
        }
        lock.unlock();
    }

    public void irAlInicio(Persona p) {
        try {
            ingresar();
            if (cantPersABordoDelTren < 15) {
                //si hay lugar en el tren se sube y espera a que arranque
                subirseAlTren(p);
                subirseAlTren.await();
                bajarseDelTren.await();
                bajarseDelTren(p);
            } else {
                irEnBici(p);
            }
        } catch (Exception ex) {
        }
    }

    public void subirseAlTren(Persona p) {
        lock.lock();
        System.out.println(color.getAzul() + p.getIdPersona() + " se dirige al inicio EN TREN");
        cantPersABordoDelTren++;
        lock.unlock();
    }

    public void bajarseDelTren(Persona p) {
        lock.lock();
        System.out.println(color.getAzul() + p.getIdPersona() + " se bajó del TREN");
        cantPersABordoDelTren--;
        lock.unlock();
    }

    public void llegarAlInicio(Persona p) {
        lock.lock();
        try {
            while (!carreraDisponible) {
                esperandoGomones.await();
            }
            pasar();
            //una vez que la carrera está disponible guardan sus pertenencias
            guardarPertenencias(p);
            prepararse(p);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void pasar() {
        lock.lock();
        try {
            cantPreparandose++;
            if (cantPreparandose == 11) {
                carreraDisponible = false;
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void guardarPertenencias(Persona p) {
        lock.lock();
        try {
            System.out.println(color.getAzul() + p.getIdPersona() + " guardando sus pertenencias");
            pertenencias++;
            if (pertenencias == 11) {
                camionetaEsperando.signal();//aviso a la camioneta que ya puede llevar las pertenencias
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void prepararse(Persona p) {
        lock.lock();
        try {
            if (gomones[correspondiente].seEncuentraLleno()) {
                /*si se encuentra lleno se sube a otro gomon*/
                correspondiente++;
            }
            gomones[correspondiente].subirse(p);
            p.setAsignacionDelGomon(correspondiente);
            //asigno a la persona en cual gomon se sento para luego poder saber de cual bajarlo
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void recorrido() {
        try {
            //simula el tiempo que dura el viaje
            //lo utilizan los hilos: tren y camioneta
            Thread.sleep(1500 * intAleatorio(2, 3));
        } catch (Exception e) {
        }
    }

    public void descenderPorElRio() {
        try {
            carrera.await();//espero a los otros gomones

            Thread.sleep(1500 * intAleatorio(3, 4));
        } catch (Exception e) {
        }
    }

    public void irEnBici(Persona p) {
        try {
            //simula el tiempo que dura el viaje en bici
            System.out.println(color.getAzul() + p.getIdPersona() + " se va en BICI");
            Thread.sleep(1500 * intAleatorio(4, 5));
        } catch (Exception e) {
        }
    }

    public void llegarAlFinal(Persona p) {
        lock.lock();
        try {
            if (!gomones[p.getAsignacionDelGomon()].llego()) {
                gomones[p.getAsignacionDelGomon()].setPuesto(puesto);
                gomones[p.getAsignacionDelGomon()].llegaron();
                puesto++;
            }
            gomones[p.getAsignacionDelGomon()].bajarse(p.getIdPersona());
            cantPreparandose--;
            if (cantPreparandose == 0) {
                //cuando el ultimo llega, la camioneta puede depositar las pertenencias
                puedoDepositarPertenencias = true;
                camionetaEsperando.signal();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }

    public void retirarPertenencias(Persona p) {
        lock.lock();
        try {
            while (!pertenenciasDisponibles) {
                personasEsperandoPertenencias.await();
            }
            System.out.println(color.getAzul() + p.getIdPersona() + " retirando sus pertenencias");
            pertenencias--;
            if (pertenencias == 0) {
                /*si el el ultimo retiro sus pertenencias enconces se habilita la siguiente carrera*/
                habilitarSigCarrera();
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void habilitarSigCarrera() {
        /*
        el ultimo hilo en llegar es el que habilita la siguiente carrera
         */
        lock.lock();
        try {
            System.out.println(color.getAzul() + "LLEVANDO GOMONES AL INICIO");
            for (int i = 0; i < gomones.length; i++) {
                gomones[i].setLlego(false);
            }
            puesto = 1;
            correspondiente = 0;
            carreraDisponible = true;
            trenEsperando.signal();
            esperandoGomones.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void salir(Persona p) {
        lock.lock();
        ingresaron--;
        if (ingresaron == 0) {
            abierto = true;
        }
        System.out.println(color.getAzul() + p.getIdPersona() + " se va a disfrutar del resto del parque acuatico");
        lock.unlock();
    }

//METODOS UTILIZADOS POR LOS HILOS: TREN Y CAMIONETA
    public void arrancarRecorrido() {
        try {
            while (!carreraDisponible && !abierto) {
                trenEsperando.await();
            }
            subirseAlTren.await();
            System.out.println(color.getAzul() + "EL TREN INICIO EL RECORRIDO");
        } catch (InterruptedException ex) {
            Logger.getLogger(Carrera.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            System.out.println(color.getAzul() + "EL TREN INICIO EL RECORRIDO, PORQUE LLEGARON TODOS LOS PASAJEROS");
        }
    }

    public void finalizarRecorrido() {
        lock.lock();
        try {
            System.out.println(color.getAzul() + "EL TREN LLEGO A DESTINO");
            bajarseDelTren.await();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void llevarPertenencias() {
        lock.lock();
        try {
            while (pertenencias < 11) {
                camionetaEsperando.await();
            }
            System.out.println(color.getRojo() + "*********** CARRERA INICIADA ***********");
            pertenenciasDisponibles = false;
            System.out.println(color.getAzul() + "LA CAMIONETA SE ENCUENTRA LLEVANDO LAS PERTENENCIAS");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void depositarPertenencias() {
        lock.lock();
        try {
            while (!puedoDepositarPertenencias) {
                camionetaEsperando.await();
            }
            System.out.println(color.getAzul() + "LA CAMIONETA DEPOSITO LAS PERTENENCIAS");
            pertenenciasDisponibles = true;
            puedoDepositarPertenencias = false;
            personasEsperandoPertenencias.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public boolean abierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        lock.lock();
        this.abierto = abierto;
        lock.unlock();
    }

}

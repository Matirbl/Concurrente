/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.laExposición;

/*
PREGUNTARRR
    si el nombre de las condiciones es correcto
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class Sala {

    /*
    recurso compartido entre los hilos: visitantes, criticos y responsables
    metodología de sincronización: ReentrantLock + Variables de condición
     */
    private int capacidad;
    private int cantPersonasIngresadas;
    private boolean criticoEsperando;
    private boolean criticoInspeccionando;
    private boolean responsableControlando;
    private final Lock lock;
    private Condition criticosEsperando;
    private Condition visitantesEsperando;
    private Condition responsablesEsperando;

    public Sala(int capacidad) {
        this.capacidad = capacidad;
        criticoEsperando = false;
        criticoInspeccionando = false;
        responsableControlando = false;
        lock = new ReentrantLock(true);
        criticosEsperando = lock.newCondition();
        visitantesEsperando = lock.newCondition();
        responsablesEsperando = lock.newCondition();

    }

    public void ingresarCritico() {
        System.err.println("El crítico desea ingresar");
        lock.lock();
        try {
            while (!salaVacia()) {
                criticoEsperando = true;
                criticosEsperando.await();
            }
            System.out.println("El crítico ingresó a la sala");
            cantPersonasIngresadas++;
            criticoEsperando = false;
            criticoInspeccionando = true;
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void salirCritico() {
        lock.lock();
        try {
            System.err.println("El crítico salió de la sala avisando a los visitantes y responsables");
            criticoInspeccionando = false;
            cantPersonasIngresadas--;
            //aviso a los visitantes que ya salí
            visitantesEsperando.signalAll();
            responsablesEsperando.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void pasear() {
        try {
            Thread.sleep(1000 * intAleatorio(2, 8));
        } catch (Exception e) {
        }
    }

    public void esperarTC() {
        try {
            Thread.sleep(500 * intAleatorio(3, 4));
        } catch (Exception e) {
        }
    }

    public void ingresarVisitante(int idVisitante) {
        System.out.println("El visitante " + idVisitante + " desea ingresar");
        lock.lock();
        try {
            while (criticoEsperando || criticoInspeccionando || salaLlena()) {
                visitantesEsperando.await();
            }
            System.err.println("El visitante " + idVisitante + " ingresó");
            cantPersonasIngresadas++;
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void ingresarResponsable() {
        System.out.println("Un responsable desea ingresar");
        lock.lock();
        try {
            while (criticoEsperando || criticoInspeccionando
                    || salaLlena() || responsableControlando) {
                visitantesEsperando.await();
            }
            System.err.println("Un responsable ingreso a la sala");
            responsableControlando = true;
            cantPersonasIngresadas++;
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void salirVisitante(int idVisitante) {
        lock.lock();
        try {
            System.err.println("El visitante " + idVisitante + " salió de la sala");
            salir();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void salirResponsable() {
        lock.lock();
        try {
            System.err.println("Un responsable salió a la sala");
            responsableControlando = false;
            salir();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void salir() {
        lock.lock();
        try {
            cantPersonasIngresadas--;
            criticosEsperando.signal();
            responsablesEsperando.signal();
            visitantesEsperando.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public boolean salaVacia() {
        lock.lock();
        boolean salida = false;
        try {
            if (cantPersonasIngresadas == 0) {
                salida = true;
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        return salida;
    }

    public boolean salaLlena() {
        /*
        devuelve true si la sala se encuentra llena
         */
        lock.lock();
        boolean respuesta = false;
        try {
            if (capacidad == cantPersonasIngresadas) {
                respuesta = true;
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        return (respuesta);
    }
}

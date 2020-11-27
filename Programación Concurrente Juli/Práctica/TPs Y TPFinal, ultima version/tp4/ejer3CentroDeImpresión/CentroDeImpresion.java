/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer3CentroDeImpresión;

/*
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class CentroDeImpresion {

    private int cantImpTipoA;//cant de impresoras de tipo A
    private int cantImpTipoB;//cant de impresoras de tipo B
    private int contDisponiblesTipoA;
    private int contDisponiblesTipoB;
    private Lock lock;
    private Condition trabajosTipoA;
    private Condition trabajosTipoB;

    public CentroDeImpresion(int cantImpTipoA, int cantImpTipoB) {
        this.cantImpTipoA = cantImpTipoA;
        this.cantImpTipoB = cantImpTipoB;
        contDisponiblesTipoA = cantImpTipoA;
        contDisponiblesTipoA = cantImpTipoB;
        lock = new ReentrantLock(true);
        trabajosTipoA = lock.newCondition();
        trabajosTipoB = lock.newCondition();

    }

    public void imprimir() {
        try {
            Thread.sleep(1000 * intAleatorio(4, 8));
        } catch (Exception e) {
        }
    }

    public void imprimirTipoA(int idUsuario) {
        //metodo utilizados por los hilos que deben imprimir trabajos 
        //con las impresoras de tipo A
        comenzarImpresionTipoA(idUsuario);
        imprimir();
        terminarImpresionTipoA(idUsuario);
    }

    public void imprimirTipoB(int idUsuario) {
        //metodo utilizados por los hilos que deben imprimir trabajos 
        //con las impresoras de tipo B
        comenzarImpresionTipoB(idUsuario);
        imprimir();
        terminarImpresionTipoB(idUsuario);
    }

    private boolean disponibleTipoA() {
        lock.lock();
        boolean salida = false;
        try {
            if (contDisponiblesTipoA > 0) {
                salida = true;
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        return salida;
    }

    private void comenzarImpresionTipoA(int idUsuario) {
        lock.lock();
        try {
            while (!disponibleTipoA()) {
                //mientras no tenga impresoras disponibles 
                //los trabajos deberan esperar
                trabajosTipoA.await();
            }
            contDisponiblesTipoA--;
            System.err.println("El usuario " + idUsuario
                    + " se encuentra imprimiendo un trabajo TIPO A");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    private void terminarImpresionTipoA(int idUsuario) {
        lock.lock();
        try {
            contDisponiblesTipoA++;
            System.err.println("El usuario " + idUsuario
                    + " finalizó un trabajo TIPO A");
            trabajosTipoA.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    private boolean disponibleTipoB() {
        lock.lock();
        boolean salida = false;
        try {
            if (contDisponiblesTipoB > 0) {
                salida = true;
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        return salida;
    }

    private void comenzarImpresionTipoB(int idUsuario) {
        lock.lock();
        try {
            while (!disponibleTipoB()) {
                //mientras no tenga impresoras disponibles 
                //los trabajos deberan esperar
                trabajosTipoB.await();
            }
            contDisponiblesTipoB--;
            System.err.println("El usuario " + idUsuario
                    + " se encuentra imprimiendo un trabajo TIPO B");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    private void terminarImpresionTipoB(int idUsuario) {
        lock.lock();
        try {
            contDisponiblesTipoB++;
            System.err.println("El usuario " + idUsuario
                    + " finalizó un trabajo TIPO B");
            trabajosTipoB.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

}

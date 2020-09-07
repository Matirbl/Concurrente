/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial2017;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClubVinos {

    private int miembros;
    private int jarras;
    private int levadura;
    private int jugo;
    private int unidadFermentacion;
    private Estacion estacion1;
    private Estacion estacion2;
    private Condition hayLugarEstacion, hayIngredientes, hayQueReponer, hayUtiles;

    private ReentrantLock lock;

    public ClubVinos(int n) {
        this.miembros = n;
        this.jarras = 6;
        this.levadura = 20;
        this.unidadFermentacion = 7;
        this.jugo = 15;
        hayLugarEstacion = lock.newCondition();
        hayIngredientes = lock.newCondition();
        hayQueReponer = lock.newCondition();
        hayUtiles = lock.newCondition();
    }

    public boolean hayUtiles() {
        return jarras >= 2 && unidadFermentacion >= 1;
    }

    public boolean hayIngredientes() {
        return levadura >= 1 && jugo >= 1;
    }

    public void tomarProductos() {
        lock.lock();
        while (!(hayUtiles() && hayIngredientes())) {
            if (!hayIngredientes()) {
                try {
                    hayQueReponer.signal();
                    hayIngredientes.await();
                } catch (InterruptedException ex) {

                }
            } else {
                try {
                    hayUtiles.await();
                } catch (InterruptedException ex) {
                }
            }
        }
        lock.unlock();
        synchronized (this) {
            jarras = jarras - 2;
            levadura--;
            jugo--;
        }
        entrarEstacion();

    }

    public void entrarEstacion() {
        lock.lock();
        while (!(estacion1.estaLibre() || estacion2.estaLibre())) {
            try {
                hayLugarEstacion.await();
            } catch (InterruptedException ex) {
            }
        }
        if (estacion1.estaLibre()) {
            estacion1.tomarEstacion();
            lock.unlock();
            estacion1.mezclar();
        } else {
            estacion2.tomarEstacion();
            lock.unlock();
            estacion2.mezclar();
        }
        synchronized (this) {
            jarras++;
        }
        hayUtiles.signal();
        hayLugarEstacion.signal();
        try {
            //Pasa vino a unidad de decantacion
            Thread.sleep(40000);
        } catch (InterruptedException ex) {
        }
        synchronized (this) {
            unidadFermentacion++;
        }
        hayUtiles.signal();
        try {
            //Decantar en segunda jarra y paso a botella
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        synchronized (this) {
            jarras++;
        }
        hayUtiles.signal();
    }

    public synchronized void entrarClub() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClubVinos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reponer() {
        try {
            this.hayQueReponer.await();
        } catch (InterruptedException ex) {

        }
        synchronized (this) {
            levadura += 20;
            jugo += 15;
        }
        this.hayIngredientes.signalAll();
    }

}

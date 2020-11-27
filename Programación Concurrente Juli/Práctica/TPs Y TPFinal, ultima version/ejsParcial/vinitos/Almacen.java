/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.vinitos;

import static utiles.Aleatorio.intAleatorio;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Almacen {

    private int estacionDeMezcla;//2
    private int jarraDeVino;//6 de 10 litros cada una
    private int unidadDeFermentacion;//7
    private boolean faltanIngredientes;
    private int envaseJugoFrutaAzucurado;//15, 5 litros cada envase
    private int paqueteLevaduraVino;//20, 10 litros de vino cada paquete
    private int semana;
    //para sincronizar
    private ReentrantLock lock;
    private Condition miemEsperandoIngredientes;
    private Condition miemEsperandoJarra;
    private Condition miemEsperandoSuVino;
    private Condition adminEsperando;

    public Almacen() {
        this.faltanIngredientes = false;
        this.estacionDeMezcla = 2;
        this.jarraDeVino = 6;
        this.unidadDeFermentacion = 7;
        this.envaseJugoFrutaAzucurado = 15;
        this.paqueteLevaduraVino = 20;
        this.semana = 0;
        lock = new ReentrantLock(true);
        miemEsperandoJarra = lock.newCondition();
        miemEsperandoIngredientes = lock.newCondition();
        miemEsperandoSuVino = lock.newCondition();
        adminEsperando = lock.newCondition();
    }

    public void hacerVino(int idMiembro) {
        iniciarPreparacion(idMiembro);
        mezclar(idMiembro);
        terminarDeMezclar(idMiembro);
    }

    public void iniciarPreparacion(int idMiembro) {
        lock.lock();
        try {
            while (!puedoPreparar()) {
                if (faltanIngredientes()) {
                    faltanIngredientes = true;
                    adminEsperando.signal();
                }
                miemEsperandoIngredientes.await();
            }
            estacionDeMezcla--;
            jarraDeVino--;
            envaseJugoFrutaAzucurado = envaseJugoFrutaAzucurado - 2;
            paqueteLevaduraVino--;
            unidadDeFermentacion--;
            System.out.println("Miembro " + idMiembro + " puede iniciar la mezcla");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void mezclar(int idMiembro) {
        try {
            System.out.println("Miembro " + idMiembro + " se encuentra mezclando");
            Thread.sleep(1000 * intAleatorio(3, 5));
        } catch (Exception e) {
        }
    }

    public void terminarDeMezclar(int idMiembro) {
        lock.lock();
        try {
            estacionDeMezcla++;
            while (jarraDeVino < 1) {
                System.out.println("Miembro " + idMiembro + " espera a que se desocupe una jarra");
                miemEsperandoJarra.await();
            }
            jarraDeVino--;
            System.out.println("Miembro " + idMiembro + " está pasando la mezcla a la unidad de fermentacion");
            Thread.sleep(1000);
            System.err.println("Miembro " + idMiembro + " termino de pasar la mezcla desocupando dos jarras y pasa a esperar q estén listos");

            jarraDeVino = jarraDeVino + 2;
            miemEsperandoJarra.signalAll();
            miemEsperandoSuVino.await();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public boolean puedoPreparar() {
        boolean salida = false;
        if (estacionDeMezcla > 0 && jarraDeVino > 1 && envaseJugoFrutaAzucurado >= 2
                && unidadDeFermentacion >= 1 && paqueteLevaduraVino >= 1) {
            salida = true;
        }
        return salida;
    }

    public boolean faltanIngredientes() {
        boolean salida = false;
        if (envaseJugoFrutaAzucurado < 4 || paqueteLevaduraVino < 4) {
            salida = true;
        }
        return salida;
    }

    public void reponerIngredientes() {
        lock.lock();
        try {
            while (!faltanIngredientes) {
                adminEsperando.await();
            }
            System.out.println("El administrador se encuentra reponiendo ingredientes");
            this.envaseJugoFrutaAzucurado = 20;
            this.paqueteLevaduraVino = 20;
            this.faltanIngredientes = false;
            Thread.sleep(1000 * intAleatorio(3, 6));
            System.err.println("El administrador termino de reponer ingredientes");
            Thread.sleep(250 * intAleatorio(3, 6));
            miemEsperandoIngredientes.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void pasarSemana() {
        lock.lock();
        try {
            semana++;
            System.err.println("Semana: " + semana);
            if (semana % 4 == 0) {
                miemEsperandoSuVino.signalAll();
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
    public void tomarVino(int idMiembro) {
        try {
            System.out.println("El miembro " + idMiembro + " se encuentra probando vino");
            Thread.sleep(1000 * intAleatorio(8, 10));
        } catch (Exception e) {
        }
        lock.lock();
        unidadDeFermentacion++;
        lock.unlock();
    }
}
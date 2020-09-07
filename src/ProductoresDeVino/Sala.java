/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoresDeVino;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sala {

    private int estacionDeMezcla;
    private int jarraDeVino;
    private int unidadDeFermentacion;
    private boolean faltanIngredientes;
    private int envaseJugoAzucarado;
    private int paqueteLevadura;
    private int semana;
    private ReentrantLock lock;
    private Condition esperandoIngrediente, esperandoJarra, esperandoVino, adminEsperando;

    public Sala() {
        faltanIngredientes = false;
        estacionDeMezcla = 2;
        jarraDeVino = 6;
        unidadDeFermentacion = 7;
        envaseJugoAzucarado = 15;
        paqueteLevadura = 20;
        semana = 0;
        lock = new ReentrantLock(true);
        esperandoIngrediente = lock.newCondition();
        esperandoJarra = lock.newCondition();
        esperandoVino = lock.newCondition();
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

                esperandoIngrediente.await();
            }
            estacionDeMezcla--;
            System.out.println("Miembro " + idMiembro + " puede iniciar la mezcla");
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }

    public void terminarDeMezclar(int idMiembro) {
        try {
            lock.lock();
            estacionDeMezcla++;
            while (jarraDeVino < 1) {
                System.out.println("Miembro " + idMiembro + "espera que se libere una jarra");
                esperandoJarra.await();
            }
            jarraDeVino--;
            System.out.println("Miembro " + idMiembro + " tiene ambas jarras para pasar la fermentacion");
            unidadDeFermentacion--;
            Thread.sleep(2000);
            System.out.println("Miembro " + idMiembro + "termino de pasar la mezcla");
            jarraDeVino = jarraDeVino + 2;

            esperandoJarra.signalAll();
            esperandoVino.await();

        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }

    public boolean puedoPreparar() {
        return estacionDeMezcla > 0 && jarraDeVino > 1 && envaseJugoAzucarado >= 2 && unidadDeFermentacion >= 1 && paqueteLevadura >= 1;
    }

    public boolean faltanIngredientes() {
        return envaseJugoAzucarado < 2 || paqueteLevadura < 1;
    }

    public void reponerIngredientes() {
        try {
            lock.lock();
            while (!faltanIngredientes) {
                adminEsperando.await();
            }
            System.out.println("El admin se encuentra reponiendo ingredientes");
            paqueteLevadura = 20;
            envaseJugoAzucarado = 15;
            faltanIngredientes = false;
            Thread.sleep(2000);
            System.out.println("El admin termino de reponer los ingredientes");
            Thread.sleep(2000);
            esperandoIngrediente.signal();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }

    public void pasarSemana() {
        lock.lock();
        try {
            semana++;
            System.out.println("SEMANA: " + semana);

            if (semana % 4 == 0) {
                esperandoVino.signalAll();
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }

    public void mezclar(int idMiembro) {
        try {
            System.out.println("Miembro " + idMiembro + "esta mezclando");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tomarVino(int idMiembro) {
        try {
            System.out.println("El miembro " + idMiembro + " se encuentra probando vino");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        lock.lock();
        unidadDeFermentacion++;
        lock.unlock();
    }
}

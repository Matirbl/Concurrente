/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class SalaLock {

    private int elementoFaltante;
    private ReentrantLock entrada = new ReentrantLock();
    private Condition agente = entrada.newCondition();
    private Condition papel = entrada.newCondition();
    private Condition fuego = entrada.newCondition();
    private Condition tabaco = entrada.newCondition();

    public void colocar(int i) throws InterruptedException {

        try {
            entrada.lock();
            elementoFaltante = i;

            switch (i) {

                case 1:
                    System.out.println("\u001B[32mCOLOQUÉ FUEGO Y TABACO");
                    papel.signal();
                    break;
                case 2:
                    System.out.println("\u001B[35mCOLOQUÉ FUEGO Y PAPEL");
                    tabaco.signal();
                    break;
                case 3:
                    System.out.println("\u001B[31mCOLOQUÉ PAPEL Y TABACO");
                    fuego.signal();
                    break;
                default:
            }
            agente.await();
        } catch (Exception e) {

        } finally {
            entrada.unlock();
        }

    }

    public void entrafumar(int id) {
        try {
            entrada.lock();
            while (elementoFaltante != id) {

                switch (id) {

                    case 1:
                        papel.await();
                        break;
                    case 2:
                        tabaco.await();
                        break;
                    case 3:
                        fuego.await();
                        break;
                    default:
                }
            }
            switch (id) {

                case 1:
                    System.out.println("\u001B[32mTENGO PAPEL Y PUEDO FUMAR");
                    Thread.sleep(2000);
                    elementoFaltante = -1;
                    System.out.println("TERMINO DE FUMAR");
                    agente.signal();

                    break;
                case 2:
                    System.out.println("\u001B[35mTENGO TABACO Y PUEDO FUMAR");
                    Thread.sleep(2000);
                    elementoFaltante = -1;
                    System.out.println("TERMINO DE FUMAR");
                    agente.signal();

                    break;
                case 3:
                    System.out.println("\u001B[31mTENGO FUEGO Y PUEDO FUMAR");
                    Thread.sleep(2000);
                    elementoFaltante = -1;
                    System.out.println("TERMINO DE FUMAR");
                    agente.signal();

                    break;
                default:
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SalaLock.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrada.unlock();
        }

    }
}

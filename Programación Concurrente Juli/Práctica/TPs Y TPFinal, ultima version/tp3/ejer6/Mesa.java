/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ejer6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.*;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class Mesa {

    private Semaphore lleno;
    private Semaphore mutex;
    private Semaphore vacio;
    private boolean papel;
    private boolean tabaco;
    private boolean fuego;
    private char ingredienteFaltante;

    public Mesa() {
        papel = false;
        tabaco = false;
        fuego = false;
        ingredienteFaltante = ' ';
    }

    public synchronized void armar(String nombreFumador, char ingredienteDelFumador) {
        try {
            mensajeDeseo(nombreFumador, ingredienteDelFumador);
            Thread.sleep(1000);
            while (!puedoArmar(ingredienteDelFumador)) {
                this.wait();
            }
            mensajeArmando(nombreFumador, ingredienteDelFumador);
            papel = false;
            tabaco = false;
            fuego = false;
            Thread.sleep(2000);
            this.notifyAll();
            System.err.println(nombreFumador +" avisá al agente que se usaron los ingredientes");
        } catch (Exception e) {
        }
    }

    public void fumar(String nombreFumador, char ingredienteDelFumador) {
        try {
            System.err.println(nombreFumador + " se encuentra fumando");
            Thread.sleep(5225);
            System.err.println(nombreFumador + " terminó de fumar");
            Thread.sleep(2552);
        } catch (Exception e) {
        }
    }

    public boolean puedoArmar(char ingredienteDelFumador) {
        return (ingredienteDelFumador == ingredienteFaltante);
    }

    public void mensajeDeseo(String nombre, char ingredienteDelFumador) {
        switch (ingredienteDelFumador) {
            case 'p':
                System.out.println(nombre + " que tiene papel, desea fumar");
                break;
            case 't':
                System.out.println(nombre + " que tiene tabaco, desea fumar");
                break;
            case 'f':
                System.out.println(nombre + " que tiene fuego, desea fumar");
                break;
            default:
                throw new AssertionError();
        }
    }

    public void mensajeArmando(String nombre, char ingredienteDelFumador) {
        switch (ingredienteDelFumador) {
            case 'p':
                System.out.println(nombre + " que tiene papel, se encuentra armando");
                break;
            case 't':
                System.out.println(nombre + " que tiene tabaco, se encuentra armando");
                break;
            case 'f':
                System.out.println(nombre + " que tiene fuego, se encuentra armando");
                break;
            default:
                throw new AssertionError();
        }
    }


    public synchronized void depositar(String nombre) {
        try {
            while (!puedoDepositar()) {
                this.wait();
            }
            
            System.out.println(nombre + " comenzó la depositación");
            this.depositarAleatoriamente();
            Thread.sleep(2552);
            System.out.println(nombre + " finalizó la depositación");
            System.err.println(nombre + " avisó a los fumadores");
            Thread.sleep(1600);

            this.notifyAll();

        } catch (Exception e) {
        }

    }

    public synchronized boolean puedoDepositar() {
        return (papel == false && tabaco == false && fuego == false);
        //sino hay ingredientes en la mesa puedo depositar
        
    }

    public synchronized void depositarAleatoriamente() {
        
        int eleccion = intAleatorio(1, 3);

        switch (eleccion) {
            case 1:
                papel = true;
                System.out.println("Se deposito papel");
                eleccion = intAleatorio(1, 2);
                if (eleccion != 1) {
                    System.out.println("Se deposito fuego");
                    fuego = true;
                    ingredienteFaltante = 't';//falta tabaco
                } else {
                    System.out.println("Se deposito tabaco");
                    tabaco = true;
                    ingredienteFaltante = 'f';//falta fuego
                }
                break;
            case 2:
                tabaco = true;
                System.out.println("Se deposito tabaco");
                eleccion = intAleatorio(1, 2);
                if (eleccion == 1) {
                    papel = true;
                    ingredienteFaltante = 'f';//falta fuego
                    System.out.println("Se deposito papel");
                } else {
                    //fuego
                    fuego = true;
                    System.out.println("Se deposito fuego");
                    ingredienteFaltante = 'p';//falta papel
                }
                break;
            case 3:
                //fuego
                fuego = true;
                System.out.println("Se deposito fuego");
                eleccion = intAleatorio(1, 2);
                if (eleccion == 1) {
                    //papel
                    papel = true;
                    System.out.println("Se deposito papel");
                    ingredienteFaltante = 't';//falta tabaco
                } else {
                    //tabaco
                    tabaco = true;
                    System.out.println("Se deposito tabaco");
                    ingredienteFaltante = 'p';//falta papel
                }
                break;
            default:
                throw new AssertionError();
        }

    }

//  solucion productor consumidor con semaforos
//    public void fumar(String nombreFumador) {
//
//        try {
//            System.out.println(nombreFumador + " desea fumar");
//            lleno.acquire();
//            mutex.acquire();
//            System.out.println(nombreFumador + " está armando");
//            Thread.sleep(2500);
//            System.out.println(nombreFumador + " está fumando");
//            Thread.sleep(2500);
//            mutex.release();
//            System.out.println(nombreFumador + " terminó de fumar");
//            vacio.release();
//        } catch (InterruptedException ex) {
//
//            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    
//    public void depositar(String nombre) {
//
//        try {
//            vacio.acquire();
//            mutex.acquire();
//            System.out.println(nombre + " está depositando");
//            Thread.sleep(2500);
//            this.depositarAleatoriamente();//deposito aleatoriamente dos de los 3 ingredientes
//            System.out.println(nombre + " terminó de depositar");
//            Thread.sleep(2500);
//            mutex.release();
//            lleno.release();
//        } catch (InterruptedException ex) {
//
//            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}

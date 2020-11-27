/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej6Pasteleria;

import utiles.Cola;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class BufferLimitado {

    /*
     Buffer de capacidad limitada
     recurso compartido entre los los hilos: empaquetadores y brazo mecanicos
     similar a un problema tipo Productor - Consumidor 
     */
    private double pesoMaximo;
    private double pesoAcumulado;
    private boolean pesoIdealAlcanzado;
    private boolean cajaOcupada;
    private int cantDePasteles;

    public BufferLimitado(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
        this.pesoAcumulado = 0;
        this.cantDePasteles = 0;
        this.pesoIdealAlcanzado = false;
        this.cajaOcupada = false;
    }

    public synchronized void colocarPastel(int idEmpaq, double pesoDelPastel) {
        /*
         el peso del pastel puede variar desde 250 a 1000
         */
        try {

            while (!this.puedoColocar(pesoDelPastel)) {
                this.wait();
            }
            System.err.println("Empaq: " + idEmpaq + " se encuentra enpaquetando");
            cajaOcupada = true;

            pesoAcumulado = pesoAcumulado + pesoDelPastel;
            cantDePasteles++;

            Thread.sleep(1000 * intAleatorio(2, 4));
            if (pesoAcumulado >= (pesoMaximo - 500)) {
                this.avisarAlBrazo(idEmpaq);
            }

            cajaOcupada = false;
            System.err.println("Empaq " + idEmpaq + " finish, peso acumulado: " + pesoAcumulado);

        } catch (Exception e) {
        }

    }

    public synchronized void avisarAlBrazo(int idEmpaq) {
        try {
            System.out.println("El paquete ya tiene un peso considerable");
            System.err.println("Entonces el empaq " + idEmpaq + " avisa al brazo");
            pesoIdealAlcanzado = true;
            this.notifyAll();

        } catch (Exception e) {
        }
    }

    public synchronized void retirarCaja() {

        try {
            while (!this.puedoRetirar()) {
                this.wait();
            }
            //si estoy aca es que me aviso un brazo que puedo retirar 
            System.out.println("El brazo mecánico procede a retirar la caja");
            cajaOcupada = true;
            Thread.sleep(1000 * intAleatorio(2, 5));
            System.out.println("Ahora se encuentra colocando una nueva caja");
            pesoAcumulado = 0;
            pesoIdealAlcanzado = false;
            cajaOcupada = false;
            Thread.sleep(250 * intAleatorio(3, 4));
            System.err.println("Notifica a los empaquetadores");
            this.notifyAll();
        } catch (Exception e) {
        }
    }

    public boolean puedoColocar(double pesoDelPastel) {
        boolean salida = false;
        double pesoResultante = pesoAcumulado + pesoDelPastel;
        if (pesoResultante <= (pesoMaximo) && !cajaOcupada) {
            //si la caja no esta ocupada y el peso no exede el peso max puedo colocar
            salida = true;
        }
        return salida;
    }

    public boolean puedoRetirar() {
        return (pesoIdealAlcanzado && !cajaOcupada);//retorna true si se alcanzo un peso ideal
    }

}

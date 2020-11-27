/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer2MuseoConTemperatura;

import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class Museo {

    private int capacidad;
    private int ingresaron;
    private int temperatura;
    private boolean jubiladoEsperando;

    public Museo(int capacidad) {
        this.capacidad = capacidad;
        this.temperatura = 25;
        this.ingresaron = 0;
        this.jubiladoEsperando = false;
    }

    public void pasear(int idEntrante) {
        try {
            System.out.println(idEntrante + " se encuentra paseando");
            Thread.sleep(1000 * intAleatorio(2, 5));

        } catch (Exception e) {
        }
    }

    public synchronized void entrar(int idEntrante) {

        try {
            while (jubiladoEsperando || museoLleno()) {
                this.wait();
            }
            System.out.println(idEntrante + " ingresó");
            ingresaron++;
            Thread.sleep(1000);

        } catch (Exception e) {
        }

    }

    public synchronized void entrarJubilado(int idEntrante) {

        try {
            while (museoLleno()) {
                jubiladoEsperando = true;
                this.wait();
            }
            System.out.println(idEntrante + " el jubilado ingresó");
            ingresaron++;
            Thread.sleep(1000);

        } catch (Exception e) {
        }

    }

    public synchronized void salir(int idSaliente) {
        try {
            System.out.println(idSaliente + " salió");
            ingresaron--;
            Thread.sleep(1000);
            this.notifyAll();
        } catch (Exception e) {
        }

    }

    public synchronized boolean museoLleno() {
        return (capacidad <= ingresaron);
    }

    public synchronized void modificarTemperatura() {
        temperatura = intAleatorio(20, 40);
        if (temperatura >= 30) {
            capacidad = 3;
        } else {
            capacidad = 6;
        }

    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

}

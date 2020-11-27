/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LaExpo;

import java.util.Random;

/**
 *
 * @author 38493269
 */
public class Test {

    /*
     Julián Domínguez 
     FAI 974
     */
    public static void main(String[] args) {

        int cantidadDeSalas = 8;

        /*
         primero inicializamos un museo con 8 salas 
         */
        Sala[] salasDelMuseo = new Sala[8];
        Museo m = new Museo("Museo Nacional De Bellas Artes");
        Random r = new Random();
        int capacidad;
        for (int i = 0; i < 8; i++) {
            capacidad = r.nextInt(6) + 4;
            Sala nuevaSala = new Sala(i + 1, capacidad, m);
            salasDelMuseo[i] = nuevaSala;
        }
        m.setSalas(salasDelMuseo);

        /*
         segundo creamos los hilos: visitantes, responsables del museo y los críticos
         */
        Visitante[] visitantes = new Visitante[9];
        ResponsableDeLaSala[] responsables = new ResponsableDeLaSala[7];
        Critico[] criticos = new Critico[4];

        for (int i = 0; i < 9; i++) {
            Visitante nuevoVisitante = new Visitante(i + 1, m);
            Thread t = new Thread(nuevoVisitante);
            t.start();
        }

        for (int i = 0; i < 7; i++) {
            ResponsableDeLaSala nuevoResponsable = new ResponsableDeLaSala(i + 1, m);
            Thread t = new Thread(nuevoResponsable);
            t.start();
        }

        for (int i = 0; i < 4; i++) {
            Critico nuevoCritico = new Critico(i + 1, m);
            Thread t = new Thread(nuevoCritico);
            t.start();
        }

    }

}

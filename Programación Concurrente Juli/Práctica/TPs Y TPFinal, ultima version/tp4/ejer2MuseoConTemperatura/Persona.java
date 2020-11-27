/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer2MuseoConTemperatura;

/**
 *
 */
public class Persona implements Runnable {

    private int id;
    private int edad;
    private Museo m;

    public Persona(int id, int edad, Museo m) {
        this.id = id;
        this.edad = edad;
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            if (edad >= 65) {
                //jubilado
                m.entrarJubilado(id);
            } else {
                //no jubilado
                m.entrar(id);
            }
            m.pasear(id);
            m.salir(id);
        }
    }

}

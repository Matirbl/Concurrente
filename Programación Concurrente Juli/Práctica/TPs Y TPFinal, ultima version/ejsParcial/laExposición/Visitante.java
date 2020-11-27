/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.laExposición;

/**
 *
 */
public class Visitante extends Persona {

    public Visitante(int id, Sala recurComp) {
        this.id = id;
        this.recursoCompartido = recurComp;
    }

    @Override
    public void run() {
        while (true) {
            recursoCompartido.ingresarVisitante(id);
            recursoCompartido.pasear();
            recursoCompartido.salirVisitante(id);
        }
    }

}

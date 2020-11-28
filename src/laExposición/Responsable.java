/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laExposición;

/**
 *
 */
public class Responsable extends Persona {

    public Responsable(int id, Sala recurComp) {
        this.id = id;
        this.recursoCompartido = recurComp;
    }

    @Override
    public void run() {
        while (true) {
            recursoCompartido.ingresarResponsable();
            recursoCompartido.pasear();
            recursoCompartido.salirResponsable();
        }
    }
}

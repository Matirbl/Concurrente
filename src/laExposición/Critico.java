/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laExposición;

/**
 *
 */
public class Critico extends Persona {

    public Critico(Sala recurComp) {
        this.id = 1;
        this.recursoCompartido = recurComp;
    }

    @Override
    public void run() {
        while (true) {
            recursoCompartido.ingresarCritico();
            recursoCompartido.pasear();
            recursoCompartido.salirCritico();
        }
    }

}

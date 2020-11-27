/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpExcepciones;

/**
 *
 * @author jdominguez
 */
public class ExcepcionMenorEdad extends RuntimeException {

    private int edad;

    public ExcepcionMenorEdad(int edad, String message) {
        super(message);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String getMessage() {
        String msj = this.getMessage();
        msj = msj + this.getEdad();
        return msj;
    }

}

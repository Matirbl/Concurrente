/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

public class MenorEdad extends Exception {

    private String mensaje = "Usted es menor de edad";

    public String mensaje() {
        return this.mensaje;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;


public class NoSalioNumero extends Exception {
    private String mensaje ="Su numero no salió, vuelva a intentarlo";
    
    public String mensaje(){
        return this.mensaje;
    }
}

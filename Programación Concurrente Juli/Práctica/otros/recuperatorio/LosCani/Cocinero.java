/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LosCani;

/**
 *
 * @author 38493269
 */
public class Cocinero implements Runnable{
    
    /*
    Julián Domínguez 
    FAI 974
    */

    private String nombre;
    private Olla recursoCompartido;

    public Cocinero(String nombre, Olla recursoCompartido) {
        this.nombre = nombre;
        this.recursoCompartido = recursoCompartido;
    }
    
    
    
    
    @Override
    public void run() {

        while (true) {
            recursoCompartido.cocinar(nombre);
        }
    
    }
    
}

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
public class Canibal implements Runnable {
    
    /*
    Julián Domínguez 
    FAI 974
    */
    private int id;
    private Olla recursoCompartido;

    public Canibal(int id, Olla recursoCompartido) {
        this.id = id;
        this.recursoCompartido = recursoCompartido;
    }
    
    
    
    
    
    @Override
    public void run() {
        while (true) {
            recursoCompartido.comer(id);
        }
    }
    
    
    
    
    
    
    
}

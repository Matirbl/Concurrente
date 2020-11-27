/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class FaroAsistente implements Runnable{
    private Faro f;

    public FaroAsistente(Faro f) {
        this.f = f;
    }

    @Override
    public void run() {
        while (true) {
            f.adminitrarToboganes();
        }
    }
    
    
    
    
}

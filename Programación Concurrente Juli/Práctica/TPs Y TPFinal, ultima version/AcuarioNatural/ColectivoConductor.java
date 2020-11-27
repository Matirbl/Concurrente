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
public class ColectivoConductor implements Runnable {
    
    private int idConductor;
    private Colectivo recComp;//entre los hilos pasajeros
    
    public ColectivoConductor(int id, Colectivo recComp) {
        this.idConductor = id;
        this.recComp = recComp;
    }
    
    @Override
    public void run() {
        while (true) {
            
            recComp.iniciarRecorrido(this);
            recComp.recorrido(this);
            recComp.finalizarRecorrido(this);
        }
    }
    
    public int getIdConductor() {
        return idConductor;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LaExpo;

/**
 *
 * @author 38493269
 */
public class Critico extends Persona implements Runnable {

    /*
    Julián Domínguez 
    FAI 974
    */
    public Critico(int id, Museo museo) {
        super(id, museo);
    }

    @Override
    public void run() {
        while (true) {
            int recorrido = museo.getSalas().length;
            for (int i = 0; i < recorrido; i++) {
                //el critico critica todas las salas
                museo.getSalas()[i].criticar(id);
            }
        }
    }

}

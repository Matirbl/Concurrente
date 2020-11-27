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
public class Test {

    /*
    Julián Domínguez 
    FAI 974
    */
    public static void main(String[] args) {

        int cantidadDeRaciones = 4;
        Canibal[] horda = new Canibal[9];
        Olla ollita = new Olla(cantidadDeRaciones);
        Cocinero cocinero = new Cocinero("Julián", ollita);
        for (int i = 0; i < 9; i++) {
            horda[i] = new Canibal(i + 1, ollita);
            Thread canibalHambriento = new Thread(horda[i]);
            canibalHambriento.start();
        }
        Thread c = new Thread(cocinero);
        c.start();
    }

}

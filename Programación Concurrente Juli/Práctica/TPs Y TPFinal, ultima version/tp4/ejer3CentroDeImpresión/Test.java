/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer3CentroDeImpresión;

/**
 *
 */
public class Test {

    public static void main(String[] args) {

        CentroDeImpresion recComp = new CentroDeImpresion(1, 2);
        Thread t;
        for (int i = 0; i < 4; i++) {
            t = new Thread(new Usuario(i+1, recComp));
            t.start();
        }
        
    }
}

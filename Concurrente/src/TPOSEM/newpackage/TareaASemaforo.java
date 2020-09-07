/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPOSEM.newpackage;

import TPOSEM.newpackage.ValorSem;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TareaASemaforo implements Runnable {

    private ValorSem val;

    public TareaASemaforo(ValorSem v1) {
        val = v1;

    }

    public void run() {

        val.sumar();
    }
}

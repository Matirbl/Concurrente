/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO;

public class TareaA implements Runnable {

    private Valor v1;
    

    public TareaA(  Valor v) {
        
        this.v1 = v;
    }

    public void run() {
        v1.sumar();
    }

}

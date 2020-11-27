/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer4;

/**
 *
 * @author jdominguez
 */
public class Test {

    public static void main(String[] args) {

        RecursoCompartido r = new RecursoCompartido();

        HiloA j1 = new HiloA(r);
        HiloB j2 = new HiloB(r);

        Thread t1 = new Thread(j1);
        Thread t2 = new Thread(j2);

        t2.start();
        t1.start();

    }

    /*
     resultado sin sincronizacion
     el hilo B recibio: 3
     el hilo A recibio: 3
     el hilo A recibio: 7
     el hilo A lo dejo en: 7
     el hilo B recibio: 7
     el hilo B lo dejo en: 7
     el hilo A recibio: 16
     el hilo A lo dejo en: 8
     el hilo B lo dejo en: 16
     el hilo B recibio: 16
     el hilo A lo dejo en: 17
     el hilo A recibio: 34
     el hilo B lo dejo en: 34
     el hilo B recibio: 34
     el hilo A lo dejo en: 35
     el hilo A recibio: 35
     el hilo B recibio: 70

    
    conclusion: no hay consistencia en el resultado
     */
}

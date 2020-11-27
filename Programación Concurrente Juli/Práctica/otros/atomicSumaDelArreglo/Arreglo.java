/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atomicSumaDelArreglo;

/**
 *
 * @author jdominguez
 */
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Arreglo {

    private AtomicInteger suma;
    private int[] elementos;

    public Arreglo() {
        elementos = new int[50];
        suma = new AtomicInteger();
    }

    public void generar() {
        Random r = new Random();
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = r.nextInt(111111);
            System.out.println(elementos[i]);
        }
    }
    
    public AtomicInteger sumar(){
        AtomicInteger salida= new AtomicInteger();
        
        for (int i = 0; i < elementos.length; i++) {
            salida.addAndGet(elementos[i]);
        }
        return salida;
    }

    public AtomicInteger getSuma() {
        return suma;
    }

    public void setSuma(AtomicInteger suma) {
        this.suma = suma;
    }

    public int[] getElementos() {
        return elementos;
    }

    public void setElementos(int[] elementos) {
        this.elementos = elementos;
    }

}

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
public class HilosSuma implements Runnable {

    private Arreglo miArreglo;
    private int[] intervalo = new int[2];
    private int id;

    public HilosSuma(Arreglo a, int inicio, int fin, int ide) {
        miArreglo = a;
        intervalo[0] = inicio;
        intervalo[1] = fin;
        id = ide;
    }

    @Override
    public void run() {

        int inicio = intervalo[0];
        int fin = intervalo[1];
        int suma = 0;
        while (inicio <= fin) {
            suma = suma + miArreglo.getElementos()[inicio];
            inicio++;
        }
        miArreglo.getSuma().addAndGet(suma);
        System.out.println("El hilo: " + id + " termino de realizar su suma: " +suma);

    }

}

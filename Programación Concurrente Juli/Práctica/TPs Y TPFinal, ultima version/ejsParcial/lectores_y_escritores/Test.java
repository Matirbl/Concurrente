/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.lectores_y_escritores;
/*
PREGUNTARRR
    si se necesita guardar los objetos runnable en un arreglo 
    o directamente los creo y inicio en la repetitiva
*/
/**
 *
 */
public class Test {

    public static void main(String[] args) {
        Lector[] lectores = new Lector[5];
        Escritor[] escritores = new Escritor[5];
        Thread t;
        Libro recComp = new Libro(25);
        for (int i = 0; i < lectores.length - 1; i++) {
            lectores[i] = new Lector(i + 1, recComp);
            t = new Thread(lectores[i]);
            t.start();
        }
        for (int i = 0; i < escritores.length - 1; i++) {
            escritores[i] = new Escritor(i + 1, recComp);
            t = new Thread(escritores[i]);
            t.start();

        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MontañaRusa;

public class mainMontaña {

    public static void main(String[] args) {
        Carrito carro = new Carrito(4);
        Thread montaña = new Thread(new MontañaRusa(carro));
        Thread visitantes[] = new Thread[20];

        for (int i = 0; i < visitantes.length; i++) {
            visitantes[i] = new Thread(new Persona(i, carro));

        }

        for (int i = 0; i < visitantes.length; i++) {
            visitantes[i].start();
        }
        montaña.start();
    }

}

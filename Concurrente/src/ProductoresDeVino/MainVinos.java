/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoresDeVino;

public class MainVinos {

    public static void main(String[] args) {
        Sala unaSala = new Sala();
        Thread productores[] = new Thread[20];
        Thread admin = new Thread(new Administrador(unaSala));

        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Thread(new Productor(unaSala, i));

        }
        for (int j = 0; j < productores.length; j++) {
            productores[j].start();
        }
        admin.start();
    }

}

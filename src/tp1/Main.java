/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

/**
 *
 * @author Chino
 */
public class Main {

    public static void main(String[] args) {
        ClienteSuper cliente1 = new ClienteSuper("Cliente 1", new int[]{2000, 2000, 1000, 5000, 2000, 3000});
        ClienteSuper cliente2 = new ClienteSuper("Cliente 2", new int[]{1000, 3000, 5000, 1000, 1000});
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);
        cajera1.start();
        cajera2.start();

//        cajera1.procesarCompra(cliente1, initialTime);
//        cajera1.procesarCompra(cliente2, initialTime);
    }
}

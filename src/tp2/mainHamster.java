/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

/**
 *
 * @author Chino
 */
public class mainHamster {

    public static void main(String[] args) {

        Jaula j = new Jaula();
        
        Thread h1 = new Thread(new Hamster("Mati", j));
        Thread h2 = new Thread(new Hamster("Carla", j));
        Thread h3 = new Thread(new Hamster("Peluson", j));

        h1.start();
        h2.start();
        h3.start();

    }

}

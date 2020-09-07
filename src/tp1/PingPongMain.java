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
public class PingPongMain {

    public static void main(String[] args) {
        PingPong t1 = new PingPong("PING", 10);
        PingPong t2 = new PingPong("PONG", 10);
        PingPong t3 = new PingPong("PANG", 10);
        PingPong t4 = new PingPong("PUNG", 10);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        for (int i = 0; i < 10000; i++) {
            System.err.println("soy main");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            };
        }

    }
  
    
}

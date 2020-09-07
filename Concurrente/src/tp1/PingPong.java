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
public class PingPong extends Thread {
    private final String nombre;
    private final int delay;

public PingPong(String cartel, int cantMs){
nombre= cartel;
delay = cantMs;
}
public void run(){
    for(int i=1; i<delay;i++){
        System.out.println(nombre+"");
        try{ Thread.sleep(0);
        }catch(InterruptedException e){
            
        };
    }
}
}

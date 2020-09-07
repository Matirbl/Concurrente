/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chino
 */
public class ThreadTesting{
    
 public static void main (String[] args){
 Thread miHilo= new miEjecucion();
 miHilo.start();
     try {
         Thread.currentThread().sleep(4000);
     } catch (InterruptedException ex) {
         Logger.getLogger(ThreadTesting.class.getName()).log(Level.SEVERE, null, ex);
     }



 System.out.println("En el main");
 }
  
}



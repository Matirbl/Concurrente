/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

public class Saludo {

    synchronized void esperarJefe(String empleado) {
        try {
            wait();
            System.out.println(empleado + "> Buenos dias jefe!");
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

    synchronized void saludoJefe() {
        System.out.println("JEFE> Buenos dias!");
        notifyAll();
    }
}

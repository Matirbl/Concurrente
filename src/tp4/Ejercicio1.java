/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

public class Ejercicio1 {

    public static void main(String argv[]) {
        String[] nombresEmpleados = {"Pablo", "Luis", "Andrea", "Pedro", "Paula"};
        Saludo hola = new Saludo();
        Personal[] elPersonal = new Personal[6];

        elPersonal[0] = new Personal(hola, "JEFE", 5);
        for (int i = 1; i < 6; i++) {
            elPersonal[i] = new Personal(hola, nombresEmpleados[i - 1]);
        }
        for (int i = 0; i < 6; i++) {
            elPersonal[i].start();
        }
        try {
            for (int i = 0; i < 6; i++) {
                elPersonal[i].join();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

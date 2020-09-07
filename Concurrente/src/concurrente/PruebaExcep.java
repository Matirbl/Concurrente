/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

import java.io.IOException;
import java.lang.Math;
import java.util.Random;

/**
 *
 * @author Chino
 */
public class PruebaExcep {

    public static void main(String[] args) {
        try {
            verEdad(16);

        } catch (MenorEdad e) {
            System.err.println(e.mensaje());
        }
        try {
            verNumero(9);
        } catch (NoSalioNumero e) {
            System.out.println(e.mensaje());
        }
        int arreglo[] = new int[5];
        int i;
        arreglo[1] = 1;
        arreglo[2] = 2;
        arreglo[3] = 3;
        arreglo[4] = 4;
        arreglo[5] = 5;

        try {
            for (i = 1; i <= 7; i++) {
                System.out.println("Arreglo en " + i);
            }
        } catch (RuntimeException e) {
            System.out.println("Se va de rango");
        }

    }

    public static void verEdad(int edad) throws MenorEdad {
        if (edad < 18) {
            throw new MenorEdad();
        } else {
            System.out.println("Usted es mayor de edad");
        }
    }

    public static void verNumero(int n) throws NoSalioNumero {
        double var = Math.random() * ((10) + 1);
        int var2 = (int) var;
        if (var2 != n) {
            throw new NoSalioNumero();

        } else {
            System.out.println("Usted ha ganado");
        }
    }

}

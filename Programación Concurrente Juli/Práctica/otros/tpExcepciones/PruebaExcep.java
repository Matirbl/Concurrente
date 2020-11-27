/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpExcepciones;

import TecladoIn.TecladoIn;

/**
 *
 * @author jdominguez
 */
public class PruebaExcep {

    public static void policia() throws ExcepcionMenorEdad {
        System.out.println("Ingrese la edad de la persona: ");
        int edad = TecladoIn.readLineInt();
        if (edad < 18) {
            throw new ExcepcionMenorEdad(edad, "Es menor de edad");
        }
    }

    public static void ingresarNumParaLaRuleta() throws ExcepcionRuleta{
        System.out.println("Ingrese el num de la ruleta: ");
        int num = TecladoIn.readLineInt();
        //falta terminar
    }

}

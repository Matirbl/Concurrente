/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalaHemoterapia;

public class MainSalaHemoterapia {

    public static void main(String[] args) {

        Sala unaSala = new Sala();

        Thread Pacientes[] = new Thread[25];

        
        for (int i = 0; i < Pacientes.length; i++) {
            Pacientes[i]= new Thread(new Paciente(unaSala,i));
            
        }
        for (int i = 0; i < Pacientes.length; i++) {
           Pacientes[i].start();
            
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ejer6;

/**
 *
 */
public class Fumador implements Runnable {

    private String nombre;
    private char ingrediente;
    private Mesa m;

    public Fumador(String nombre, Mesa m, char ingrediente) {
        this.nombre = nombre;
        this.m = m;
        this.ingrediente = ingrediente;
    }

    @Override
    public void run() {

        while (true) {
            m.armar(nombre, ingrediente);
            m.fumar(nombre, ingrediente);
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(char ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Mesa getM() {
        return m;
    }

    public void setM(Mesa m) {
        this.m = m;
    }

}

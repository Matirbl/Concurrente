/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.lectores_y_escritores;

/**
 *
 */
public class Lector implements Runnable {

    private int id;
    private Libro libro;
    private int cantPagLeidas;
    private boolean terminarLibro;

    public Lector(int id, Libro libro) {
        this.id = id;
        this.libro = libro;
        this.cantPagLeidas = 0;
        this.terminarLibro = false;
    }

    @Override
    public void run() {
        while (!terminarLibro) {
            libro.empezarALeer(this);
            libro.leer(id);
            libro.terminarDeLeer(this);
        }

    }

    public void aumentarPagLeidas(int cant) {
        this.cantPagLeidas = this.cantPagLeidas + cant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantPagLeidas() {
        return cantPagLeidas;
    }

    public void setCantPagLeidas(int cantPagLeidas) {
        this.cantPagLeidas = cantPagLeidas;
    }

    public boolean isTerminarLibro() {
        return terminarLibro;
    }

    public void setTerminarLibro(boolean terminarLibro) {
        this.terminarLibro = terminarLibro;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.lectores_y_escritores;

/**
 *
 */
public class Escritor implements Runnable {
    
    private int id;
    private Libro libro;
    
    public Escritor(int id, Libro libro) {
        this.id = id;
        this.libro = libro;
        
    }
    
    @Override
    public void run() {
        while (!libro.isFinalizado()) {
            //mientras no este finalizado escribira
            libro.empezarAEscribir(this);
            libro.escribir(id);
            libro.terminarDeEscribir(this);
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Libro getLibro() {
        return libro;
    }
    
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
}

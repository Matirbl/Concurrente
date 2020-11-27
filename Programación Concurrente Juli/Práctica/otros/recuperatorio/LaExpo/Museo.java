/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LaExpo;

/**
 *
 * @author 38493269
 */
/*
 metologia de sincronización: monitores
 */
public class Museo {
    
    /*
    Julián Domínguez 
    FAI 974
    */

    //recurso compartido de los visitantes, responsables de la sala y criticos
    private String nombre;
    private Sala[] salas;
    private Critica[] criticas;
    private int cantDeCriticas;//inicializa en 0
    private int siguienteCritica;//marca el lugar donde se ingresara la siguiente critica

    public Museo(String nombre) {
        this.nombre = nombre;
        this.salas = null;
        this.criticas = new Critica[10000];
        this.siguienteCritica = 1;
        this.cantDeCriticas = 0;
    }

    public synchronized void ingresarCritica(Critica c) {
        //metodo sincronizado para generar un estado consistente 
        //ya que varios criticos pueden ingresar sus respectivas criticas
        //sobre las criticas y que no se pierdan o pisen viejas criticas
        //la estructura elegica es estática pero una mejora seria hacerla dinámica
        //mejorando asi la memoria requerida
        criticas[siguienteCritica] = c;
        siguienteCritica++;
        cantDeCriticas++;
        this.notifyAll();
    }

    public synchronized void mostrarCriticasHastaAhora() {
        for (int i = 1; i <= cantDeCriticas; i++) {
            System.out.println(criticas[i].toString());
        }
    }

    public synchronized String getNombre() {
        return nombre;
    }

    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public synchronized Sala[] getSalas() {
        return salas;
    }

    public synchronized void setSalas(Sala[] salas) {
        this.salas = salas;
    }

    public synchronized Critica[] getCriticas() {
        return criticas;
    }

    public synchronized void setCriticas(Critica[] criticas) {
        this.criticas = criticas;
    }

    public synchronized int getCantDeCriticas() {
        return cantDeCriticas;
    }

    public synchronized void setCantDeCriticas(int cantDeCriticas) {
        this.cantDeCriticas = cantDeCriticas;
    }

    public synchronized int getSiguienteCritica() {
        return siguienteCritica;
    }

    public synchronized void setSiguienteCritica(int siguienteCritica) {
        this.siguienteCritica = siguienteCritica;
    }

}
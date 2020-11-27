/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LaExpo;

import java.util.Random;

/**
 *
 * @author 38493269
 */
public class Sala {
    
    /*
    Julián Domínguez 
    FAI 974
    */
    
    //uso un arreglo debido a que no tengo experiencia usando la lista provista por java
    //pero la idea es usar una estructra dinámica para guardar las criticas
    //considero que las criticas corresponden al museo

    private int id;
    private int capacidad;
    private Museo museo;
    private int cantidadAdentro;
    private boolean criticoEsperandoEntrar;
    private boolean ingresoUnCritico;

    public Sala(int id, int capacidad, Museo museo) {
        this.id = id;
        this.capacidad = capacidad;
        this.museo = museo;
        this.cantidadAdentro = 0;
        this.criticoEsperandoEntrar = false;
        this.ingresoUnCritico = false;
    }

    public void pasear(int idDelVisitante) {
        /*
         este métdo no es synchronized ya que varios visitantes pueden entrar 
         a la misma sala; si no fuera el caso si deberia ser synchronized 
         por definición de monitor(todos sus métodos deben ser declarado sync.)
         */

        try {
            while ((criticoEsperandoEntrar == true || ingresoUnCritico == true) && cantidadAdentro >= capacidad) {
                this.wait();
                /*
                 espera hasta que la capacidad se la adecuada o que un critico termine la critica/su espera y critica
                 */
            }
            cantidadAdentro++;
            Random r = new Random();
            int tiempoDePaseo = (r.nextInt(10) + 1) * 2500;
            System.out.println("El visitante " + idDelVisitante
                    + " se fue a pasear a la sala " + id);
            Thread.sleep(tiempoDePaseo);
            System.out.println("El visitante " + idDelVisitante
                    + " terminó de pasear por la la sala " + id);
            cantidadAdentro--;
            this.notify();//aviso a los criticos/otros visitantes/responsables que ya dejé la sala

        } catch (Exception e) {
        }
    }

    public synchronized void criticar(int idDelCritico) {

        if (criticoEsperandoEntrar == false && ingresoUnCritico == false) {
            //ya no hay un critico esperando criticar
            //ni critico criticando
            try {
                System.out.println("El critico " + idDelCritico
                        + " quiere ingresar a la sala " + id);
                criticoEsperandoEntrar = true;

                while (cantidadAdentro != 0) {
                    this.wait();//espera hasta que se vayan todas las personas
                }
                //salio del while, ya no hay personas adentro
                ingresoUnCritico = true;
                cantidadAdentro++;
                criticoEsperandoEntrar = false;

                this.realizarCritica(idDelCritico);

                cantidadAdentro--;
                ingresoUnCritico = false;
            } catch (Exception e) {
            }

        } else {
            System.out.println
                        ("-----------------------------------------------\n"
                    +"El critico " + idDelCritico
                    + " se dió cuenta que hay otro critico\n"
                    + "esperando ingresar o que ya ingresó,\n"
                    + "por lo tanto se retira de la sala " + id
                    + "\n-----------------------------------------------");

        }
        this.notifyAll();//aviso a los criticos/otros visitantes/responsables que ya dejé la sala

    }

    public synchronized void realizarCritica(int idDelCritico) {

        try {
            System.out.println("El critico " + idDelCritico
                    + " está criticando la sala " + id);
            Random r = new Random();
            boolean critica = (r.nextInt() % 2) == 1;
            Critica c = new Critica(critica, id, idDelCritico);
            this.museo.ingresarCritica(c);
            Thread.sleep(8000);
            System.out.println("El critico " + idDelCritico
                    + " terminó la critica de la sala " + id);
        } catch (Exception e) {
        }
    }

    public synchronized void controlar(int idDelResponsable) {
        cantidadAdentro++;
        try {
            while ((criticoEsperandoEntrar == true || ingresoUnCritico == true) && cantidadAdentro >= capacidad) {
                /*
                 espera hasta que la capacidad se la adecuada o que un critico termine la critica/su espera y critica
                 */
                this.wait();
            }
            System.out.println("El responsable/encargado " + idDelResponsable
                    + " ingresó a la sala " + id);
            Thread.sleep(8000);
            System.out.println("El responsable/encargado " + idDelResponsable
                    + " salió de sala " + id);
        } catch (Exception e) {
        }
        cantidadAdentro--;
        this.notifyAll();//aviso a los criticos/otros visitantes/responsables que ya dejé la sala
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized int getCapacidad() {
        return capacidad;
    }

    public synchronized void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized Museo getMuseo() {
        return museo;
    }

    public synchronized void setMuseo(Museo museo) {
        this.museo = museo;
    }

    public synchronized int getCantidadAdentro() {
        return cantidadAdentro;
    }

    public synchronized void setCantidadAdentro(int cantidadAdentro) {
        this.cantidadAdentro = cantidadAdentro;
    }

    public synchronized boolean isCriticoEsperandoEntrar() {
        return criticoEsperandoEntrar;
    }

    public synchronized void setCriticoEsperandoEntrar(boolean criticoEsperandoEntrar) {
        this.criticoEsperandoEntrar = criticoEsperandoEntrar;
    }

    public synchronized boolean isIngresoUnCritico() {
        return ingresoUnCritico;
    }

    public synchronized void setIngresoUnCritico(boolean ingresoUnCritico) {
        this.ingresoUnCritico = ingresoUnCritico;
    }

}

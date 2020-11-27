/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.BrokenBarrierException;
import static utiles.Aleatorio.intAleatorio;
import utiles.TextoColorido;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class PiletaConDelfines {

    private static boolean actividadRealizada;
    private static int cantTotal = 0;//cantidad total de las 4 piletas
    private static CyclicBarrier coordinarActividad;

    private int idPileta;
    private int ingresaron;//va de 1 hasta 10
    private boolean grupoLleno;
    private boolean abierto;
    private TextoColorido color;

    public PiletaConDelfines(int idPileta) {
        this.idPileta = idPileta;
        this.ingresaron = 0;
        this.grupoLleno = false;
        this.color = new TextoColorido();
        actividadRealizada = false;
        coordinarActividad = new CyclicBarrier(40);
    }

    public void realizarNadoConDelfines(Persona p, int horaActual) {

        if (puedoRealizarNadoConDelfines(horaActual)) {
            ingresar(p);
            coordinarActividad(p, horaActual);
        } 
    }

    public synchronized boolean puedoRealizarNadoConDelfines(int horaActual) {
        //si el grupo no está lleno, si está abierto y si se encuentra dentro del horario
        //solo en ese caso se podrá realizar la actividad
        //esto permite que se puedan realizar el resto de las actividades
        return (!actividadRealizada & abierto & !grupoLleno
                & estoyDentroDelHorario(horaActual));
    }

    public synchronized boolean estoyDentroDelHorario(int horaActual) {
        //puede que se respete la politica de 3 grupos completos y uno incompleto
        //en un horario o en el otro, una vez que se respeta la politica 
        //la actividad es realizada.
        return ((9 < horaActual && horaActual < 13)
                || (15 < horaActual && horaActual < 18));
    }

    public void coordinarActividad(Persona p, int horaActual) {
        /*
        se utiliza un conjunto de espera comun para coordinar que todos inicien al mismo tiempo
         */
        if (getCantTotal() <= 30) {
            esperarPrimeros30();
            if (getActividadRealizada()) {
                nadar(p);
                salir(p);
            } else {
                //el tercer grupo no se lleno, entonces los libero
                salir(p);
            }
        } else {
            esperarGrupoIncompleto();
            nadar(p);
            salir(p);
        }

    }

    public void esperarPrimeros30() {
        try {
            coordinarActividad.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
        } catch (BrokenBarrierException ex) {
        } catch (TimeoutException ex) {
            coordinarActividad.reset();
        }
    }

    public void esperarGrupoIncompleto() {
        try {
            coordinarActividad.await(4000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
        } catch (BrokenBarrierException ex) {
        } catch (TimeoutException ex) {
            setAbierto(false);
            setActividadRealizada(true);
            System.out.println(color.getRojo() + "Finalizo el tiempo limite que se llene el ultimo grupo");
            coordinarActividad.reset();
        }

    }

    public synchronized boolean actividadDisp() {
        return cantTotal < 30;
    }

    public void nadar(Persona p) {
        try {
            System.out.println(color.getCyan() + p.getIdPersona() + " se encuentra nadando con delfines en la PILETA: " + idPileta);
            Thread.sleep(1000 * intAleatorio(6, 9));
        } catch (Exception e) {
        }
    }

    public void simularTiempito(Persona p) {
        try {
            System.out.println(color.getCyan() + p.getIdPersona() + " se encuentra nadando con delfines en la PILETA: " + idPileta);
            Thread.sleep(250 * intAleatorio(2, 4));
        } catch (Exception e) {
        }
    }

    public synchronized void ingresar(Persona p) {
        cantTotal++;
        ingresaron++;
        System.out.println(color.getCyan() + p.getIdPersona() + " ingreso al nado con delfines en la PILETA: " + idPileta);
        if (ingresaron == 10) {

            System.out.println(color.getRojo() + "Grupo lleno en la PILETA: " + idPileta);
            grupoLleno = true;
            abierto = false;//solo se realiza un nado con pileta al dia, para que los visitantes puedan disfrutar del resto de las actividades
        }

    }

    public synchronized void salir(Persona p) {
        System.out.println(color.getCyan() + p.getIdPersona() + " se va a disfrutar del resto del parque acuatico");
        ingresaron--;
        cantTotal--;
        if (ingresaron == 0) {
            grupoLleno = false;
        }
    }

    public synchronized static int getCantTotal() {
        return cantTotal;
    }

    public synchronized static void setCantTotal(int cantTotal) {
        PiletaConDelfines.cantTotal = cantTotal;
    }

    public synchronized boolean abierto() {
        return abierto;
    }

    public synchronized void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public int getIdPileta() {
        return idPileta;
    }

    public void setIdPileta(int idPileta) {
        this.idPileta = idPileta;
    }

    public synchronized boolean grupoLleno() {
        return grupoLleno;
    }

    public synchronized void setGrupoLleno(boolean grupoLleno) {
        this.grupoLleno = grupoLleno;
    }

    public synchronized static boolean getActividadRealizada() {
        return actividadRealizada;
    }

    public synchronized static void setActividadRealizada(boolean actividadRealizada) {
        PiletaConDelfines.actividadRealizada = actividadRealizada;
    }

}

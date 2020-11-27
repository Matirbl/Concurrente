/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import static utiles.Aleatorio.intAleatorio;
import utiles.TextoColorido;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class PiletaConDelfines {

    //private static CountDownLatch cuentaRegresiva = new CountDownLatch(30+1); // 30 para los 3 gurpos de 10 personas, para el 4 grupo incompleto
    private static Object conjDeEspera = new Object();
    private static boolean actividadRealizada = false;
    private static int cantTotal = 0;//cantidad total de las 4 piletas
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
    }

    public synchronized void realizarNadoConDelfines(Persona p, int horaActual) {

        if (puedoRealizarNadoConDelfines(horaActual)) {
            ingresar(p);
            coordinarActividad();
            nadar(p);
            salir(p);
        } else {
            System.out.println(color.getCyan() + p.getIdPersona() + " se va a disfrutar del resto del Parque, porque el nado con delfines no se encuentra disponible");
        }
    }

    public synchronized boolean puedoRealizarNadoConDelfines(int horaActual) {
        //si el grupo no está lleno, si está abierto y si se encuentra dentro del horario
        //solo en ese caso se podrá realizar la actividad
        //esto permite que se puedan realizar el resto de las actividades
        return (!actividadRealizada && abierto && !grupoLleno && (15 < horaActual && horaActual < 18));
    }

    public synchronized void coordinarActividad() {
        /*
        se utiliza un conjunto de espera comun para coordinar que todos inicien al mismo tiempo
         */
        try {
            while (cantTotal < 30) {
                //mientras no se verifique la politica del parque
                //es decir mientras no esten 3 grupos completos
                //e uno incompleto deben esperar
                conjDeEspera.wait();
            }
        } catch (Exception e) {
        }

    }

    public void nadar(Persona p) {
        try {
            System.out.println(color.getCyan() + p.getIdPersona() + " se encuentra nadando con delfines en la PILETA: " + idPileta);
            Thread.sleep(1000 * intAleatorio(2, 3));// la actividad dura aproximadamente 45 min
        } catch (Exception e) {
        }
    }

    public synchronized void ingresar(Persona p) {
        ingresaron++;
        if (ingresaron == 10) {
            System.out.println(color.getCyan() + p.getIdPersona() + " grupo lleno en la PILETA: " + idPileta);
            grupoLleno = true;
            abierto = false;//solo se realiza un nado con pileta al dia, para que los visitantes puedan disfrutar del resto de las actividades
        }
        System.out.println(color.getCyan() + p.getIdPersona() + " ingreso al nado con delfines en la PILETA: " + idPileta);
        cantTotal++;
        if (cantTotal > 33) {
            actividadRealizada = true;
            conjDeEspera.notifyAll();
        }

    }

    public synchronized void salir(Persona p) {
        ingresaron--;
        cantTotal--;
        if (ingresaron == 0) {
            grupoLleno = false;
        }
        System.out.println(color.getCyan() + p.getIdPersona() + " se va a disfrutar del resto del parque acuatico");
    }

    public boolean abierto() {
        return abierto;
    }

    public synchronized  void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public int getIdPileta() {
        return idPileta;
    }

    public void setIdPileta(int idPileta) {
        this.idPileta = idPileta;
    }

    public boolean grupoLleno() {
        return grupoLleno;
    }

    public synchronized  void setGrupoLleno(boolean grupoLleno) {
        this.grupoLleno = grupoLleno;
    }

    public static Object getConjDeEspera() {
        return conjDeEspera;
    }

    public static boolean getActividadRealizada() {
        return actividadRealizada;
    }

    public static void setActividadRealizada(boolean actividadRealizada) {
        PiletaConDelfines.actividadRealizada = actividadRealizada;
    }

    
    
}

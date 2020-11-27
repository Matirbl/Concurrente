/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.CountDownLatch;
import static utiles.Aleatorio.intAleatorio;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import utiles.Aleatorio;
import utiles.TextoColorido;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class AcuarioNatural {

    private Carrera carrera;
    private Faro faro;
    private Restaurante[] restaurantes;
    private Snorkel laguna;
    private PiletaConDelfines[] piletas;
    private Shopping shopping;
    private Reloj reloj;
    private Semaphore abrio;
    private Semaphore mutexIngresar;
    private Semaphore mutexSalir;
    private Semaphore subirLineaA;
    private Semaphore subirLineaB;
    private Colectivo lineaA;
    private Colectivo lineaB;
    private Semaphore mutexPiletas;

    private TextoColorido color;

    public AcuarioNatural(Reloj r) {
        abrio = new Semaphore(0);
        color = new TextoColorido();
        reloj = r;
        carrera = new Carrera();//espera 7 gomones e inicia
        faro = new Faro(11);//11 es la capacidad de la escalera
        laguna = new Snorkel(15);//15 es la cantidad de equipos disponibles
        restaurantes = new Restaurante[3];
        restaurantes[0] = new Restaurante("Argentino",3, r);
        restaurantes[1] = new Restaurante("Italiano", 4, r);
        restaurantes[2] = new Restaurante("Chino", 5, r);
        piletas = new PiletaConDelfines[4];
        for (int i = 0; i < 4; i++) {
            piletas[i] = new PiletaConDelfines(i + 1);
        }
        shopping = new Shopping(10);

        lineaA = new Colectivo(15);
        lineaB = new Colectivo(15);
        subirLineaA = new Semaphore(0, true);
        subirLineaB = new Semaphore(0, true);

        mutexIngresar = new Semaphore(1, true);//el primer permiso lo da RelojManagement
        mutexSalir = new Semaphore(1, true);
        mutexPiletas = new Semaphore(1, true);

    }

    public void irAlParque(Persona p) {
        try {
            abrio.acquire();
            if (subirLineaA.tryAcquire()) {
                //linea A
                irEnLineaA(p);
            } else {
                if (subirLineaB.tryAcquire()) {
                    //linea B
                    irEnLineaB(p);
                } else {
                    //forma particular
                    irEnFormaParticular(p);
                }
            }
        } catch (Exception e) {
        }
    }

    public Restaurante elegirRestaurante() {
        return restaurantes[intAleatorio(0, 2)];
    }

    public void ingresar(Persona p) {
        try {
            mutexIngresar.acquire();
            System.out.println(color.getVioleta() + p.getIdPersona()
                    + " INGRESO AL ACUARIO " + reloj.obtenerHora());
            p.setPuedeAlmorzar(true);
            p.setPuedeMerendar(true);
            mutexIngresar.release();
        } catch (Exception e) {
        }
    }

    public void pensarQueElegir() {
        try {
            Thread.sleep(1000 * intAleatorio(1, 7));
        } catch (Exception e) {
        }
    }

    public void realizarActividades(Persona p) {
        int horaActual;
        int actividadElegida;
        PiletaConDelfines pileta;
        Restaurante restaurante;
        while (reloj.actividadesDisp()) {
            horaActual = reloj.getHora();
            actividadElegida = intAleatorio(4, 4);
            switch (actividadElegida) {
                case 1:
                    //Carreras de Gomones por el Rio
                    carrera.realizarCarrera(p);
                    break;
                case 2:
                    //Restaurante
                    if (p.puedeAlmorzar() || p.puedeMerendar()) {
                        restaurante = elegirRestaurante();
                        restaurante.ingresar(p, horaActual);
                        restaurante.salir(p);
                    } else {
                        System.err.println(color.getVerde() + p.getIdPersona()
                                + " ya uso los cupones");
                    }
                    break;
                case 3:
                    //Faro-Mirado con vista a 40m de altura y descenso en tobogán
                    faro.irAlFaro(p);
                    break;
                case 4:
                    //Nado Con Delfines
                    pileta = elegirPileta(horaActual);
                    if (pileta != null) {
                        pileta.realizarNadoConDelfines(p, horaActual);
                    } else {
                        System.out.println(color.getCyan() + p.getIdPersona() + " se va a disfrutar del resto del Parque, porque el nado con delfines no se encuentra disponible");
                    }
                    break;
                case 5:
                    //Disfruta de Snorkel ilimitado
                    laguna.realizarSnorkel(p);
                    break;
                case 6:
                    //Ir de compras al Shopping
                    shopping.irAlShoping(p);
                    break;
            }
            
        };//fin while
    }//fin realizarActividades

    public void salir(Persona p) {
        try {
            mutexSalir.acquire();
            System.err.println(color.getVioleta() + p.getIdPersona()
                    + " SALIO DEL ACUARIO a las " + reloj.obtenerHora());
            mutexSalir.release();
        } catch (Exception e) {
        }
    }

    public void abrir() {
        try {
            System.out.println(color.getVioleta() + "********ABRIO EL ACUARIO********");
            puedenSubirALosColes();
            carrera.setAbierto(true);
            faro.setAbierto(true);
            laguna.setAbierto(true);
            shopping.setAbierto(true);
            abrirPiletas();
            abrio.release(100);//importante que tenga correspondencia con el main
        } catch (Exception e) {
        }
    }

    public void puedenSubirALosColes() {
        subirLineaA.release(15);
        subirLineaB.release(15);
    }

    public void irEnFormaParticular(Persona p) {
        try {
            System.out.println(color.getVioleta()
                    + +p.getIdPersona()
                    + " se va en forma particular porque ya salieron los colectivos");
            Thread.sleep(1000 * intAleatorio(2, 9));
        } catch (Exception e) {
        }
    }

    public void irEnLineaA(Persona p) {
        System.out.println(color.getCyan() + p.getIdPersona()
                + " se encuentra subiendo al colectivo A");
        lineaA.subir(p);
        lineaA.bajar(p);
        System.out.println(color.getCyan() + p.getIdPersona()
                + " se encuentra bajando del colectivo A");

    }

    public void irEnLineaB(Persona p) {
        System.out.println(color.getCyan() + p.getIdPersona()
                + " se encuentra subiendo al colectivo B");
        lineaB.subir(p);
        lineaB.bajar(p);
        System.out.println(color.getCyan() + p.getIdPersona()
                + " se encuentra bajando del colectivo B");

    }

    public void cerrar() {
        try {
            carrera.setAbierto(false);
            faro.setAbierto(false);
            laguna.setAbierto(false);
            shopping.setAbierto(false);
            int cantPermisosRestantes = abrio.availablePermits();
            abrio.acquire(cantPermisosRestantes);

            System.out.println(color.getVioleta() + "********CERRO EL ACUARIO********");
            cerrarPiletas();

        } catch (Exception e) {
        }
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public Snorkel getLaguna() {
        return laguna;
    }

    public Faro getFaro() {
        return faro;
    }

    public Colectivo getLineaA() {
        return lineaA;
    }

    public Colectivo getLineaB() {
        return lineaB;
    }

    public PiletaConDelfines elegirPileta(int horaActual) {
        PiletaConDelfines salida = null;
        try {
            mutexPiletas.acquire();
            int i = 0;
            while (i < 4) {
                if (piletas[i].puedoRealizarNadoConDelfines(horaActual)) {
                    salida = piletas[i];
                    i = 5;
                }
                i++;
            }
            mutexPiletas.release();
        } catch (Exception e) {
        }
        return salida;
    }

    public void abrirPiletas() {
        try {
            mutexPiletas.acquire();
            for (int i = 0; i < 4; i++) {
                piletas[i].setAbierto(true);
            }
            PiletaConDelfines.setActividadRealizada(false);
            mutexPiletas.release();
        } catch (Exception e) {
        }
    }

    public void cerrarPiletas() {
        try {
            mutexPiletas.acquire();
            for (int i = 0; i < 4; i++) {
                piletas[i].setAbierto(false);
            }
            PiletaConDelfines.setActividadRealizada(true);
            mutexPiletas.release();
        } catch (Exception e) {
        }
    }
}

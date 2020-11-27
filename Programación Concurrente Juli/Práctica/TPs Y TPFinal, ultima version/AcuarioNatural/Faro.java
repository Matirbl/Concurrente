/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import static utiles.Aleatorio.intAleatorio;
import utiles.TextoColorido;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Faro {

    private boolean abierto;
    private TextoColorido color;
    private ReentrantLock lockFaro;
    private ReentrantLock lockEscalera;
    private Condition esperandoEscalera;

    private Condition adminEsperando;
    private Condition persEsperandoAsignacion;

    private int capacidadEscaleras;
    private int persSubiendoEscaleras;
    private int persSolcitandoTobogan;
    private int puedenTirarse;
    private int asignacion;

    private ReentrantLock lockToboganA;
    private Condition persEsperandoElToboganA;
    private boolean toboganADisp;

    private ReentrantLock lockToboganB;
    private Condition persEsperandoElToboganB;
    private boolean toboganBDisp;

    public Faro(int capacidadEscaleras) {
        this.capacidadEscaleras = capacidadEscaleras;
        abierto = true;
        lockEscalera = new ReentrantLock(true);
        esperandoEscalera = lockEscalera.newCondition();

        lockFaro = new ReentrantLock(true);
        persEsperandoAsignacion = lockFaro.newCondition();
        adminEsperando = lockFaro.newCondition();

        lockToboganA = new ReentrantLock(true);
        persEsperandoElToboganA = lockToboganA.newCondition();
        lockToboganB = new ReentrantLock(true);
        persEsperandoElToboganB = lockToboganB.newCondition();

        asignacion = 0;

        persSubiendoEscaleras = 0;
        persSolcitandoTobogan = 0;
        puedenTirarse = 0;
        toboganADisp = true;
        toboganBDisp = true;

        color = new TextoColorido();

    }

    public void irAlFaro(Persona p) {
        if (getAbierto()) {
            subirEscaleras(p);
            admirarDesdeLoAlto(p);
            avisarAlAdmin(p);
            esperarAsignacion(p);
            nadarEnLaPile(p);
        } else {
            //esto es para no sobrecargar una actividad
            System.out.println(color.getAmarillo() + p.getIdPersona() + " ve que el faro no está disponible");
        }
    }

    public void subirEscaleras(Persona p) {
        try {
            empezarASubir(p);
            Thread.sleep(1000);
            terminarDeSubir(p);
        } catch (Exception e) {
        }

    }

    public void empezarASubir(Persona p) {
        lockEscalera.lock();
        try {
            while (persSubiendoEscaleras > capacidadEscaleras) {
                esperandoEscalera.await();
            }
            persSubiendoEscaleras++;
            
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se encuentra subiendo las escaleras");
        } catch (Exception e) {
        } finally {
            lockEscalera.unlock();
        }

    }

    public void terminarDeSubir(Persona p) {
        lockEscalera.lock();
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " termino de subir las escaleras");
            persSubiendoEscaleras--;
            esperandoEscalera.signalAll();
        } catch (Exception e) {
        }finally {
            lockEscalera.unlock();
            
        }

    }

    public void admirarDesdeLoAlto(Persona p) {
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se encuentra admirando desde lo alto el esplendor de una maravilla natural");
            Thread.sleep(1000 * intAleatorio(1, 3));
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se dirige a los toboganes luego de admirar el esplendor");
        } catch (Exception e) {
        }
    }

    public void avisarAlAdmin(Persona p) {
        lockFaro.lock();
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " le avisa al administrador de toboganes que desea tirarse");
            persSolcitandoTobogan++;
            adminEsperando.signal();
        } catch (Exception e) {
        } finally {
            lockFaro.unlock();
        }
    }

    public void adminitrarToboganes() {
        lockFaro.lock();

        try {
            while (persSolcitandoTobogan < 1) {
                adminEsperando.await();
                //el admin se duerme si hay dos personas tirandose o si
                //no hay personas solicitanto tirarse
            }
            System.out.println(color.getAmarillo() + "El administrador de toboganes está viendo por donde se tirara");
            persSolcitandoTobogan--;
            asignacion = intAleatorio(0, 1);//0 se tira por A, 1 se tira por B

            puedenTirarse++;
            persEsperandoAsignacion.signalAll();
        } catch (Exception e) {
        } finally {
            lockFaro.unlock();
        }
    }

    public void esperarAsignacion(Persona p) {
        lockFaro.lock();
        try {
            while (puedenTirarse < 1) {
                persEsperandoAsignacion.await();
            }
            puedenTirarse--;
            System.out.println(color.getAmarillo() + p.getIdPersona() + " ya le fue asignado un tobogan, ahora procede a hacer la fila");
        } catch (Exception e) {
        } finally {
            lockFaro.unlock();
        }
        //0 se tira por A, 1 se tira por B
        if (meLargoPor()) {
            tirarsePorA(p);
        } else {
            tirarsePorB(p);
        }
    }

    public void tirarsePorA(Persona p) {
        try {
            empezarATirarsePorA(p);
            Thread.sleep(1000);//simulo el tiempo que tarda en el tobogan hasta llegar a la pileta
            terminarDeTirarsePorA(p);
        } catch (Exception e) {
        }
    }

    public void empezarATirarsePorA(Persona p) {
        lockToboganA.lock();
        try {
            while (!toboganADisp) {
                //mientras se esten tirando por el tobogan asignado espero
                persEsperandoElToboganA.await();
            }
            toboganADisp = false;
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se largo por el tobogan A");
        } catch (Exception e) {
        } finally {
            lockToboganA.unlock();
        }
    }

    public void terminarDeTirarsePorA(Persona p) {
        lockToboganA.lock();
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona()
                    + " llegó a la pileta de abajo desocupando el tobogan A");
            toboganADisp = true;
            persEsperandoElToboganA.signal();

        } catch (Exception e) {
        } finally {
            lockToboganA.unlock();
        }
    }

    public void empezarATirarsePorB(Persona p) {
        lockToboganB.lock();
        try {
            while (!toboganBDisp) {
                //mientras se esten tirando por el tobogan asignado espero
                persEsperandoElToboganB.await();
            }
            toboganBDisp = false;
            System.out.println(color.getAmarillo() + p.getIdPersona()
                    + " se largo por el tobogan B");
        } catch (Exception e) {
        } finally {
            lockToboganB.unlock();
        }
    }

    public void terminarDeTirarsePorB(Persona p) {
        lockToboganB.lock();
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona()
                    + " llegó a la pileta de abajo desocupando el tobogan B");
            toboganBDisp = true;
            persEsperandoElToboganB.signal();

        } catch (Exception e) {
        } finally {
            lockToboganB.unlock();
        }
    }

    public void tirarsePorB(Persona p) {
        try {
            empezarATirarsePorB(p);
            Thread.sleep(1500);//simulo el tiempo que tarda en el tobogan hasta llegar a la pileta
            terminarDeTirarsePorB(p);
        } catch (Exception e) {
        }
    }

    public boolean meLargoPor() {
        lockFaro.lock();
        boolean salida = true;
        try {
            if (asignacion == 1) {
                salida = false;//se larga por el tobogan B, caso contrario (asignacion == 0) se larga por A
            }
        } catch (Exception e) {
        } finally {
            lockFaro.unlock();
        }
        return salida;
    }

    public void nadarEnLaPile(Persona p) {
        try {
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se encuentra nadando en la pileta");
            Thread.sleep(1000);//simulo el tiempo que disfruta de la pileta
            System.out.println(color.getAmarillo() + p.getIdPersona() + " se va a disfrutar del resto del parque");
        } catch (Exception e) {
        }
    }

    public boolean getAbierto() {
        lockFaro.lock();
        boolean salida = false;
        try {
            salida = abierto;
        } catch (Exception e) {
        } finally {
            lockFaro.unlock();
        }
        return salida;
    }

    public void setAbierto(boolean abierto) {
        lockFaro.lock();
        try {
            this.abierto = abierto;
        } catch (Exception e) {
        } finally {
            lockFaro.unlock();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej4Puente;

/*
entre 30 y 37
colores
\u001B"32m

 */
import java.util.concurrent.Semaphore;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class PuenteSemaph {

    private int esperandoNorte;
    private int esperandoSur;
    private int cantCirculando;
    private Semaphore puedoCircularNorteSur;
    private Semaphore puedoCircularSurNorte;
    private Semaphore mutex;

    public PuenteSemaph() {
        puedoCircularNorteSur = new Semaphore(0, true);
        puedoCircularSurNorte = new Semaphore(1, true);
        mutex = new Semaphore(1, true);
        cantCirculando = 0;
    }

    public void entrarNorteASur(int idAuto) {
        try {
            mutex.acquire();
            esperandoNorte++;
            mutex.release();

            puedoCircularNorteSur.acquire();
            
            mutex.acquire();
            System.out.println("Auto " + idAuto + " entro de Norte a Sur");
            cantCirculando++;
            esperandoNorte--;
            mutex.release();
            
        } catch (Exception e) {
        }
    }

    public void entrarSurANorte(int idAuto) {
        try {
            mutex.acquire();
            esperandoSur++;
            mutex.release();

            puedoCircularSurNorte.acquire();
            mutex.acquire();
            System.out.println("Auto " + idAuto + " entro de Sur a Norte");
            cantCirculando++;
            esperandoSur--;
            mutex.release();
        } catch (Exception e) {
        }
    }

    public void salirSurANorte(int idAuto) {
        try {
            mutex.acquire();
            if (cantCirculando == 1) {
                System.err.println("Auto " + idAuto + " es el último en salir de sur a norte");
                Thread.sleep(2500);
                if (esperandoNorte > 0) {
                    System.out.println("salen de norte a sur");
                    puedoCircularNorteSur.release(esperandoNorte);
                } else if (esperandoSur > 0) {
                    System.out.println("salen de sur a norte");
                    puedoCircularSurNorte.release(esperandoSur);
                }
            } else {
                System.out.println("Auto " + idAuto + " salió");
            }
            cantCirculando--;
            mutex.release();
        } catch (Exception e) {
        }

    }

    public void salirNorteASur(int idAuto) {
        try {
            mutex.acquire();
            if (cantCirculando == 1) {
                System.err.println("Auto " + idAuto + " es el último en salir de norte a sur");
                Thread.sleep(2500);
                if (esperandoSur > 0) {
                    System.out.println("salen de sur a norte");
                    puedoCircularSurNorte.release(esperandoSur);
                } else if (esperandoNorte > 0) {
                    System.out.println("salen de norte a sur");
                    puedoCircularNorteSur.release(esperandoNorte);
                }

            } else {
                System.out.println("Auto " + idAuto + " salió");
            }
            cantCirculando--;
            mutex.release();
        } catch (Exception e) {
        }
    }

    public void circular(int idAuto) {
        try {
            System.out.println("El auto " + idAuto + " se encuentra circulando");
            Thread.sleep(1000 * intAleatorio(2, 5));
        } catch (Exception e) {
        }
    }
}

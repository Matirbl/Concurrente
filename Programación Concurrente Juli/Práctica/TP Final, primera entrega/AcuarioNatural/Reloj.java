/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import java.util.concurrent.Semaphore;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Reloj {

    private int hora;
    private int minutos;

    private Semaphore mutex;

    public Reloj(int hora) {
        this.hora = hora;
        this.minutos = 0;
        mutex = new Semaphore(1, true);
    }

    public void pasarTiempo() {
        try {
            mutex.acquire();
            hora = (hora + 1) % 24;
            minutos = intAleatorio(0, 59);
            mutex.release();
            if (hora > 8 && hora < 22) {
                Thread.sleep(3000);
            } else {
                //de las 23 a las 9 el tiepo pasa mas rapido para el testing
                Thread.sleep(1500);
            }

        } catch (Exception e) {
        }
    }

    public int getHora() {
        int salida = 0;
        try {
            mutex.acquire();
            salida = hora;
            mutex.release();
        } catch (Exception e) {
        }
        return salida;
    }

    public int getMinutos() {
        return minutos;
    }

    public String obtenerHora() {
        return (hora + ":" + minutos + " hrs");
    }

    public boolean parqueDisp() {
        boolean salida = false;
        try {
            mutex.acquire();
            if (hora >= 9 && hora <= 17) {
                salida = true;
            }
            mutex.release();
        } catch (Exception e) {
        }
        return salida;
    }

    public boolean actividadesDisp() {
        boolean salida = false;
        try {
            mutex.acquire();
            if (hora >= 9 && hora <= 18) {
                salida = true;
            }
            mutex.release();
        } catch (Exception e) {
        }
        return salida;
    }

    public boolean carreraDisp() {
        boolean salida = false;
        try {
            mutex.acquire();
            if (hora >= 9 && hora <= 14) {
                salida = true;
            }
            mutex.release();
        } catch (Exception e) {
        }
        return salida;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parcial;

import java.util.concurrent.Semaphore;

public class Pista {

    private final Semaphore pista;
    private final Semaphore despegar;
    private final Semaphore contadorAterrizajes;
    private final Semaphore contadorDespegues;
    private int quiereAterrizar, quiereDespegar, aterrizaron;
    private final int CANTIDAD_MAXIMA_ATERRIZAJES = 2;

    public Pista() {
        // Solo puede haber un avion en la pista
        this.pista = new Semaphore(1);
        
        // Solo puede despegar de a un avion
        this.despegar = new Semaphore(1);
        
        // Contadores auxiliares
        this.quiereAterrizar = 0;
        this.quiereDespegar = 0;
        this.aterrizaron = 0;
        
        // Semaforos para contadores
        this.contadorAterrizajes = new Semaphore(1);
        this.contadorDespegues = new Semaphore (1);
    }

    public void aterrizando() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+ " >> Un avion esta aterrizando");
        Thread.sleep(100);
    }


    public void despegando() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+ " >> Un avion esta despegando");
        Thread.sleep(100);
    }
    
    public void __despegar() throws InterruptedException {
        // Solicitud de despegue
        System.out.println(Thread.currentThread().getName()+ " >> Solicito despegue");
        this.contadorDespegues.acquire();
            this.quiereDespegar++;
        this.contadorDespegues.release();
        
        // Despego de a 1
        this.despegar.acquire();
        
        this.contadorDespegues.acquire();
            this.quiereDespegar--;
        this.contadorDespegues.release();
        
        System.out.println(Thread.currentThread().getName()+ " >> Despegue habilitado");
        
        // Tomo el lock de la pista
        this.pista.acquire();
    }
    
    
    public void __terminarDeDespegar() {       
        // Permito otro despegue
        this.despegar.release();

        System.out.println(Thread.currentThread().getName()+ " >> Termino mi despegue");
        
        // Libero el lock de la pista
        this.pista.release();
    }
    
    public void __aterrizar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+ " >> Solicito aterrizaje");
        
        this.contadorAterrizajes.acquire();
            this.quiereAterrizar++;
        this.contadorAterrizajes.release();
        
       
        // Nadie puede despegar hasta que llegue al umbral o no queden mas aterrizajes
        if (this.quiereAterrizar == 0) {
             despegar.acquire();
        }
        
        System.out.println(Thread.currentThread().getName()+ " >> Aterrizaje habilitado ");
        
        this.contadorAterrizajes.acquire();
            this.quiereAterrizar--;
        this.contadorAterrizajes.release();
        
        // Tomo el lock de la pista
        this.pista.acquire();
    }
    
    public void __terminarDeAterrizar() {
        // Registro un nuevo aterrizaje
        this.aterrizaron++;
        
        if (this.aterrizaron >= this.CANTIDAD_MAXIMA_ATERRIZAJES) {
            // Hubo demasiados arribos, prorizo los despegues
            System.out.println("* UMBRAL MAXIMO ALCANZADO");
            this.despegar.release();
            
            // Reseteo el contador
            this.aterrizaron = 0;
        } else if (this.quiereAterrizar == 0) {
            // No hay mas aterrizajes, permito despegues
            this.despegar.release();
        } 
        
        System.out.println(Thread.currentThread().getName()+ " >> Termino de aterrizar");
        
        // Libero el lock de la pista
        this.pista.release();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.BlancaNieves;

import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class Casa {
    
    private int sillaDisp;
    private int servirComida;
    private int comidaServida;
    
    public Casa(int cillaDisp) {
        this.sillaDisp = cillaDisp;
        this.servirComida = 0;
        this.comidaServida = 0;
    }

    public int getServirComida() {
        return servirComida;
    }

    public void setServirComida(int servirComida) {
        this.servirComida = servirComida;
    }
    
    
    public synchronized void empezarAServir() {
        try {
            while (servirComida < 1) {
                this.wait();
            }
            servirComida--;
            System.out.println("Blancanieves esta preparando el plato");
            
        } catch (Exception e) {
        }
        
    }
    
    public void servir() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    
    public synchronized void terminarAServir() {
        try {
            System.out.println("Blancanieves termino de servir un plato y le avisa al enanito");
            comidaServida++;
            this.notifyAll();
        } catch (Exception e) {
        }
    }
    
    public synchronized void sentarse(int idEnanito) {
        try {
            System.out.println("El enanito " + idEnanito + " desear comer");
            while (sillaDisp < 1) {
                this.wait();
            }
            sillaDisp--;
            System.out.println("El enanito " + idEnanito + " se encuentra sentado y avisa a Blancanieves");
            servirComida++;
            this.notifyAll();
        } catch (Exception e) {
        }
    }
    
    public synchronized void empezarAComer(int idEnanito) {
        try {
            while (comidaServida < 1) {
                this.wait();
            }
            comidaServida--;
            System.out.println("El enanito " + idEnanito + " ya tiene su plato");
        } catch (Exception e) {
        }
        
    }
    
    public void comer(int idEnanito) {
        try {
            System.err.println("El enanito " + idEnanito + " se encuentra comiendo");
            Thread.sleep(1000 * intAleatorio(3, 4));
        } catch (Exception e) {
        }
        
    }
    
    public synchronized void terminarDeComer(int idEnanito) {
        try {
            sillaDisp++;
            System.err.println("El enanito " + idEnanito + " termino de comer y se va a trabajar dejando la silla");
            this.notifyAll();
        } catch (Exception e) {
        }
    }
    
    public void trabajar(int idEnanito) {
        try {
            System.out.println("El enanito " + idEnanito + " se encuentra trabajando");
            Thread.sleep(1000 * intAleatorio(5, 10));
        } catch (Exception e) {
        }
        
    }
    
    public void pasearConElPrincipe() {
        try {
            System.out.println("Blancanieves se encuentra paseando con el principe");
            Thread.sleep(2500);
        } catch (Exception e) {
        }
        
    }
    
}

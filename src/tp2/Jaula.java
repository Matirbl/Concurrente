/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.concurrent.locks.ReentrantLock;

public class Jaula {

    private boolean comidaDisponible;
    private boolean hamacaDisponible;
    private boolean ruedaDisponible;

    private ReentrantLock comida = new ReentrantLock(),
            hamaca = new ReentrantLock(),
            rueda = new ReentrantLock();

    public Jaula() {
        comidaDisponible = true;
        hamacaDisponible = true;
        ruedaDisponible = true;
    }

    public void comer(String nom) {
        try {
            comida.lock();
            if (comidaDisponible) {
                comidaDisponible = false;
                comida.unlock();
                System.out.println("El Hamster " + nom + " empieza a comer");
                Thread.sleep(1000);
                System.out.println("El Hamster " + nom + " termina de comer");
                comida.lock();
                comidaDisponible = true;
            } else {
                System.out.println("esperando" + nom);
            }
            comida.unlock();
        } catch (Exception e) {
        }
    }

    public void hamarcarse(String nom) {
        try {
            hamaca.lock();
            if (hamacaDisponible) {
                hamacaDisponible = false;
                hamaca.unlock();
                System.out.println("El Hamster " + nom + " empieza a hamacarse");
                Thread.sleep(4000);
                System.out.println("El Hamster " + nom + " termina de hamacarse");
                hamaca.lock();
                hamacaDisponible = true;
            }
            hamaca.unlock();
        } catch (Exception e) {
        }

    }

    public void irAlaRueda(String nom) {
        try {
            rueda.lock();
            if (ruedaDisponible) {
                ruedaDisponible = false;
                rueda.unlock();
                System.out.println("El Hamster " + nom + " empieza a correr");
                Thread.sleep(2000);
                System.out.println("El Hamster " + nom + " termina de correr");
                rueda.lock();
                ruedaDisponible = true;
            }
            rueda.unlock();
        } catch (Exception e) {
        }
    }
}

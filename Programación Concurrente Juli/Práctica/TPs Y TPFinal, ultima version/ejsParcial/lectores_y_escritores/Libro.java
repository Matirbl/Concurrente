/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejsParcial.lectores_y_escritores;
/*
PREGUNTARRR:
    si necesita condicion de ser el ultimo escritor para avisar a los lectores
*/
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;
import java.util.logging.Logger;
import static utiles.Aleatorio.intAleatorio;

/**
 *
 */
public class Libro {

    private int cantPaginasTotales;//cantidad de paginas que va a tener el libro
    private int cantPaginasEscritas;//cantidad de paginas que van escribiendo el/los escritor/res
    private int cantLectoresLeyendo;//cantidad de escritores que se encuentran leyendo el libro 
    private int cantEscritoresEscribiendo;
    private boolean finalizado;
    private ReentrantLock lock;
    private Condition noLeyendo;
    private Condition noEscribiendo;

    public Libro(int cantPaginas) {
        this.cantPaginasTotales = cantPaginas;
        this.cantPaginasEscritas = 0;
        this.cantEscritoresEscribiendo = 0;
        this.finalizado = false;
        lock = new ReentrantLock(true);
        noLeyendo = lock.newCondition();
        noEscribiendo = lock.newCondition();
    }

    public void empezarALeer(Lector lector) {
        System.out.println("Lector  " + lector.getId() + " desea empezar a leeer");
        lock.lock();
        try {
            while (!puedoLeer(lector)) {
                noEscribiendo.await();
            }
            cantLectoresLeyendo++;
            System.out.println("Lector " + lector.getId() + " procede leer");
            Thread.sleep(500);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void leer(int idLector) {
        try {
            System.out.println("Lector " + idLector + " se encuentra leyendo");
            Thread.sleep(1000 * intAleatorio(2, 5));
        } catch (Exception e) {
        }
    }

    public void terminarDeLeer(Lector lector) {
        lock.lock();
        int cantPLeidas = lector.getCantPagLeidas() + intAleatorio(2, 5);
        try {
            if (cantPLeidas <= cantPaginasEscritas) {
                lector.setCantPagLeidas(cantPLeidas);
            } else {
                lector.setCantPagLeidas(lector.getCantPagLeidas() + 1);
            }
            if (lector.getCantPagLeidas() == cantPaginasTotales) {
                System.err.println("Lector " + lector.getId() + " terminó el libro");
                lector.setTerminarLibro(true);
            } else {
                System.err.println("Lector " + lector.getId() + " va por la pag " + lector.getCantPagLeidas());
            }
            cantLectoresLeyendo--;
            noLeyendo.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public boolean puedoLeer(Lector lector) {
        boolean salida = false;
        if (cantEscritoresEscribiendo == 0 && lector.getCantPagLeidas() < cantPaginasEscritas) {
            salida = true;
        }
        return salida;
    }

    public boolean puedoEscribir() {
        boolean salida = false;
        if (cantLectoresLeyendo == 0 && cantPaginasEscritas < cantPaginasTotales) {
            salida = true;
        }
        return salida;
    }

    public void empezarAEscribir(Escritor escritor) {
        lock.lock();
        try {
            while (!puedoEscribir()) {
                noLeyendo.await();
            }
            cantEscritoresEscribiendo++;
            System.out.println("Escritor " + escritor.getId() + " procede a escribir");
            Thread.sleep(500);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }

    public void terminarDeEscribir(Escritor escritor) {
        lock.lock();
        int cantPEscritas = cantPaginasEscritas + intAleatorio(2, 5);
        try {
            if (!finalizado) {
                if (cantPEscritas <= cantPaginasTotales) {
                    cantPaginasEscritas = cantPEscritas;
                } else {
                    cantPaginasEscritas++;
                }
                if (cantPaginasEscritas == cantPaginasTotales) {
                    System.err.println("Escritor " + escritor.getId() + " terminó el libro");
                    finalizado = true;
                } else {
                    System.err.println("Escritor " + escritor.getId() + " aumento a " + cantPaginasEscritas);
                }
            }
            else{
                System.out.println("El libro ya lo completo otro escritor!(:");
            }
            cantEscritoresEscribiendo--;
            noEscribiendo.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void escribir(int idEscritor) {
        try {
            System.out.println("Escritor " + idEscritor + " se encuentra escribiendo");
            Thread.sleep(1000 * intAleatorio(2, 5));
        } catch (Exception e) {
        }
    }

    public int getCantPaginasEscritas() {
        return cantPaginasEscritas;
    }

    public void setCantPaginasEscritas(int cantPaginasEscritas) {
        this.cantPaginasEscritas = cantPaginasEscritas;
    }

    public int getCantLectoresLeyendo() {
        return cantLectoresLeyendo;
    }

    public void setCantLectoresLeyendo(int cantLectoresLeyendo) {
        this.cantLectoresLeyendo = cantLectoresLeyendo;
    }

    public int getCantEscritoresEscribiendo() {
        return cantEscritoresEscribiendo;
    }

    public void setCantEscritoresEscribiendo(int cantEscritoresEscribiendo) {
        this.cantEscritoresEscribiendo = cantEscritoresEscribiendo;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}

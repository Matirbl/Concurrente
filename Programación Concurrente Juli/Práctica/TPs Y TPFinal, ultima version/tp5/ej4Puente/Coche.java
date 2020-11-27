/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej4Puente;

/**
 *
 */
public class Coche implements Runnable {

    private int id;
    private boolean lado;//true NorteSur - false SurNorte
    private PuenteSemaph recCom;

    public Coche(int id, boolean lado, PuenteSemaph recCom) {
        this.id = id;
        this.lado = lado;
        this.recCom = recCom;
    }

    @Override
    public void run() {
        while (true) {
            if (lado) {
                recCom.entrarNorteASur(id);
                recCom.circular(id);
                recCom.salirNorteASur(id);
            } else {
                recCom.entrarSurANorte(id);
                recCom.circular(id);
                recCom.salirSurANorte(id);
            }
            lado = !lado;
        }
    }
}

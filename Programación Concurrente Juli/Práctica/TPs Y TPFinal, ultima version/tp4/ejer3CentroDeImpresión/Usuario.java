/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ejer3CentroDeImpresión;

import static utiles.Aleatorio.booleanAleatorio;

/**
 *
 */
public class Usuario implements Runnable {

    private int idUser;
    private CentroDeImpresion recCom;

    public Usuario(int idUser, CentroDeImpresion r) {
        this.idUser = idUser;
        this.recCom = r;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public void run() {
        boolean b = booleanAleatorio();;
        try {
            while (true) {
                if (b == true) {
                    recCom.imprimirTipoA(idUser);

                } else {
                    recCom.imprimirTipoB(idUser);
                }
                Thread.sleep(350);
                b = booleanAleatorio();
            }

        } catch (Exception e) {
        }
    }

}

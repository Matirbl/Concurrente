/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

import utiles.TextoColorido;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Relojero implements Runnable {

    private AcuarioNatural recComp;
    private Reloj reloj;
    private TextoColorido color;

    public Relojero(Reloj r, AcuarioNatural recComp) {
        this.reloj = r;
        this.recComp = recComp;
        color = new TextoColorido();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(color.getRojo() + reloj.obtenerHora());
            if (reloj.getHora() == 9) {
                recComp.abrir();
            }
            if (reloj.getHora() == 17) {
                recComp.cerrar();
            }
            reloj.pasarTiempo();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class SnorkelAsistente implements Runnable{
    private Snorkel recComp;
    private int idAsistente;

    public SnorkelAsistente(Snorkel recComp, int idAsistente) {
        this.recComp = recComp;
        this.idAsistente = idAsistente;
    }

    @Override
    public void run() {
        while (true) {
            recComp.darEquipo(idAsistente);
        }
    }
    
}

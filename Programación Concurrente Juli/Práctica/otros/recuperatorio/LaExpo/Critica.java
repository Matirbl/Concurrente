/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperatorio.LaExpo;

/**
 *
 * @author 38493269
 */
public class Critica {

    /*
    Julián Domínguez 
    FAI 974
    */
    private boolean critica;
    private int idDeLaSala;
    private int idDelCritico;

    public Critica(boolean critica, int id1, int id2) {
        this.critica = critica;
        this.idDeLaSala = id1;
        this.idDelCritico = id2;
    }

    public boolean isCritica() {
        return critica;
    }

    public void setCritica(boolean critica) {
        this.critica = critica;
    }

    public String toString() {
        String salida = "";
        if (critica == true) {
            salida = "La sala " + idDeLaSala + "tuvo una buena critica por el critico " + idDelCritico;
        } else {
            salida = "La sala " + idDeLaSala + "tuvo una mala critica por el critico " + idDelCritico;
        }
        return salida;
    }
}

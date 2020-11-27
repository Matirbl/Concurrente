/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

/**
 *
 */
public class TextoColorido {

    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";

    public TextoColorido() {
    }

    public String getNegro() {
        return ANSI_BLACK;
    }

    public String getRojo() {
        return ANSI_RED;
    }

    public String getVerde() {
        return ANSI_GREEN;
    }

    public String getAmarillo() {
        return ANSI_YELLOW;
    }

    public String getAzul() {
        return ANSI_BLUE;
    }

    public String getVioleta() {
        return ANSI_PURPLE;
    }

    public String getCyan() {
        return ANSI_CYAN;
    }

    public String getBlanco() {
        return ANSI_WHITE;
    }

}

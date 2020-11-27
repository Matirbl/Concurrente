package tpo2_2dointento;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tiempo {

    public static final int TIEMPO = 1000;

    public static void esperar(int cant) {
        try {
            Thread.sleep(TIEMPO * cant);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

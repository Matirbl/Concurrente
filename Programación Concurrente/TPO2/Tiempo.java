package TPO2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tiempo {

    public static final int TIEMPO = 1000;

    public static void esperar(int cant) {
        try {
//            System.out.println("ME DUERMO SOY " +Thread.currentThread().getName());
            Thread.sleep(TIEMPO * cant);
//            System.out.println("VOLVI SOY " +Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

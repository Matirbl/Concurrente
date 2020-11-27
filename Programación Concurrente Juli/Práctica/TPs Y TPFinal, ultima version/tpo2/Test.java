package tpo2;

/**
 *
 * @author jdominguez
 */
public class Test {

    public static void main(String[] args) {
        MesaRedonda m = new MesaRedonda();
        Thread t;
        Filosofo f;
        boolean turno = true;
        for (int i = 0; i < 5; i++) {
            f = new Filosofo(m, i, turno);
            turno = !turno;
            t = new Thread(f);
            t.start();
        }
    }
}

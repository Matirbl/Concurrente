package tpo2;

/**
 *
 * @author jdominguez
 */
public class Filosofo implements Runnable {

    private int posMesa;
    private MesaRedonda mesa;
    private boolean turno;

    public Filosofo(MesaRedonda mesa, int pos, boolean turno) {
        this.mesa = mesa;
        this.posMesa = pos; //tiene sentido si el valor ingresado se encuentra entre 1 y 5 inclusive
        this.turno = turno;
    }

    @Override
    public void run() {
        while (true) {
            turno = !mesa.comer(posMesa, turno); 
//si retorna true encones ya comio, entonces ya no es mi turno,
//en caso contrario retorna false y entonces si es mi turno para comer
        }

    }

    public int getPosMesa() {
        return posMesa;
    }

    public void setPosMesa(int posMesa) {
        this.posMesa = posMesa;
    }

    public MesaRedonda getMesa() {
        return mesa;
    }

    public void setMesa(MesaRedonda mesa) {
        this.mesa = mesa;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

}

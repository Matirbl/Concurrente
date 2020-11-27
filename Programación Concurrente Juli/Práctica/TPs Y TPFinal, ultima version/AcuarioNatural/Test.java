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
public class Test {

    public static void main(String[] args) {
        //recursos compartidos
        Reloj r = new Reloj(9);
        Persona[] cantPersonas = new Persona[36];
        AcuarioNatural recCompartido = new AcuarioNatural(r);

        //runnables o ejecutables
        ColectivoConductor conductor1, conductor2;
        FaroAsistente asistenteDelFaro;
        SnorkelAsistente AsistenteDeSnorkel;
        CarreraCamioneta camioneta;
        CarreraTren tren;
        Relojero relojero;
        //al momento de abrir se liberan 100 permisos al acuario,
        //tiene que tener cierta correspondencia con
        //la cantidad de hilos (Persona)que se crean

        Thread hilo;

        //creo a los conductores, dos porque solo hay dos colectivos
        conductor1 = new ColectivoConductor(1, recCompartido.getLineaA());
        hilo = new Thread(conductor1);
        hilo.start();

        conductor2 = new ColectivoConductor(2, recCompartido.getLineaB());
        hilo = new Thread(conductor2);
        hilo.start();

        //creo el asistente del faro con vista esplendorosa, para controlar por que tobogan se tiran
        asistenteDelFaro = new FaroAsistente(recCompartido.getFaro());
        hilo = new Thread(asistenteDelFaro);
        hilo.start();

        //creo los asistentes de la laguna con nado snorkel, para dar los equipos necesarios para la actividad
        for (int i = 0; i < 2; i++) {
            AsistenteDeSnorkel = new SnorkelAsistente(recCompartido.getLaguna(), i + 1);
            hilo = new Thread(AsistenteDeSnorkel);
            hilo.start();
        }

        //creo los hilos necesarios para la actividad carrera de gomones en el rio
        tren = new CarreraTren(recCompartido.getCarrera(), r);
        hilo = new Thread(tren);
        hilo.start();

        camioneta = new CarreraCamioneta(recCompartido.getCarrera(), r);
        hilo = new Thread(camioneta);
        hilo.start();

        //creo cada persona con su correspondiente id y recurso compartido
        for (int i = 0; i < cantPersonas.length; i++) {
            cantPersonas[i] = new Persona(i + 1, recCompartido);
            hilo = new Thread(cantPersonas[i]);//instacio cada hilo correspodiente a cada persona
            hilo.start();//y pasa a estar listo para ser ejecutado
        }

        //creo al relojero para controlar cuando abre y cierra el parque
        relojero = new Relojero(r, recCompartido);
        hilo = new Thread(relojero);
        hilo.start();

    }
}

package TPO2;

import java.util.concurrent.Semaphore;
import utiles.Aleatorio;

public class SuperMercado {

    public static void main(String[] args) {
        // arreglos
        Deposito avisaStock = new Deposito();
        Cajera[] cajeras = new Cajera[5];
        Distribuidor[] distribuidores = new Distribuidor[3];
        Producto[] productos = new Producto[5];
        Gerente juan = new Gerente(productos,avisaStock);

        // creo instancias de cajeras, productos y distribuidores
        cargarSuper(productos, cajeras, distribuidores,avisaStock);

        // inicio los hilos
        iniciarHilos(cajeras, distribuidores, juan);

        // iniciar mundo
        int cantDias = 1, tiempoDia = 5;
        abrirSupermercado(productos, cajeras, cantDias, tiempoDia);
        cerrarSupermercado(cajeras, distribuidores, juan);
    }

    private static void cargarSuper(Producto[] productos, Cajera[] cajeras, Distribuidor[] distribuidores, Deposito avisaStock) {
        for (int j = 0; j < productos.length; j++) {
            int delay = Aleatorio.intAleatorio(1, 5);
            boolean esOferta = Aleatorio.intAleatorio(0, 2) == 1;
            int precio = Aleatorio.intAleatorio(10, 100);

            productos[j] = new Producto(delay, esOferta, precio);
        }
        for (int i = 0; i < cajeras.length; i++) {
            cajeras[i] = new Cajera("Cajera " + (i + 1));
        }
        for (int k = 0; k < distribuidores.length; k++) {
            distribuidores[k] = new Distribuidor(productos,avisaStock);

        }
    }

    private static void iniciarHilos(Cajera[] cajeras, Distribuidor[] distribuidores, Gerente g1) {
        for (int i = 0; i < cajeras.length; i++) {
            Thread hiloCajera = new Thread(cajeras[i], "Cajera " + (i + 1));
            hiloCajera.start();
        }

        for (int i = 0; i < distribuidores.length; i++) {
            Thread hiloDistribuidor = new Thread(distribuidores[i], "Distribuidor " + (i + 1));
            hiloDistribuidor.start();
        }

        Thread gerente = new Thread(g1, "Gerente Juan");
        gerente.start();
    }

    private static void abrirSupermercado(Producto[] productos, Cajera[] cajeras, int cantDias, int tiempoDia) {
        for (int j = 0; j < cantDias; j++) {
            Reloj unReloj = new Reloj(tiempoDia);       //Este reloj simula un dia
            System.out.println("Empieza el dia");
            Thread reloj = new Thread(unReloj, "Reloj");
            reloj.start();
            int i = 1;
            int cantCliPorDia = 1;
            while (unReloj.esActivo() && cantCliPorDia <= 3 ) {
                Cliente c1 = new Cliente("Cliente " + i, productos);
                Thread hiloCliente = new Thread(c1, "Cliente " + i);
                hiloCliente.start();

                Cajera cajeraAleatoria = cajeras[Aleatorio.intAleatorio(0, cajeras.length)];
                cajeraAleatoria.setCliente(c1);

//                Tiempo.esperar(3);

                i++;
                cantCliPorDia++;
            }

            System.out.println("Termina el dia");
        }
    }

    private static void cerrarSupermercado(Cajera[] cajeras, Distribuidor[] distribuidores, Gerente g1) {
        for (int l = 0; l < cajeras.length; l++) {
            cajeras[l].desactivar();
        }
        for (int i = 0; i < distribuidores.length; i++) {
            distribuidores[i].desactivar();
        }

        g1.desactivar();
    }
}

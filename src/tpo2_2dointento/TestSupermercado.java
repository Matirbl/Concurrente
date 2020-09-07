package tpo2_2dointento;

public class TestSupermercado {

    public static void main(String[] args) {
        final int cantCintas = 1;   // una cinta por cajera
        
        // recursos compartidos
        Producto[] catalogo = new Producto[10];
        Deposito deposito = new Deposito();
        Cinta[] cintas = new Cinta[cantCintas];
        
        // actores del supermercado
        Reloj reloj = new Reloj(40); // cantidad de seguros que dura un dia
        Gerente gerente = new Gerente(catalogo, deposito,reloj);
        Distribuidor[] distribuidores = new Distribuidor[1];
        Cajera[] cajeras = new Cajera[cantCintas];
        

        // cargo supermercado
        cargarCatalogo(catalogo);
        cargarCintas(cintas);

        // abro el super
        abrirSupermercado(cajeras, cintas, gerente, distribuidores,reloj, deposito, catalogo);

    }

    public static void cargarCatalogo(Producto[] catalogo) {
        // carga aleatoriamente el catalogo de productos del supermercado
        for (int i = 0; i < catalogo.length; i++) {
            int stockMinimo = Aleatorio.intAleatorio(3, 6);
            int precio = Aleatorio.intAleatorio(10, 100);
            int delay = Aleatorio.intAleatorio(1, 5);
            int cantidad = Aleatorio.intAleatorio(0, 10);
            boolean oferta = Aleatorio.intAleatorio(0, 2) == 1;

            catalogo[i] = new Producto(precio, delay, oferta, cantidad, stockMinimo);
        }
    }

    private static void cargarCintas(Cinta[] cintas) {
        // carga las cintas del supermercado que se asignaran a las cajeras
        for (int i = 0; i < cintas.length; i++) {
            cintas[i] = new Cinta(i + 1);
        }
    }

    private static void iniciarCajeras(Cajera[] cajeras, Cinta[] cintas, Reloj reloj) {
        // nuevos hilos de cajeras comienzan a trabajar
        for (int i = 0; i < cajeras.length; i++) {
            Cajera c1 = new Cajera(cintas[i], reloj);
            cajeras[i] = c1;
            Thread hilo = new Thread(c1, "Cajera " + (i + 1));
            hilo.start();
        }
    }

    private static void iniciarDistribuidores(Distribuidor[] distribuidores, Deposito deposito) {
        // nuevos hilos de distribuidores comienzan a trabajar
        for (int i = 0; i < distribuidores.length; i++) {
            distribuidores[i] = new Distribuidor(deposito);
            Thread hiloDistribuidor = new Thread(distribuidores[i], "Distribuidor " + (i + 1));
            hiloDistribuidor.start();
        }

    }

    private static void iniciarGerente(Gerente gerente) {
        gerente.setEsActivo(true);
        Thread hiloGerente = new Thread(gerente, "Gerente 1");
        hiloGerente.start();
    }

    private static void iniciarReloj(Reloj reloj) {
        reloj.setEsActivo(true);  // seteo el reloj en true para el nuevo dia
        Thread hiloReloj = new Thread(reloj, "Reloj");
        hiloReloj.start();
    }

    private static void abrirSupermercado(Cajera[] cajeras, Cinta[] cintas, Gerente gerente, Distribuidor[] distribuidores,Reloj reloj, Deposito deposito, Producto[] catalogo) {
        final int totalClientesPorDia = 5;  // maximo de clientes por dia
        int cantidadDias = 5;  // cantidad de dias que abre el supermercado

        for (int i = 0; i < cantidadDias; i++) {
            int j = 1; // cantidad de clientes que pasaron ese dia
            System.err.println("ABRE EL SUPERMERCADO VARATIJA ");

            // inicio los hilos de los actores
            iniciarGerente(gerente);
            iniciarDistribuidores(distribuidores, deposito);
            iniciarCajeras(cajeras, cintas, reloj);  // se abren las cajeras
            iniciarReloj(reloj);
            abrirDeposito(deposito);
            abrirCintas(cintas);

            while (reloj.esActivo()) {
                if (j <= totalClientesPorDia) {
                    int cajaAleatoria = Aleatorio.intAleatorio(0, cintas.length);
                    Cliente c1 = new Cliente(catalogo, cintas[cajaAleatoria]);
                    Thread hilo = new Thread(c1, "Cliente " + j);
                    hilo.start();
                    j++;
                }
            }

            cerrarSupermercado(cintas, cajeras, distribuidores, deposito, gerente); // finaliza el dia, se cierran las cintas
            Tiempo.esperar(15); // solo para sincronizar los mensajes
            System.err.println("CIERRA EL SUPERMERCADO VARATIJA");
        }

    }

    private static void abrirDeposito(Deposito deposito) {
        deposito.abrir();
    }

    private static void abrirCintas(Cinta[] cintas) {
        for (int i = 0; i < cintas.length; i++) {
            cintas[i].abrir();
        }
    }

    private static void cerrarSupermercado(Cinta[] cintas, Cajera[] cajeras, Distribuidor[] distribuidores, Deposito deposito, Gerente gerente) {
        // avisa a todos los actores que el supermercado cerro
        for (int i = 0; i < cajeras.length; i++) {
            cajeras[i].cerrar();
        }

        // notifica a las cajeras y a los clientes que cerro el super 
        for (int i = 0; i < cintas.length; i++) {
            cintas[i].cerrar();
        }

        for (int i = 0; i < distribuidores.length; i++) {
            distribuidores[i].cerrar();
        }

        gerente.cerrar();

//         notifica al gerente y a los distruibudores que cerro el super
        deposito.cerrar();
    }

}

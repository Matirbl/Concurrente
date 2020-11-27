package TPO2;

import utiles.Aleatorio;

public class Cliente implements Runnable {

    private Producto[] carroCompra, catalogo;
    private String nombre;

    public Cliente(String nom, Producto[] prods) {
        this.nombre = nom;
        this.catalogo = prods;
    }

    public String getNombre() {
        // devuelve el nombre 
        return this.nombre;
    }

    public Producto[] getCarroCompra() {
        // devuelve el carro de compras
        return carroCompra;
    }

    public boolean yaCompre() {
        return (this.carroCompra != null && this.carroCompra[carroCompra.length-1] != null);
    }

    public synchronized void cargarCarro() {
        // carga el carro de productos de forma aleatoria
        int cant = Aleatorio.intAleatorio(1, 3);
        this.carroCompra = new Producto[cant];
        for (int i = 0; i < cant; i++) {
            int aleatorio = Aleatorio.intAleatorio(0, cant);
            this.carroCompra[i] = catalogo[aleatorio].obtenerUnidad();

            // Simulo la carga del producto al carro 
            System.out.println("El cliente " + Thread.currentThread().getName() + " cargo un nuevo producto al carro");
            Tiempo.esperar(Aleatorio.intAleatorio(1, 3));
        }
    }

    public synchronized void avisarCajera() {
        this.notifyAll();
    }
    
    public void run() {
//        System.out.println("soy " + Thread.currentThread().getName() + " y entre a comprar");
        cargarCarro();
        this.avisarCajera();
        System.out.println("TERMINE " +this.nombre);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNievesSem;

public class Main {

    public static void main(String[] args) {

        String[] nombres = {"Mocoso", "Sabio", "Gruñon", "Feliz", "Dormilon", "Tímido", "Mudito"};

        Mesa laMesa = new Mesa();

        Thread[] enanos = new Thread[7];

        Thread blancaNieves = new Thread(new BlancaNievesSem(laMesa));
        blancaNieves.start();

        for (int i = 0; i < enanos.length; i++) {
            enanos[i] = new Thread(new Enano(nombres[i], laMesa));
        }

        for (int i = 0; i < enanos.length; i++) {
            enanos[i].start();

        }

    }
}

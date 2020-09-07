/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlancaNieves;

public class testBlancaNieves {

    public static void main(String[] args) {

        Mesa unaMesa = new Mesa();
        Thread hiloBlanca = new Thread(new BlancaNieves(unaMesa));

        Thread enanos[] = new Thread[7];

        for (int i = 0; i < enanos.length; i++) {
            enanos[i] = new Thread(new Enano(unaMesa, i));
        }

        for (int i = 0; i < enanos.length; i++) {
            enanos[i].start();
        }
        hiloBlanca.start();

    }
}

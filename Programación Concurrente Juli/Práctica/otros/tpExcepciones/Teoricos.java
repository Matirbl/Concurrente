package tpExcepciones;

public class Teoricos {

    /*
     1: el nombre de la clase en Java de excepciones es Exception
     2: el nombre en java de la clase que representa las excepciones
     que se producen al invocar un método de un objeto cuyo valor es 
     null es NullPointerException y en un comportamiento anómalo en 
     la entrada/salida es IOException
     3: la peculiaridad de las excepciones del tipo RuntimeException es
     errores del programador, como una división por cero o 
     el acceso fuera de los límites de un array
     4: se produce una excepcion de tipo NullPointerException
     5: se produce una excepcion de tipo NumberFormatException
         
     */
        //punto 6
    public static double acceso_por_indice(double[] v, int j) {

        try {
            if ((0 <= j) && (j <= v.length)) {
                return v[j];
            } else {
                throw new ArrayIndexOutOfBoundsException("El indice " + j + " no existe en el vector");
            }
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println(exc.getMessage());
            return -1;
        }

    }

    public static void main(String[] args) {
        double[] v = new double[15];
        double valorRetornado = acceso_por_indice(v, 16);
        System.out.println(valorRetornado);
    }

}

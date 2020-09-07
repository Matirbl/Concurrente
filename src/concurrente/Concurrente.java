package concurrente;

public class Concurrente {

    /**
     * @param args the command line arguments
     */
    public static double acceso_por_indice(double[] v, int j) throws RuntimeException {
     //   try {
            if ((0 <= j) && (j <= v.length)) {
                return v[j];
            } else {
                throw new RuntimeException();
            }
//        } catch (RuntimeException exc) {
//            System.out.println("El indice " + j + " no existe en el vector");
//
//            throw exc;
//        }
    }
    // Desde el siguiente cliente “main”:

    public static void main(String[] args) {
        double[] v = new double[15];
        try {
            acceso_por_indice(v, 16);
        }catch(RuntimeException e){
            System.out.println("El numero no entra en el rango");
        }
    }
    
    
  
}

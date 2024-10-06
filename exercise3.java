package L3;
import java.util.Random;
public class exercise3 {
    public static void main(String[] args) {
        Random rand = new Random();
        DatosSoldados[] ejercito1 = new DatosSoldados[rand.nextInt(5) + 1];
        DatosSoldados[] ejercito2 = new DatosSoldados[rand.nextInt(5) + 1];
        crearObjetos(ejercito1);
        crearObjetos(ejercito2);
        darNombres(ejercito1,'A');
        darNombres(ejercito2, 'B');
        System.out.println("Soldados del ejercito A:");
        imprimir(ejercito1);
        System.out.println("\nSoldados del ejercito B:");
        imprimir(ejercito2);
        ganador(ejercito1, ejercito2);
        

    }
    public static void darNombres(DatosSoldados[] ejercitoN, char distintivo) {
        for(int i = 0; i < ejercitoN.length; i++) {
            ejercitoN[i].setNombre("Soldado " + (i + 1) + distintivo);
        }
    }
    public static void crearObjetos(DatosSoldados[] ejercitoN) {
        for(int i = 0; i < ejercitoN.length; i++) {
            ejercitoN[i] = new DatosSoldados();
        }
    }
    public static void imprimir(DatosSoldados[] soldados) {
        for(int i = 0; i < soldados.length; i++) {
            System.out.println("    " + soldados[i].getNombre());
        }
    }
    public static void ganador(DatosSoldados[] ejercito1, DatosSoldados[] ejercito2) {
        if (ejercito1.length > ejercito2.length)
            System.out.println("\nEl ejercito A gana");
        else if (ejercito1.length < ejercito2.length)
            System.out.println("\nEl ejercito B gana");
        else
            System.out.println("\nEmpate");
    }
}


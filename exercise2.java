package L3;

import java.util.*;
public class exercise2 {
    public static void main(String[] args) {
    DatosSoldados[] soldados = new DatosSoldados[5];
        for (int  i = 0; i < soldados.length; i++) {
            soldados[i] = new DatosSoldados();
        }
        recibirDatos(soldados);
        imprimir(soldados);
        
    }
    public static void recibirDatos(DatosSoldados[] soldados) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < soldados.length; i++) {
            System.out.println("Ingrese el nombre y vida del soldado " + (i + 1) + ": ");
            System.out.print("\tNombre: ");
            soldados[i].setNombre(sc.nextLine());
            System.out.print("\tVida (1 - 5): ");
            soldados[i].setVida(sc.nextInt());
            sc.nextLine();

        }
    }
    public static void imprimir(DatosSoldados[] soldados) {
        System.out.println("\nSoldado\t|\tVida");
        System.out.println("---------------------");
        for (int i = 0; i < soldados.length; i++) {
            System.out.println(soldados[i].getNombre() + "   |\t" + soldados[i].getVida());
            System.out.println("---------------------");
        }
    }
}
    



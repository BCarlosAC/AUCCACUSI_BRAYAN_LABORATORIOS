package L5;

import java.util.*;
public class VideoJuego2 {
    public static void main(String [] args){
        Random rand = new Random();
        Soldado[][] campo = new Soldado[10][10];
        int i = 1;
        boolean[][] ocupados = new boolean[10][10];
        Soldado[] ordenCreacion = new Soldado[10];
        int indice = 0;
        while(i <= 10) {
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            while(ocupados[fila][columna] == true){
                fila = rand.nextInt(10);
                columna = rand.nextInt(10);
            }
            ocupados[fila][columna] = true;
            Soldado soldado = new Soldado();
            if(i == 10){
                soldado.setNombre("Soldado10");
            }
            else {
                soldado.setNombre("Soldado " + i);
            }
            soldado.setVida(rand.nextInt(5) + 1);
            soldado.setFila(fila);
            soldado.setColumna((char)('A' + columna));
            soldado.setEstado(true);
            campo[fila][columna] = soldado;
            ordenCreacion[indice++] = soldado;
            i++;
        }
        mostrarTabla(campo);
        mostrarMayorVida(campo);
        promedioVida(campo);
        nivelDeVidaEjercito(ordenCreacion);
        System.out.println("\nDatos de los soldados en orden de creación:");
        mostrar(ordenCreacion);
        aleatorio(ordenCreacion);
        System.out.println("\nAleatorio");
        mostrar(ordenCreacion);
        System.out.println("\nOrdenamiento BURBUJA");
        ordenarPorVidaBurbuja(ordenCreacion);
        mostrar(ordenCreacion);
        aleatorio(ordenCreacion);
        System.out.println("\nAleatorio");

        mostrar(ordenCreacion);
        System.out.println("\nOrdenamiento SELECCION");
        ordenarPorVidaSeleccion(ordenCreacion);
        mostrar(ordenCreacion);
        System.out.println("\nRanking de mayor a menor: ");
        rankingMayorMenor(ordenCreacion);
        mostrar(ordenCreacion);

    }


    public static void mostrarTabla(Soldado[][] camp) {
        for(char i = 'A'; i < 'K'; i++)
        {
            System.out.print("       " + i + "  ");
        }
        
        System.out.println();
        for(int i = 0; i < camp.length; i++)
        {
            if(i == 9){
                System.out.print(i + 1);
            }
            else{
                System.out.print((i + 1) + " ");
            }
            for(int j = 0; j < camp[i].length; j++)
            {
                if (camp[i][j] != null && camp[i][j].getEstado())
                    System.out.print("|" + camp[i][j].getNombre());
                else
                    System.out.print("|         ");
            }
            System.out.println("|");
            System.out.println("   ---------------------------------------------"+
            "-------------------------------------------------------");
        }
        
    }


    public static void mostrarMayorVida(Soldado[][] campo){
        Soldado mayor = null;
        int filMayor = 0;
        int colMayor = 0;
        for (int i = 0; i < campo.length; i++) {
            for(int j = 0; j < campo[i].length; j++){
                if (campo[i][j] != null && campo[i][j].getEstado()){
                    if(mayor == null || campo[i][j].getVida() > mayor.getVida()){
                        mayor = campo[i][j];
                        filMayor = i;
                        colMayor = j;
                    }
                } 
                
            }
        }
        System.out.println("El soldado con mayor nivel de vida es: ");
        System.out.println(campo[filMayor][colMayor].toString());
    }


    public static void promedioVida(Soldado[][] campo){
        int suma = 0;
        for(int i = 0; i < campo.length; i++){
            for(int j = 0; j < campo[i].length; j++){
                if(campo[i][j] != null){
                    suma += campo[i][j].getVida();
                }
            }
        }
        System.out.println("\nEl promedio de vida de los 10 soldados es: " + (suma / campo.length));
    }

    public static void nivelDeVidaEjercito(Soldado[] ordenSoldados){
        int suma = 0;
        for(int i = 0; i < ordenSoldados.length; i++) {
            suma += ordenSoldados[i].getVida();
        }
        System.out.println("\nEl nivel de vida del ejercito es: " + suma);
    }

    public static void mostrar(Soldado[] ordenSoldados){
        System.out.println();
        for (Soldado soldado : ordenSoldados) {
            System.out.println(soldado.toString());
        }
    }


    public static void ordenarPorVidaBurbuja(Soldado[] ordenSoldados){
        for (int i = 0; i < ordenSoldados.length; i++) {
            for (int j = 0; j < ordenSoldados.length - 1 - i; j++) {
                if(ordenSoldados[j].getVida() > ordenSoldados[j + 1].getVida()) {
                    Soldado temp = ordenSoldados[j];
                    ordenSoldados[j] = ordenSoldados[j + 1];
                    ordenSoldados[j + 1] = temp;
                }
            }
        }
    }
    public static void ordenarPorVidaSeleccion(Soldado[] ordenCreacion) {
        for (int i = 0; i < ordenCreacion.length - 1; i++) {
            int indexMenor = i;
            for (int j = i + 1; j < ordenCreacion.length; j++) {
                if (ordenCreacion[j].getVida() < ordenCreacion[indexMenor].getVida()) {
                    indexMenor = j; 
                }
            }
            if (indexMenor != i) {
                Soldado temp = ordenCreacion[i];
                ordenCreacion[i] = ordenCreacion[indexMenor];
                ordenCreacion[indexMenor] = temp;
            }
        }
    }
    public static void aleatorio(Soldado[] ordenCreacion) {
        Random rand = new Random();
        for (int i = ordenCreacion.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Soldado temp = ordenCreacion[i];
            ordenCreacion[i] = ordenCreacion[j];
            ordenCreacion[j] = temp;
        }
    }
    public static void rankingMayorMenor(Soldado[] ordenSoldados) {
        for (int i = 0; i < ordenSoldados.length; i++) {
            for (int j = 0; j < ordenSoldados.length - 1 - i; j++) {
                if (ordenSoldados[j].getVida() < ordenSoldados[j + 1].getVida()) {
                    Soldado temp = ordenSoldados[j];
                    ordenSoldados[j] = ordenSoldados[j + 1];
                    ordenSoldados[j + 1] = temp;
                }
            }
        }
    }
}
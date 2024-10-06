package L3;
import java.util.*;
public class demoBatalla {
    public static void main(String [] args){
    Nave [] flota = new Nave[10];
    Scanner sc = new Scanner(System.in);
    String nomb, col;
    int fil, punt;
    boolean est;
    for (int i = 0; i < flota.length; i++) {
        System.out.println("\nNave " + (i + 1));
        System.out.print("Nombre: ");
        nomb = sc.next();
        System.out.print("Fila: ");
        fil = sc.nextInt();
        System.out.print("Columna: ");
        col = sc.next();
        System.out.print("Estado: ");
        est = sc.nextBoolean();
        System.out.print("Puntos: ");
        punt = sc.nextInt();
        flota[i] = new Nave(); //Se crea un objeto Nave y se asigna su referencia a misNaves
        flota[i].setNombre(nomb);
        flota[i].setFila(fil);
        flota[i].setColumna(col);
        flota[i].setEstado(est);
        flota[i].setPuntos(punt);
    }
    System.out.println("\nNaves creadas:");
    mostrarNaves(flota);
    System.out.println();
    System.out.print("Ingrese el nombre de la nave(s) a mostrar: ");
    String elNombre = sc.next();
    sc.nextLine();
    mostrarPorNombre(flota, elNombre);
    System.out.print("\nIngrese el limite de puntos de la(s) nave(s) a mostrar: ");
    int puntosPedidos = sc.nextInt();
    sc.nextLine();
    
    mostrarPorPuntos(flota, puntosPedidos);
    System.out.println("\nLa nave con mayor número de puntos es: ");
    mostrarDatosNave(mostrarMayorPuntos(flota));
    System.out.println();
    Nave[] arrAleatorio = devuelveArray(flota);
    System.out.println("Aleatorio");
    mostrarNaves(arrAleatorio);
    
    }
    //Método para mostrar todas las naves
    public static void mostrarNaves(Nave[] flota){
        for (Nave misNaves : flota){
            System.out.println("\tNombre: "+ misNaves.getNombre() + "\tFila: " 
            + misNaves.getFila() + "\tColumna: " + misNaves.getColumna() + 
            "\tEstado: " + misNaves.getEstado() + "\t Puntos: " + misNaves.getPuntos());
        }

    }
    //Método para mostrar todas las naves de un nombre que se pide por teclado
    public static void mostrarPorNombre(Nave[] flota, String nombre){
        for (int i = 0; i < flota.length; i++) {
            if(flota[i].getNombre().equalsIgnoreCase(nombre)){
                System.out.println("\tNombre: "+ flota[i].getNombre() + "\tFila: " 
                + flota[i].getFila() + "\tColumna: " + flota[i].getColumna() + 
                "\tEstado: " + flota[i].getEstado() + "\t Puntos: " + flota[i].getPuntos());
        
            }
        }
    }
    //Método para mostrar todas las naves con un número de puntos inferior o igual
    //al número de puntos que se pide por teclado
    public static void mostrarPorPuntos(Nave [] flota, int puntosPedidos){
        for (int i = 0; i < flota.length; i++) {
            if(flota[i].getPuntos() <= puntosPedidos)
                System.out.println("\tNombre: "+ flota[i].getNombre() + "\tFila: " 
                + flota[i].getFila() + "\tColumna: " + flota[i].getColumna() + 
                "\tEstado: " + flota[i].getEstado() + "\t Puntos: " + flota[i].getPuntos());
        }
    }
    //Método que devuelve la Nave con mayor número de Puntos
    public static Nave mostrarMayorPuntos(Nave [] flota){
        int indexMayor = 0;
        for (int i = 0; i < flota.length; i++) {
            if(flota[i].getPuntos() > flota[indexMayor].getPuntos())
                indexMayor = i;
        }
        return flota[indexMayor];
    }
    //Crear un método que devuelva un nuevo arreglo de objetos con todos los 
    //objetos previamente ingresados
    //pero aleatoriamente desordenados
    public static Nave[] devuelveArray(Nave[] flota) {
        Random rand = new Random();
        int r1, r2;
        Nave auxiliar;
        Nave[] arrAleatorio = new Nave[flota.length];
        for (int i = 0; i < flota.length; i++) {
            arrAleatorio[i] = flota[i];
        }
        for(int i = 0; i < arrAleatorio.length; i++) {
            r1 = rand.nextInt(arrAleatorio.length);
            r2 = rand.nextInt(arrAleatorio.length);
            auxiliar = arrAleatorio[r1];
            arrAleatorio[r1] = arrAleatorio[r2];
            arrAleatorio[r2] = auxiliar;
        }
        return arrAleatorio;
    }
    public static void mostrarDatosNave(Nave naveParticular) {
        System.out.println("\tNombre: "+ naveParticular.getNombre() + "\tFila: "
         + naveParticular.getFila() + "\tColumna: " + naveParticular.getColumna() +
          "\tEstado: " + naveParticular.getEstado() + "\t Puntos: " 
          + naveParticular.getPuntos());
    }
}
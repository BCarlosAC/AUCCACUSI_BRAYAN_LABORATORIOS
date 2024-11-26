package L8;
import java.util.*;
public class VideoJuego5 {
    public static void main(String [] args){
        char decicion = 'y';
        while(decicion == 'y'){
            Scanner sc = new Scanner(System.in);
            Random rand = new Random();
            Soldado[][] campo = new Soldado[10][10];
            Soldado[] ej1 = new Soldado[rand.nextInt(10) + 1];
            Soldado[] ej2 = new Soldado[rand.nextInt(10) + 1];
            crearSoldados(ej1, '@');
            crearSoldados(ej2, '?');
            asignarSoldados(campo, ej1);
            asignarSoldados(campo, ej2);
            mostrarTabla(campo);
            System.out.println("Mayor vida ejercito 1: \n\t" + ej1[mayorVida(ej1)]);
            System.out.println("Mayor vida ejercito 2: \n\t" + ej2[mayorVida(ej2)]);
            System.out.println("\nPromedio vida ejercito 1: " + promedioEjercito(ej1));
            System.out.println("Promedio vida ejercito 2: " + promedioEjercito(ej1));
            System.out.println("\nDatos en orden que fueron creados");
            mostrar(ej1, 1);
            mostrar(ej2, 2);
            System.out.println("\nRanking de poder mayor a menor vida");
            System.out.println("BURBUJA");
            rankingMayorMenorBurbuja(ej1);
            mostrar(ej1, 1);
            rankingMayorMenorBurbuja(ej2);
            mostrar(ej2, 2);
    
            System.out.println("\nAleatorio");
            aleatorio(ej1);
            aleatorio(ej2);
            mostrar(ej1, 1);
            mostrar(ej2, 2);
    
            System.out.println("\nSELECCION");
            rankingMayorMenorSeleccion(ej1);
            mostrar(ej1, 1);
            rankingMayorMenorSeleccion(ej2);
            mostrar(ej2, 2);
    
            System.out.println();
            determinarGanador(ej1, ej2);

            System.out.print("Desea iniciar un nuevo juego? (y/n): ");
            decicion = sc.nextLine().charAt(0);
        }
        
    }
    
    public static void crearSoldados(Soldado[] ej, char a){
        Random rand = new Random();
        for(int i = 0; i < ej.length; i++){
            ej[i] = new Soldado();
            ej[i].setVida(rand.nextInt(5) + 1);
                ej[i].setNombre(i + "." + a + ej[i].getVida());
        }
    }
    public static void asignarSoldados(Soldado[][] campo, Soldado[] ej){
        Random rand = new Random();
        int fila, columna;
        for (int i = 0; i < ej.length; i++){
            boolean aux = true;
            while(aux){
                fila = rand.nextInt(10);
                columna = rand.nextInt(10);
                if(campo[fila][columna] == null){
                    campo[fila][columna] = ej[i];
                    ej[i].setFila(fila);
                    ej[i].setColumna(columna);
                    aux = false;
                }
            }
        }
    
    }
    public static void mostrarTabla(Soldado[][] campo){
        System.out.print("  ");
        for(char i = 'A'; i < 'K'; i++){
            System.out.print("   " + i + " ");
        }
        System.out.println();
        for(int i = 0; i < campo.length; i++){
            if(i == 9)
                System.out.print(i + 1);
            else
                System.out.print((i + 1) + " ");

            for(int j = 0; j < campo[i].length; j++){
                if (campo[i][j] != null)
                    System.out.print("|" + campo[i][j].getNombre());
                else
                    System.out.print("|    ");
            }
            System.out.println(" |");
            System.out.println("  ----------------------------------------------------");
        }
    }
    public static int mayorVida(Soldado[] ej){
        int indexMayor = 0;
        for(int i = 0; i < ej.length - 1; i++){
            if(ej[indexMayor].getVida() < ej[i].getVida()){
                indexMayor = i;
            }
        }
        return indexMayor;
    }

    public static double promedioEjercito(Soldado[] ej){
        double sumLife = 0;
        for(int i = 0; i < ej.length; i++){
            sumLife += ej[i].getVida();
        }
        return sumLife/ej.length;
    }

    //Muestra el orden de creacion de los soldados, esto porque al asignar soldados al array
    //lo hicimos de manera ordenada, ademas puede reutilizarse en otros metodos.
    public static void mostrar(Soldado[] ej, int a){
        System.out.println("Ejercito " + a);
        for(Soldado sold : ej){
            System.out.println(sold);
        }
    }
    
    public static void aleatorio(Soldado[] ej){
        Random rand = new Random();
        Soldado aux;
        int r1, r2;
        for(int i = 0; i < ej.length; i++){
            r1 = rand.nextInt(ej.length);
            r2 = rand.nextInt(ej.length);
            aux = ej[r1];
            ej[r1] = ej[r2];
            ej[r2] = aux;
        }
    }
    
    public static void rankingMayorMenorBurbuja(Soldado[] ej){
        for (int i = 0; i < ej.length - 1; i++){
            for(int j = 0; j < ej.length - i - 1; j++){
                if(ej[j].getVida() < ej[j + 1].getVida()){
                    Soldado aux = ej[j];
                    ej[j] = ej[j + 1];
                    ej[j + 1] = aux;
                }   
            }
        }
    }
    
    public static void rankingMayorMenorSeleccion(Soldado[] ej){
        for (int i = 0; i < ej.length - 1; i++){
            int indexMayor = i;
            for (int j = i + 1; j < ej.length; j++){
                if(ej[j].getVida() > ej[indexMayor].getVida())
                indexMayor = j;
            }
            Soldado aux = ej[i];
            ej[i] = ej[indexMayor];
            ej[indexMayor] = aux;
        }
    }
    
    public static void determinarGanador(Soldado[] ej1, Soldado[] ej2){
        double sumLifeEj1 = 0, sumLifeEj2 = 0;
        for(int i = 0; i < ej1.length; i++){
            sumLifeEj1 += ej1[i].getVida();
        }
        for(int i = 0; i < ej2.length; i++){
            sumLifeEj2 += ej2[i].getVida();
        }
        if (sumLifeEj1 == sumLifeEj2) {
            System.out.println("EMPATE\nEjercito1 = " + sumLifeEj1 + 
            " ---- Ejercito2 = " + sumLifeEj2);
        }
        else if (sumLifeEj1 > sumLifeEj2) {
            System.out.println("GANA EJERCITO1\nEjercito1 = " + sumLifeEj1 + 
            "   >  Ejercito2 = " + sumLifeEj2);
        }
        else{
            System.out.println("GANA EJERCITO2\nEjercito1 = " + sumLifeEj1 + 
            "   <  Ejercito2 = " + sumLifeEj2);
        }   
    }
}
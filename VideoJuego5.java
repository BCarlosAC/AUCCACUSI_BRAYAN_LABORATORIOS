package L8;
import java.util.*;
public class VideoJuego5 {
    public static void main(String [] args){
        char decicion = 'y';
        while(decicion == 'y'){
            Scanner sc = new Scanner(System.in);
            Random rand = new Random();
            Soldado[][] campo = new Soldado[10][10];
            HashMap<Integer, Soldado> e1 = new HashMap<>();
            HashMap<Integer, Soldado> e2 = new HashMap<>();
            
            crearSoldados(e1, '@');
            crearSoldados(e2, '?');
            asignarSoldados(campo, e1);
            asignarSoldados(campo, e2);
            mostrarTabla(campo);

            System.out.println("Mayor vida ejercito 1: \n\t" + e1.get(mayorVida(e1)));
            System.out.println("Mayor vida ejercito 2: \n\t" + e2.get(mayorVida(e2)));

            System.out.println("\nPromedio vida ejercito 1: " + promedioVida(e1));
            System.out.println("Promedio vida ejercito 2: " + promedioVida(e2));

            System.out.println("\nDatos en orden que fueron creados");
            mostrarEnOrdenCreacion(e1);
            mostrarEnOrdenCreacion(e2);

            System.out.println("\nRanking de poder mayor a menor vida");
            System.out.println("BURBUJA");
            rankingMayorMenorBurbuja(e1);
            mostrar(ej1, 1);
            rankingMayorMenorBurbuja(e2);
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
    
    public static void crearSoldados(HashMap<Integer, Soldado> ejercito, char a){
        Random random = new Random();
        for(int i = 0; i < random.nextInt(10); i++){
            Soldado aux = new Soldado();
            aux.setVida(random.nextInt(5) + 1);
            aux.setNombre(i + "." + a + aux.getVida());
            ejercito.put(i, aux);
        }
    }
    public static void asignarSoldados(Soldado[][] campo, HashMap<Integer, Soldado> ejercito){
        Random rand = new Random();
        int fila, columna;
        for (int i = 0; i < ejercito.size(); i++){
            boolean aux = true;
            while(aux){
                fila = rand.nextInt(10);
                columna = rand.nextInt(10);
                if(campo[fila][columna] == null){
                    campo[fila][columna] = ejercito.get(i);
                    ejercito.get(i).setFila(fila);
                    ejercito.get(i).setColumna(columna);
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

    public static int mayorVida(HashMap<Integer, Soldado> ejercito){
        int indexMayor = 0;
        for(int i = 0; i < ejercito.size(); i++){
            if(ejercito.get(i).getVida() > ejercito.get(indexMayor).getVida()){
                indexMayor = i;
            }
        }
        return indexMayor;
    }

    public static double promedioVida(HashMap<Integer, Soldado> ejercito){
        double sumaVida = 0;
        for(int i = 0; i < ejercito.size(); i++){
            sumaVida += ejercito.get(i).getVida();
        }
        return (double)sumaVida/ejercito.size();
    }

    //Muestra el orden de creacion de los soldados, esto porque al asignar soldados al array
    //lo hicimos de manera ordenada, ademas puede reutilizarse en otros metodos.
    public static void mostrarEnOrdenCreacion(HashMap<Integer, Soldado> ejercito){
        for(int key : ejercito.keySet()){
            System.out.println(ejercito.get(key));
        }
    }

    public static void mostrar(HashMap<Integer, Soldado> ejercito){
        for(int key : ejercito.keySet()){
            System.out.println(ejercito.get(key));
        }
    }
    
    public static void aleatorio(HashMap<Integer, Soldado> ejercito){
        Random rand = new Random();
        Soldado aux;
        int r1, r2;
        for(int i = 0; i < ejercito.size(); i++){
            r1 = rand.nextInt(ejercito.size());
            r2 = rand.nextInt(ejercito.size());
            aux = ej[r1];
            ej[r1] = ej[r2];
            ej[r2] = aux;
        }
    }
    
    public static ArrayList<Soldado> rankingMayorMenorBurbuja(HashMap<Integer, Soldado> ejercito){
        ArrayList<Soldado> auxEjercito = new ArrayList<>();
        //añadimos los soldados del HashMap a un ArrayList
        for(int key : ejercito.keySet()){
            auxEjercito.add(ejercito.get(key));
        }
        for (int i = 0; i < ejercito.size() - 1; i++){
            for(int j = 0; j < ejercito.size() - i - 1; j++){
                //Comparar elementos del arraylist e intercambiar posicion si se cumple la condicion
                if( auxEjercito.get(i).getVida() < auxEjercito.get(j + 1).getVida()){
                    Soldado aux = auxEjercito.get(j);
                    auxEjercito.set(j, auxEjercito.get(j + 1));
                    auxEjercito.set(j + 1, aux);
                }   
            }
        }
        return auxEjercito;
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
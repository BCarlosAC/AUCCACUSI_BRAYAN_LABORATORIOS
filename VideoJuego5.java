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
            rankingMayorMenorSeleccion(e2);

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

    
    public static void mostrarEnOrdenCreacion(HashMap<Integer, Soldado> ejercito){
        for(int i = 0; i < ejercito.size(); i++){
            for(int j = 0; j < ejercito.size(); j++){
            //comparamos el primer caracter de cada soldado, este nos dice su orden de creacion.
                if(ejercito.get(j).getNombre().charAt(0) == i){
                    System.out.println(ejercito.get(j));
                }
            }
        }
    }

    /*mostrarSoldadosEjercito mustra lo valores del HashMap sin inportar si se hicieron 
    cambios en los "valores" del Hashmap */
    public static void mostrarSoldadosEjercito(HashMap<Integer, Soldado> ejercito){
        for(int key : ejercito.keySet()){
            System.out.println(ejercito.get(key));
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
    

    public static void rankingMayorMenorBurbuja(HashMap<Integer, Soldado> ejercito) {
        // Bucle para ordenar y mostrar el ranking
        for (int i = 0; i < ejercito.size(); i++) {
            for (int j = i + 1; j < ejercito.size(); j++) {
                if (ejercito.get(j).getVida() > ejercito.get(i).getVida()) {
                    // Intercambiar posiciones para burbuja
                    Soldado temp = ejercito.get(i);
                    ejercito.put(i, ejercito.get(j));
                    ejercito.put(j, temp);
                }
            }
            // Imprimir el soldado actual después de ordenar
            System.out.println(ejercito.get(i));
        }
    }
    
    
    public static void rankingMayorMenorSeleccion(HashMap<Integer, Soldado> ejercito) {
        for (int i = 0; i < ejercito.size() - 1; i++) {
            int indexMayor = i;
            for (int j = i + 1; j < ejercito.size(); j++) {
                if (ejercito.get(j).getVida() > ejercito.get(indexMayor).getVida()) {
                    indexMayor = j;
                }
            }
            // Intercambiar los soldados
            Soldado temp = ejercito.get(i);
            ejercito.put(i, ejercito.get(indexMayor));
            ejercito.put(indexMayor, temp);
        }
    
        // Imprimir los soldados después de ordenar
        for (int i = 0; i < ejercito.size(); i++) {
            System.out.println(ejercito.get(i));
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
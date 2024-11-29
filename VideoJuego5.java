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

            System.out.println("\nPromedio vida ejercito 1: " + (double)sumaVida(e1)/e1.size());
            System.out.println("Promedio vida ejercito 2: " + (double)sumaVida(e2)/e2.size());

            System.out.println("\nDatos en orden que fueron creados");
            System.out.println("Ejercito 1: ");
            mostrarEnOrdenCreacion(e1);
            System.out.println("Ejercito 2: ");
            mostrarEnOrdenCreacion(e2);

            System.out.println("\nRanking de poder mayor a menor vida");
            System.out.println("BURBUJA");
            rankingMayorMenorBurbuja(e1,"Ejercito1");
            rankingMayorMenorSeleccion(e2,"Ejercito2");

            System.out.println("\nAleatorio");
            aleatorio(e1);
            aleatorio(e2);
            mostrarSoldadosEjercito(e1, "Ejercito 1: ");
            mostrarSoldadosEjercito(e2, "Ejercito 2: ");
    
            System.out.println("\nSELECCION");
            rankingMayorMenorSeleccion(e1, "Ejercito1");
            rankingMayorMenorSeleccion(e2, "Ejercito2");
    
            System.out.println();
            determinarGanador(e1, e2);

            System.out.print("Desea iniciar un nuevo juego? (y/n): ");
            decicion = sc.nextLine().charAt(0);
        }
        
    }
    
    public static void crearSoldados(HashMap<Integer, Soldado> ejercito, char a){
        Random random = new Random();
        for(int i = 0; i < random.nextInt(10) + 1; i++){
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
                if(ejercito.get(j).getNombre().charAt(0) == i + '0'){
                    System.out.println(ejercito.get(j));
                }
            }
        }
    }

    /*mostrarSoldadosEjercito mustra lo valores del HashMap sin inportar si se hicieron 
    cambios en los "valores" del Hashmap */
    public static void mostrarSoldadosEjercito(HashMap<Integer, Soldado> ejercito, String ejercitoNombre){
        System.out.println(ejercitoNombre);
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

    

    
    public static void aleatorio(HashMap<Integer, Soldado> ejercito){
        Random rand = new Random();
        Soldado aux;
        int r1, r2;
        for(int i = 0; i < ejercito.size(); i++){
            r1 = rand.nextInt(ejercito.size());
            r2 = rand.nextInt(ejercito.size());
            aux = ejercito.get(r1);
            ejercito.put(r1, ejercito.get(r2));
            ejercito.put(r2, aux);
        }
    }
    

    public static void rankingMayorMenorBurbuja(HashMap<Integer, Soldado> ejercito, String ejercitoNombre) {
        System.out.println(ejercitoNombre);
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
    
    
    public static void rankingMayorMenorSeleccion(HashMap<Integer, Soldado> ejercito, String ejercitoNombre){
        System.out.println(ejercitoNombre);
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
    
    public static int sumaVida(HashMap<Integer, Soldado> ejercito){
        int sumaVida = 0;
        for(int i = 0; i < ejercito.size(); i++){
            sumaVida += ejercito.get(i).getVida();
        }
        return sumaVida;
    }
    
    public static void determinarGanador(HashMap<Integer, Soldado> ejercito1, HashMap<Integer, Soldado> ejercito2){
        int vidaTotalEjer1, vidaTotalEjer2;
        vidaTotalEjer1 = sumaVida(ejercito1);
        vidaTotalEjer2 = sumaVida(ejercito2);

        if (vidaTotalEjer1 == vidaTotalEjer2) {
            System.out.println("EMPATE\nEjercito1 = " + vidaTotalEjer1 + 
            " ---- Ejercito2 = " + vidaTotalEjer2);
        }
        else if (vidaTotalEjer1 > vidaTotalEjer2) {
            System.out.println("GANA EJERCITO 1\nEjercito1 = " + vidaTotalEjer1 + 
            "   >  Ejercito2 = " + vidaTotalEjer2);
        }
        else{
            System.out.println("GANA EJERCITO 2\nEjercito1 = " + vidaTotalEjer1 + 
            "   <  Ejercito2 = " + vidaTotalEjer2);
        }   
    }
}
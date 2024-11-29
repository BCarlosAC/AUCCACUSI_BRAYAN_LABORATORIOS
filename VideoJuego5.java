package L8;
import java.util.*;
public class VideoJuego5 {
    public static void main(String [] args){
        char decision = 'y';
        while(decision == 'y'){
            Scanner sc = new Scanner(System.in);
            Soldado[][] campo = new Soldado[10][10];
            HashMap<Integer, Soldado> e1 = new HashMap<>();
            HashMap<Integer, Soldado> e2 = new HashMap<>();
            
            crearSoldados(e1, '@');
            crearSoldados(e2, '?');
            asignarSoldados(campo, e1);
            asignarSoldados(campo, e2);

            System.out.println("Leyenda nombre\n-----------------------------\n" +
            "1erNumero = Orden de creación\n "+
            "@ = ejército1\n? = ejército2\nÚltimo Número = Nivel de vida\n");
            mostrarTabla(campo);

            System.out.println("Mayor vida ejército 1: \n\t" + e1.get(mayorVida(e1)));
            System.out.println("Mayor vida ejército 2: \n\t" + e2.get(mayorVida(e2)));

            System.out.println("\nPromedio vida ejército 1: " + (double)sumaVida(e1)/e1.size());
            System.out.println("Promedio vida ejército 2: " + (double)sumaVida(e2)/e2.size());

            System.out.println("\nDatos en orden que fueron creados");
            System.out.println("Ejército 1: ");
            mostrarEnOrdenCreacion(e1);
            System.out.println("Ejército 2: ");
            mostrarEnOrdenCreacion(e2);

            System.out.println("\nRanking de poder mayor a menor vida");
            System.out.println("BURBUJA");
            rankingMayorMenorBurbuja(e1);
            mostrarSoldadosEjercito(e1, "Ejército 1: ");
            rankingMayorMenorSeleccion(e2);
            mostrarSoldadosEjercito(e2, "Ejército 2: ");

            System.out.println("\nAleatorio");
            aleatorio(e1);
            aleatorio(e2);
            mostrarSoldadosEjercito(e1, "Ejército 1: ");
            mostrarSoldadosEjercito(e2, "Ejército 2: ");
    
            System.out.println("\nSELECCIÓN");
            rankingMayorMenorSeleccion(e1);
            mostrarSoldadosEjercito(e1, "Ejército 1: ");
            rankingMayorMenorSeleccion(e2);
            mostrarSoldadosEjercito(e2, "Ejército 2: ");
    
            System.out.println();
            determinarGanador(e1, e2);

            System.out.print("¿Desea iniciar un nuevo juego? (y/n): ");
            decision = sc.nextLine().charAt(0);
        }
        
    }
    
    public static void crearSoldados(HashMap<Integer, Soldado> ejercito, char a){
        Random random = new Random();
        /*Asignamos soldados al HashMap
        * la cantidad de soldados es determinada aleatoriamente
        * Damos nombre a cada soldado creado, considerando el ejército al que pertenece (@ o ?)
        */
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
            //Comparamos el primer carácter de cada soldado, este nos dice su orden de creación.
                if(ejercito.get(j).getNombre().charAt(0) == i + '0'){
                    System.out.println(ejercito.get(j));
                }
            }
        }
    }

    /*mostrarSoldadosEjercito muestra los valores del HashMap sin importar si se hicieron 
    cambios en los "valores" del HashMap */
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
                /*Si campo está vacío le asignamos un elemento del HashMap (correspondiente a un ejército determinado) */
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
        /*Generamos 2 números aleatorios que actuarán como índices, luego intercambiamos los 
         * "valores" de cada entrada del HashMap con estos índices.
         */
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
    
    public static void rankingMayorMenorBurbuja(HashMap<Integer, Soldado> ejercito) {
        for (int i = 0; i < ejercito.size(); i++) {
            /* Cogemos el primer elemento y lo comparamos con los demás, para las siguientes pasadas
            ya no consideramos el primero puesto que ya es el mayor */
            for (int j = i + 1; j < ejercito.size(); j++) {
                if (ejercito.get(j).getVida() > ejercito.get(i).getVida()) {
                    // Intercambiar posiciones para burbuja
                    Soldado temp = ejercito.get(i);
                    ejercito.put(i, ejercito.get(j));
                    ejercito.put(j, temp);
                }
            }
        }
    }
    
    public static void rankingMayorMenorSeleccion(HashMap<Integer, Soldado> ejercito){
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
    }
    
    public static int sumaVida(HashMap<Integer, Soldado> ejercito){
        int sumaVida = 0;
        for(int i = 0; i < ejercito.size(); i++){
            sumaVida += ejercito.get(i).getVida();
        }
        return sumaVida;
    }
    
    public static void determinarGanador(HashMap<Integer, Soldado> ejercito1, HashMap<Integer, Soldado> ejercito2){
        /*El ganador se determina de acuerdo a la suma total de las vidas de los soldados de cada ejército */
        int vidaTotalEjer1, vidaTotalEjer2;
        vidaTotalEjer1 = sumaVida(ejercito1);
        vidaTotalEjer2 = sumaVida(ejercito2);

        if (vidaTotalEjer1 == vidaTotalEjer2) {
            System.out.println("EMPATE\nEjército1 = " + vidaTotalEjer1 + 
            " ---- Ejército2 = " + vidaTotalEjer2);
        }
        else if (vidaTotalEjer1 > vidaTotalEjer2) {
            System.out.println("GANA EJÉRCITO 1\nEjército1 = " + vidaTotalEjer1 + 
            "   >  Ejército2 = " + vidaTotalEjer2);
        }
        else{
            System.out.println("GANA EJÉRCITO 2\nEjército1 = " + vidaTotalEjer1 + 
            "   <  Ejército2 = " + vidaTotalEjer2);
        }   
    }
}

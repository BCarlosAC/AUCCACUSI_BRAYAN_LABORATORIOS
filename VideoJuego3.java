package L6;
import java.util.*;
public class VideoJuego3 {
    public static void main(String [] args){
        Random rand = new Random();
        ArrayList<ArrayList<Soldado>> campoo = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            campoo.add(new ArrayList<Soldado>());
            for(int j = 0; j < 10 ; j++){
                campoo.get(i).add(null);
            }
        }
        Soldado[] ordenCreacion = new Soldado[20];
        int i = 1;
        int indice = 0;
        while(i <= 20) {
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            while(campoo.get(fila).get(columna) != null){
                fila = rand.nextInt(10);
                columna = rand.nextInt(10);
            }
            Soldado soldado = new Soldado();
            if(i <= 10){
                if(i == 10){
                    soldado.setNombre("Soldado10X1");
                } else {
                    soldado.setNombre("Soldado0" + i + "X1");
                }
            }

            if(i > 10){
                if(i == 20){
                    soldado.setNombre("Soldado20X2");
                } else {
                    soldado.setNombre("Soldado0" + i + "X2");
                }
            }
            
            soldado.setVida(rand.nextInt(5) + 1);
            soldado.setFila(fila);
            soldado.setColumna((char)('A' + columna));
            soldado.setEstado(true);
            campoo.get(fila).set(columna, soldado);
            ordenCreacion[indice++] = soldado;
            i++;
        }

        mostrarTabla(campoo);
        mostrarMayorVida(campoo);
        promedioVida(campoo);
        nivelDeVidaEjercito(campoo);
        System.out.println("\nDatos de los soldados en orden de creación:");
        mostrar(campoo);
        aleatorio(campoo);
        System.out.println("\nAleatorio");
        mostrar(campoo);
        System.out.println("\nOrdenamiento BURBUJA");
        ordenarPorVidaBurbuja(campoo);
        mostrar(campoo);
        aleatorio(campoo);
        System.out.println("\nAleatorio");

        mostrar(campoo);
        System.out.println("\nOrdenamiento SELECCION");
        ordenarPorVidaSeleccion(campoo);
        mostrar(campoo);
        System.out.println("\nRanking de mayor a menor: ");
        rankingMayorMenor(campoo);
        mostrar(campoo);

    }


    public static void mostrarTabla(ArrayList<ArrayList<Soldado>> campo){
        for(char i = 'A'; i < 'K'; i++){
            System.out.print("       " + i + "  ");
        }
        
        System.out.println();
        for(int i = 0; i < campo.length; i++)
        {
            if(i == 9){
                System.out.print(i + 1);
            }
            else{
                System.out.print((i + 1) + " ");
            }
            for(int j = 0; j < campo[i].length; j++)
            {
                if (campo[i][j] != null && campo[i][j].getEstado())
                    System.out.print("|" + campo[i][j].getNombre());
                else
                    System.out.print("|         ");
            }
            System.out.println("|");
            System.out.println("   ---------------------------------------------"+
            "-------------------------------------------------------");
        }
        
    }


    public static void mostrarMayorVida(Soldado[][] campoo){
        Soldado mayor = null;
        int filMayor = 0;
        int colMayor = 0;
        for (int i = 0; i < campoo.length; i++){
            for(int j = 0; j < campoo[i].length; j++){
                // Verifica que la posición actual tenga un soldado y que esté activo
                if (campoo[i][j] != null && campoo[i][j].getEstado()){
                    // Si no hay soldado registrado o el actual tiene más vida que el registrado
                    // Actualiza el soldado con mayor vida
                    if(mayor == null || campoo[i][j].getVida() > mayor.getVida()){
                        mayor = campoo[i][j];
                        filMayor = i;
                        colMayor = j;
                    }
                } 
                
            }
        }
        System.out.println("El soldado con mayor nivel de vida es: ");
        System.out.println(campoo[filMayor][colMayor].toString());
    }


    public static void promedioVida(Soldado[][] campoo){
        int suma = 0;
        // Recorre todo el campoo de soldados
        for(int i = 0; i < campoo.length; i++){
            for(int j = 0; j < campoo[i].length; j++){
                // Si la posición actual contiene un soldado, suma su vida al total
                if(campoo[i][j] != null){
                    // Acumula el valor de la vida del soldado
                    suma += campoo[i][j].getVida();
                }
            }
        }
        System.out.println("\nEl promedio de vida de los 10 soldados es: " + (suma / campoo.length));
    }

    public static void nivelDeVidaEjercito(Soldado[][] campoo){
        int suma = 0;
        for (int i = 0; i < campoo.length; i++){
            for (int j = 0; j < campoo[i].length; j++){
                if (campoo[i][j] != null){
                    suma += campoo[i][j].getVida();
                }
            }
        }
        System.out.println("\nEl nivel de vida del ejército es: " + suma);
    }
    

    public static void mostrar(Soldado[][] campoo){
        System.out.println();
        // Recorre todo el campoo de soldados
        for (int i = 0; i < campoo.length; i++){
            // Si hay un soldado en la posición actual, imprime su información
            for (int j = 0; j < campoo[i].length; j++){
                if (campoo[i][j] != null){
                    // Muestra los datos del soldado
                    System.out.println(campoo[i][j].toString());
                }
            }
        }
    }
    


    public static void ordenarPorVidaBurbuja(Soldado[][] campoo) {
        Soldado temp;
        // Recorremos todo el array bidimensional
        for (int i = 0; i < campoo.length; i++) {
            for (int j = 0; j < campoo[i].length; j++) {
                // Comparar el soldado actual con el resto de los soldados
                for (int k = 0; k < campoo.length; k++) {
                    for (int l = 0; l < campoo[k].length; l++) {
                        // Comparar vida de soldados e intercambiar si están desordenados
                        if (campoo[i][j] != null && campoo[k][l] != null) {
                            if (campoo[i][j].getVida() < campoo[k][l].getVida()) {
                                temp = campoo[i][j];
                                campoo[i][j] = campoo[k][l];
                                campoo[k][l] = temp;
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    public static void ordenarPorVidaSeleccion(Soldado[][] campoo){
        Soldado temp;
        // Recorre todas las posiciones del campoo
        for (int i = 0; i < campoo.length; i++){
            for (int j = 0; j < campoo[i].length; j++){
                int filMenor = i;
                int colMenor = j;
                // Busca el soldado con la menor vida a partir de la posición actual
                for (int k = i; k < campoo.length; k++){
                    for (int l = 0; l < campoo[k].length; l++){
                        // Evita revisar posiciones ya evaluadas
                        if (k == i && l <= j) {
                            continue;
                        }
                        // Cambia las coordenadas si se encuentra un soldado con vida más baja
                        if (campoo[k][l] != null && campoo[filMenor][colMenor] != null){
                            if (campoo[k][l].getVida() < campoo[filMenor][colMenor].getVida()){
                                filMenor = k;
                                colMenor = l;
                            }
                        }
                    }
                }
                // Intercambia los soldados si se encontró uno con menor vida
                if (filMenor != i || colMenor != j){
                    temp = campoo[i][j];
                    campoo[i][j] = campoo[filMenor][colMenor];
                    campoo[filMenor][colMenor] = temp;
                }
            }
        }
    }
    
    
    public static void aleatorio(Soldado[][] campoo){
        Random rand = new Random();
        Soldado temp;
        // Recorre todo el campoo de soldados
        for (int i = 0; i < campoo.length; i++){
            for (int j = 0; j < campoo[i].length; j++){
                // Genera una fila y columna aleatoria dentro de la matriz
                int randFila = rand.nextInt(campoo.length);
                int randColumna = rand.nextInt(campoo[0].length);
                 // Intercambia el soldado en (i, j) con uno en una posición aleatoria (randFila, randColumna)
                temp = campoo[i][j];
                campoo[i][j] = campoo[randFila][randColumna];
                campoo[randFila][randColumna] = temp;
            }
        }
    }
    
    public static void rankingMayorMenor(Soldado[][] campoo){
        Soldado temp;
        // Recorre todo el campoo de soldados
        for (int i = 0; i < campoo.length; i++){
            for (int j = 0; j < campoo[i].length; j++){
                // Compara cada soldado con todos los demás en el campoo
                for (int k = 0; k < campoo.length; k++){
                    for (int l = 0; l < campoo[k].length; l++){
                        // Verifica que ambos soldados no sean nulos
                        if (campoo[i][j] != null && campoo[k][l] != null){
                            // Si el soldado en (i, j) tiene más vida que el soldado en 
                            //(k, l), los intercambia
                            if (campoo[i][j].getVida() > campoo[k][l].getVida()){
                                temp = campoo[i][j];
                                campoo[i][j] = campoo[k][l];
                                campoo[k][l] = temp;
                            }
                        }
                    }
                }
            }
        }
    }
}
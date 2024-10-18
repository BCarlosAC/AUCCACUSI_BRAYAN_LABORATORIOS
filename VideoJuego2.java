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
        nivelDeVidaEjercito(campo);
        System.out.println("\nDatos de los soldados en orden de creación:");
        mostrar(campo);
        aleatorio(campo);
        System.out.println("\nAleatorio");
        mostrar(campo);
        System.out.println("\nOrdenamiento BURBUJA");
        ordenarPorVidaBurbuja(campo);
        mostrar(campo);
        aleatorio(campo);
        System.out.println("\nAleatorio");

        mostrar(campo);
        System.out.println("\nOrdenamiento SELECCION");
        ordenarPorVidaSeleccion(campo);
        mostrar(campo);
        System.out.println("\nRanking de mayor a menor: ");
        rankingMayorMenor(campo);
        mostrar(campo);

    }


    public static void mostrarTabla(Soldado[][] camp){
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
        for (int i = 0; i < campo.length; i++){
            for(int j = 0; j < campo[i].length; j++){
                // Verifica que la posición actual tenga un soldado y que esté activo
                if (campo[i][j] != null && campo[i][j].getEstado()){
                    // Si no hay soldado registrado o el actual tiene más vida que el registrado
                    // Actualiza el soldado con mayor vida
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
        // Recorre todo el campo de soldados
        for(int i = 0; i < campo.length; i++){
            for(int j = 0; j < campo[i].length; j++){
                // Si la posición actual contiene un soldado, suma su vida al total
                if(campo[i][j] != null){
                    // Acumula el valor de la vida del soldado
                    suma += campo[i][j].getVida();
                }
            }
        }
        System.out.println("\nEl promedio de vida de los 10 soldados es: " + (suma / campo.length));
    }

    public static void nivelDeVidaEjercito(Soldado[][] campo){
        int suma = 0;
        for (int i = 0; i < campo.length; i++){
            for (int j = 0; j < campo[i].length; j++){
                if (campo[i][j] != null){
                    suma += campo[i][j].getVida();
                }
            }
        }
        System.out.println("\nEl nivel de vida del ejército es: " + suma);
    }
    

    public static void mostrar(Soldado[][] campo){
        System.out.println();
        // Recorre todo el campo de soldados
        for (int i = 0; i < campo.length; i++){
            // Si hay un soldado en la posición actual, imprime su información
            for (int j = 0; j < campo[i].length; j++){
                if (campo[i][j] != null){
                    // Muestra los datos del soldado
                    System.out.println(campo[i][j].toString());
                }
            }
        }
    }
    


    public static void ordenarPorVidaBurbuja(Soldado[][] campo) {
        Soldado temp;
        // Recorremos todo el array bidimensional
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                // Comparar el soldado actual con el resto de los soldados
                for (int k = 0; k < campo.length; k++) {
                    for (int l = 0; l < campo[k].length; l++) {
                        // Comparar vida de soldados e intercambiar si están desordenados
                        if (campo[i][j] != null && campo[k][l] != null) {
                            if (campo[i][j].getVida() < campo[k][l].getVida()) {
                                temp = campo[i][j];
                                campo[i][j] = campo[k][l];
                                campo[k][l] = temp;
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    public static void ordenarPorVidaSeleccion(Soldado[][] campo){
        Soldado temp;
        // Recorre todas las posiciones del campo
        for (int i = 0; i < campo.length; i++){
            for (int j = 0; j < campo[i].length; j++){
                int filMenor = i;
                int colMenor = j;
                // Busca el soldado con la menor vida a partir de la posición actual
                for (int k = i; k < campo.length; k++){
                    for (int l = 0; l < campo[k].length; l++){
                        // Evita revisar posiciones ya evaluadas
                        if (k == i && l <= j) {
                            continue;
                        }
                        // Cambia las coordenadas si se encuentra un soldado con vida más baja
                        if (campo[k][l] != null && campo[filMenor][colMenor] != null){
                            if (campo[k][l].getVida() < campo[filMenor][colMenor].getVida()){
                                filMenor = k;
                                colMenor = l;
                            }
                        }
                    }
                }
                // Intercambia los soldados si se encontró uno con menor vida
                if (filMenor != i || colMenor != j){
                    temp = campo[i][j];
                    campo[i][j] = campo[filMenor][colMenor];
                    campo[filMenor][colMenor] = temp;
                }
            }
        }
    }
    
    
    public static void aleatorio(Soldado[][] campo){
        Random rand = new Random();
        Soldado temp;
        // Recorre todo el campo de soldados
        for (int i = 0; i < campo.length; i++){
            for (int j = 0; j < campo[i].length; j++){
                // Genera una fila y columna aleatoria dentro de la matriz
                int randFila = rand.nextInt(campo.length);
                int randColumna = rand.nextInt(campo[0].length);
                 // Intercambia el soldado en (i, j) con uno en una posición aleatoria (randFila, randColumna)
                temp = campo[i][j];
                campo[i][j] = campo[randFila][randColumna];
                campo[randFila][randColumna] = temp;
            }
        }
    }
    
    public static void rankingMayorMenor(Soldado[][] campo){
        Soldado temp;
        // Recorre todo el campo de soldados
        for (int i = 0; i < campo.length; i++){
            for (int j = 0; j < campo[i].length; j++){
                // Compara cada soldado con todos los demás en el campo
                for (int k = 0; k < campo.length; k++){
                    for (int l = 0; l < campo[k].length; l++){
                        // Verifica que ambos soldados no sean nulos
                        if (campo[i][j] != null && campo[k][l] != null){
                            // Si el soldado en (i, j) tiene más vida que el soldado en 
                            //(k, l), los intercambia
                            if (campo[i][j].getVida() > campo[k][l].getVida()){
                                temp = campo[i][j];
                                campo[i][j] = campo[k][l];
                                campo[k][l] = temp;
                            }
                        }
                    }
                }
            }
        }
    }
}
package L7;
import java.util.*;
public class VideoJuego4 {
    public static void main(String [] args){
        Random rand = new Random();
        ArrayList<ArrayList<Soldado>> campo = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            campo.add(new ArrayList<Soldado>());
            for(int j = 0; j < 10 ; j++){
                campo.get(i).add(null);
            }
        }
        int i = 1;
        while(i <= 20) {
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            while(campo.get(fila).get(columna) != null){
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
                    soldado.setNombre("Soldado10X2");
                } else {
                    soldado.setNombre("Soldado0" + (i - 10 )+ "X2");
                }
            }
            
            soldado.setVida(rand.nextInt(5) + 1);
            soldado.setFila(fila);
            soldado.setColumna((char)('A' + columna));
            soldado.setEstado(true);
            campo.get(fila).set(columna, soldado);
            i++;
        }

        mostrarTabla(campo);
        mostrarMayorVida(campo);
        promedioVida(campo);
        System.out.println("\nDatos de los soldados en orden de creación:");
        mostrar(campo);
        aleatorio(campo);
        System.out.println("\nAleatorio");
        mostrar(campo);
        System.out.println("\nRanking de mayor a menor BURBUJA");
        rankingMayorMenorBurbuja(campo);
        mostrar(campo);
        aleatorio(campo);
        System.out.println("\nAleatorio");
        mostrar(campo);
        System.out.println("\nRanking de mayor a menor SELECCION ");
        rankingMayorMenorSeleccion(campo);
        mostrar(campo);
        determinarGanador(campo);
    }


    public static void mostrarTabla(ArrayList<ArrayList<Soldado>> campo){
        for(char i = 'A'; i < 'K'; i++){
            System.out.print("          S " + i + "  ");
        }
        
        System.out.println();
        for(int i = 0; i < 10; i++){
            if(i == 9){
                System.out.print(i + 1);
            }
            else
            System.out.print((i + 1) + " ");

            for(int j = 0; j < 10; j++){
                if (campo.get(i).get(j) != null && campo.get(i).get(j).getEstado())
                    System.out.print("|" + campo.get(i).get(j).getNombre());
                else
                    System.out.print("|           ");
            }
            System.out.println("|");
            System.out.println("   ---------------------------------------------"+
            "---------------------------------------------------------------------------");
        }
    }


    public static void mostrarMayorVida(ArrayList<ArrayList<Soldado>> campo){
        Soldado mayor1 = new Soldado();
        Soldado mayor2 =  new Soldado();

        mayor1.setVida(1);
        mayor2.setVida(1);
        int filMayorX1 = 0, colMayorX1 = 0, filMayorX2 = 0, colMayorX2 = 0;
        // Recorremos todo el campo de batalla (array bidimensional)
        for (int i = 0; i < campo.size(); i++){
            for(int j = 0; j < campo.get(i).size();  j++){
                // Verificamos que haya un soldado en la posición actual
                if (campo.get(i).get(j) != null){
                    // Si el soldado pertenece al ejército 1 y tiene más vida que el actual mayor1
                    if(campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '1' &&
                    campo.get(i).get(j).getVida() > mayor1.getVida()){
                        // Actualizamos el soldado mayor1 y su posición
                        mayor1 = campo.get(i).get(j);
                        filMayorX1 = i;
                        colMayorX1 = j;
                    }
                    // Si el soldado pertenece al ejército 2 y tiene más vida que el actual mayor2
                    if(campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '2' &&
                    campo.get(i).get(j).getVida() > mayor2.getVida()){
                        // Actualizamos el soldado mayor2 y su posición
                        mayor2 = campo.get(i).get(j);
                        filMayorX2 = i;
                        colMayorX2   = j;
                    }
                } 
                
            }
        }
        System.out.println("El soldado con mayor nivel de vida del ejercito X1 es: ");
        System.out.println(campo.get(filMayorX1).get(colMayorX1).toString());
        System.out.println("\nEl soldado con mayor nivel de vida del ejercito X1 es: ");
        System.out.println(campo.get(filMayorX2).get(colMayorX2).toString());
    }


    public static void promedioVida(ArrayList<ArrayList<Soldado>> campo){
        double sumEj1 = 0, sumEj2 = 0;
        // Recorre todo el campo de soldados
        for(int i = 0; i < campo.size(); i++){
            for(int j = 0; j < campo.get(i).size(); j++){
                // Si la posición actual contiene un soldado, suma su vida al total
                if(campo.get(i).get(j) != null){
                    if(campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '1'){
                        // Acumula el valor de la vida del soldado
                        sumEj1 +=campo.get(i).get(j).getVida();
                    }
                    if(campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '2'){
                        // Acumula el valor de la vida del soldado
                        sumEj2 +=campo.get(i).get(j).getVida();
                    }
                }
                
            }
        }
        System.out.println("\nEl promedio de vida de los soldados del ejercito 1 es: " + (sumEj1 / 10));
        System.out.println("El promedio de vida de los soldados del ejercito 2 es: " + (sumEj2 / 10));
    }


    public static void mostrar(ArrayList<ArrayList<Soldado>> campo){
        System.out.println();
        // Recorre todo el campo de soldados
        System.out.println("Ejercito 1:");
        for (int i = 0; i < campo.size(); i++){
            for (int j = 0; j < campo.get(i).size(); j++){
            // Si hay un soldado en la posición actual, imprime su información
                if(campo.get(i).get(j) != null){
                    if (campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '1'){
                        System.out.println(campo.get(i).get(j).toString());
                    }
                }
            }
        }
        System.out.println("Ejercito 2:");
        for (int i = 0; i < campo.size(); i++){
            for (int j = 0; j < campo.get(i).size(); j++){
            // Si hay un soldado en la posición actual, imprime su información
                if(campo.get(i).get(j) != null){
                    if (campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '2'){
                        System.out.println(campo.get(i).get(j).toString());
                    }
                }
            }
        }
    }
    
    
    public static void aleatorio(ArrayList<ArrayList<Soldado>> campo){
        Random rand = new Random();
        Soldado temp;
        // Recorre todo el campoo de soldados
        for (int i = 0; i < campo.size(); i++){
            for (int j = 0; j < campo.get(i).size(); j++){
                // Genera una fila y columna aleatoria dentro de la matriz
                int randFila = rand.nextInt(campo.size());
                int randColumna = rand.nextInt(campo.get(0).size());
                 // Intercambia el soldado en (i, j) con uno en una posición aleatoria (randFila, randColumna)
                temp = campo.get(i).get(j);
                campo.get(i).set(j, campo.get(randFila).get(randColumna));
                campo.get(randFila).set(randColumna, temp);
            }
        }
    }

    
    public static void rankingMayorMenorBurbuja(ArrayList<ArrayList<Soldado>> campo) {
        ArrayList<Soldado> ejercito1 = new ArrayList<>();
        ArrayList<Soldado> ejercito2 = new ArrayList<>();
    
        // Dividir soldados en Ejército 1 y Ejército 2
        for (int i = 0; i < campo.size(); i++) {
            for (int j = 0; j < campo.get(i).size(); j++) {
                Soldado soldado = campo.get(i).get(j);
                if (soldado != null) {
                    // Si el nombre termina en '1', pertenece al Ejército 1
                    if (soldado.getNombre().charAt(soldado.getNombre().length() - 1) == '1') {
                        ejercito1.add(soldado);
                    }
                    // Si el nombre termina en '2', pertenece al Ejército 2
                    else if (soldado.getNombre().charAt(soldado.getNombre().length() - 1) == '2') {
                        ejercito2.add(soldado);
                    }
                }
            }
        }
    
        // Ordenar Ejército 1 en orden descendente de vida (mayor a menor)
        for (int i = 0; i < ejercito1.size() - 1; i++) {
            for (int j = 0; j < ejercito1.size() - 1 - i; j++) {
                if (ejercito1.get(j).getVida() < ejercito1.get(j + 1).getVida()) {
                    // Intercambiar si está en el orden incorrecto
                    Soldado temp = ejercito1.get(j);
                    ejercito1.set(j, ejercito1.get(j + 1));
                    ejercito1.set(j + 1, temp);
                }
            }
        }
    
        // Ordenar Ejército 2 en orden descendente de vida (mayor a menor)
        for (int i = 0; i < ejercito2.size() - 1; i++) {
            for (int j = 0; j < ejercito2.size() - 1 - i; j++) {
                if (ejercito2.get(j).getVida() < ejercito2.get(j + 1).getVida()) {
                    // Intercambiar si está en el orden incorrecto
                    Soldado temp = ejercito2.get(j);
                    ejercito2.set(j, ejercito2.get(j + 1));
                    ejercito2.set(j + 1, temp);
                }
            }
        }
    }
    


    public static void rankingMayorMenorSeleccion(ArrayList<ArrayList<Soldado>> campo){
        Soldado temp1, temp2;
        // Recorre todas las posiciones del campoo
        for (int i = 0; i < campo.size(); i++){
            for (int j = 0; j < campo.get(i).size(); j++){
                int filMenorEj1 = i, colMenorEj1 = j, filMenorEj2 = i, colMenorEj2 = j;

                // Busca el soldado con la menor vida a partir de la posición actual
                for (int k = i; k < campo.size(); k++){
                    for (int l = 0; l < campo.get(k).size(); l++){
                        
                        // Evita revisar posiciones ya evaluadas
                        if(campo.get(i).get(j) != null){
                            if (campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '1'){
                               if (k == i && l <= j) {
                                   continue;
                               }
                               // Cambia las coordenadas si se encuentra un soldado con vida más baja
                               if (campo.get(k).get(l) != null && campo.get(filMenorEj1).get(colMenorEj1) != null){
                                   if (campo.get(k).get(l).getVida() < campo.get(filMenorEj1).get(colMenorEj1).getVida()){
                                       filMenorEj1 = k;
                                       colMenorEj1 = l;
                                   }
                               }
                           }
                           if (campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '2'){
                               if (k == i && l <= j) {
                                   continue;
                               }
                               // Cambia las coordenadas si se encuentra un soldado con vida más baja
                               if (campo.get(k).get(l) != null && campo.get(filMenorEj2).get(colMenorEj2) != null){
                                   if (campo.get(k).get(l).getVida() > campo.get(filMenorEj2).get(colMenorEj2).getVida()){
                                       filMenorEj2 = k;
                                       colMenorEj2 = l;
                                   }
                               }
                           }
                        }
                        
                    }
                }
                // Intercambia los soldados si se encontró uno con menor vida
                if (filMenorEj1 != i || colMenorEj1 != j){
                    temp1 = campo.get(i).get(j);
                    campo.get(i).set(j, campo.get(filMenorEj1).get(colMenorEj1));
                    campo.get(filMenorEj1).set(colMenorEj1, temp1);
                }
                if (filMenorEj2 != i || colMenorEj2 != j){
                    temp2 = campo.get(i).get(j);
                    campo.get(i).set(j, campo.get(filMenorEj2).get(colMenorEj2));
                    campo.get(filMenorEj2).set(colMenorEj2, temp2);
                }
            }
        }
    }

    
    public static void determinarGanador(ArrayList<ArrayList<Soldado>> campo){
        double sumEj1 = 0, sumEj2 = 0;
        // Recorre todo el campoo de soldados
        for(int i = 0; i < campo.size(); i++){
            for(int j = 0; j < campo.get(i).size(); j++){
                // Si la posición actual contiene un soldado, suma su vida al total
                if(campo.get(i).get(j) != null){
                    if(campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '1'){
                        // Acumula el valor de la vida del soldado
                        sumEj1 +=campo.get(i).get(j).getVida();
                    }
                    if(campo.get(i).get(j).getNombre().charAt(campo.get(i).get(j).getNombre().length() - 1) == '2'){
                        // Acumula el valor de la vida del soldado
                        sumEj2 +=campo.get(i).get(j).getVida();
                    }
                }
                
            }
        }
        if(sumEj1 == sumEj2){
            System.out.println("\nEMPATE\nPuntos de vida totales: \nEjercito X1: " + sumEj1 +" = " + sumEj2 + " Ejercito X2");
        }
        else if(sumEj1 > sumEj2){
            System.out.println("\nGANA EL EJERCITO X1\nPuntos de vida totales: \nEjercito X1: " + sumEj1 +" > " + sumEj2 + " Ejercito X2");
        }
        else{
            System.out.println("\nGANA EL EJERCITO X2\nPuntos de vida totales: \nEjercito X1: " + sumEj1 +" < " + sumEj2 + " Ejercito X2");
        }
    }
}
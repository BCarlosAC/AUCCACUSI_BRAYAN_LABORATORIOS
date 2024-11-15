package L7;
import java.util.*;
public class VideoJuego4 {
    public static void main(String [] args){
        Random rand = new Random();
        Soldado[][] campo = new Soldado[10][10];
        Soldado[] ej1 = new Soldado[rand.nextInt(10) + 1];
        Soldado[] ej2 = new Soldado[rand.nextInt(10) + 1];
        crearSoldados(ej1, '@');
        crearSoldados(ej2, '?');
        asignarSoldados(campo, ej1);
        asignarSoldados(campo, ej2);
        mostrarTabla(campo);
    }
    public static void crearSoldados(Soldado[] ej, char a){
        Random rand = new Random();
        for(int i = 0; i < ej.length; i++){
            ej[i] = new Soldado();
            ej[i].setVida(rand.nextInt(5) + 1);
            if(i == 10)
                ej[i].setNombre("" + ej[i].getVida() + a);
            else
                ej[i].setNombre("" + ej[i].getVida() + a);
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
        for(char i = 'A'; i < 'K'; i++){
            System.out.print("   " + i + "  ");
        }
        System.out.println();
        for(int i = 0; i < campo.length; i++){
            if(i == 9)
                System.out.print(i + 1);
            else
                System.out.print((i + 1) + " ");

            for(int j = 0; j < campo[i].length; j++){
                if (campo[i][j] != null)
                    System.out.print(" |" + campo[i][j].getNombre());
                else
                    System.out.print(" |  ");
            }
            System.out.println(" |");
            System.out.println("   -----------------------------------------");
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
        for(int i = 0; i < ej.length - 1; i++){
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
            r1 = rand.nextInt(10) + 1;
            r2 = rand.nextInt(10) + 1;
            aux = ej[r1];
            ej[r1] = ej[r2];
            ej[r2] = aux;
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
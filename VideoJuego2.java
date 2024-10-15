import java.util.*;
public class VideoJuego2 {
    public static void main(String [] args){
        Random rand = new Random();
        Soldado[][] campo = new Soldado[10][10];
        int cantSoldados = rand.nextInt(10) + 1;
        int i = 1;
        while(i != 10) {
            int fila = rand.nextInt(10) + 1;
            int columna = rand.nextInt(10) + 1;
            if(i == 1){
                campo[fila][columna] = new Soldado();
            }
            while(campo[fila][columna].getEstado() == true){
                campo[fila][columna] = new Soldado();
                campo[fila][columna].setNombre("Soldado " + i);
                campo[fila][columna].setVida(rand.nextInt(5) + 1); 
                i++;
            }
        }
    }
    public static void mostrarTabla(Soldado[][] camp) {
        for(int i = 0; i < camp.length; i++){
            for(int j = 0; j < camp[i].length; j++){
                if(camp[i][j].getEstado()){
                    System.out.print("|S"+camp[i][j].getNombre().charAt(7));    
                }
                else
                    System.out.print("| ");
            }
            System.out.println("---------------------------------");
        }
    }
}





































































/*

        Nave [] misNaves = new Nave[2];
        Scanner sc = new Scanner(System.in);
        String nomb = "", col = "";
        int fil, punt = 0;
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
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
            misNaves[i] = new Nave(); //Se crea un objeto Nave y se asigna su referencia a misNaves
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }
        System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves);
        System.out.println();
        System.out.print("Ingrese el nombre de la nave(s) a mostrar: ");
        String elNombre = sc.next();
        sc.nextLine();
        mostrarPorNombre(misNaves, elNombre);
        System.out.print("\nIngrese el limite de puntos de la(s) nave(s) a mostrar: ");
        int puntosPedidos = sc.nextInt();
        sc.nextLine();
        
        mostrarPorPuntos(misNaves, puntosPedidos);
        System.out.println("\nLa nave con mayor número de puntos es: ");
        mostrarDatosNave(mostrarMayorPuntos(misNaves));
        System.out.println();
        Nave[] arrAleatorio = devuelveArray(misNaves);
        System.out.println("Aleatorio");
        mostrarNaves(arrAleatorio);
        int pos=busquedaLinealNombre(misNaves, nomb);
        System.out.println("Ordenando por puntos BURBUJA");
        ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        System.out.println("\n\nDESORDENANDO LAS NAVES");
        devuelveArray(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Ordenando por nombre BURBUJA");
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);
        //mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
        

        System.out.print("\n\nIngrese el nombre de la nave a buscar: ");
        nomb = sc.nextLine();
        pos=busquedaBinariaNombre(misNaves, nomb);
        System.out.println("La nave con nombre "+ nomb + " esta en la posicion " + 
            pos + " del arreglo de naves\n");
        System.out.println("\n\nDESORDENANDO LAS NAVES");
        devuelveArray(misNaves);
        mostrarNaves(misNaves);

        System.out.println("Ordenando por puntos SELECCION");
        ordenarPorPuntosSeleccion(misNaves);
        mostrarNaves(misNaves);
        System.out.println("\n\nDESORDENANDO LAS NAVES");
        devuelveArray(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Ordenando por nombres SELECCION");
        ordenarPorNombreSeleccion(misNaves);
        mostrarNaves(misNaves);
        
        System.out.println("\n\nDESORDENANDO LAS NAVES");
        devuelveArray(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Ordenando por puntos INSERSION");
        ordenarPorPuntosInsercion(misNaves);
        mostrarNaves(misNaves);
        System.out.println("\n\nDESORDENANDO LAS NAVES");
        devuelveArray(misNaves);
        mostrarNaves(misNaves);
        System.out.println("Ordenando por nombre INSERSION");
        ordenarPorNombreInsercion(misNaves);
        mostrarNaves(misNaves);

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

        //Método para buscar la primera nave con un nombre que se pidió por teclado
        public static int busquedaLinealNombre(Nave[] flota, String s){
            for (int i = 0; i < flota.length; i++) {
                if (flota[i].getNombre().equals(s)){
                    return i;
                }
            }
            return -1;
        }

        //Método que ordena por número de puntos de menor a mayor
        public static void ordenarPorPuntosBurbuja(Nave[] flota){
            for (int i = 0; i < flota.length; i++) {
                for (int j = 0; j < flota.length - 1 - i; j++) {
                    if(flota[j].getPuntos() > flota[j + 1].getPuntos()) {
                        Nave temp = flota[j];
                        flota[j] = flota[j + 1];
                        flota[j + 1] = temp;
                    }
                }
            }
        }


        //Método que ordena por nombre de A a Z
        public static void ordenarPorNombreBurbuja(Nave[] flota){
            for (int i = 0; i < flota.length; i++) {
                for (int j = 0; j < flota.length - 1 - i; j++) {
                    if(flota[j].getNombre().compareTo(flota[j + 1].getNombre()) > 0){
                        Nave temp = flota[j];
                        flota[j] = flota[j + 1];
                        flota[j + 1] = temp;
                    }
                }
            }
        }

        //Método para buscar la primera nave con un nombre que se pidió por teclado
        public static int busquedaBinariaNombre(Nave[] flota, String s){
            int alta, baja, media;
            baja = 0;
            alta = flota.length - 1;
            while (baja <= alta) {
                media = (alta + baja) / 2;
                if (flota[media].getNombre().equals(s))
                    return media;
                else if (s.compareTo(flota[media].getNombre()) < 0)
                    alta = media - 1;
                else
                    baja = media + 1;
            }
            return -1;
        }

        //Método que ordena por número de puntos de menor a mayor
        public static void ordenarPorPuntosSeleccion(Nave[] flota) {
            for (int i = 0; i < flota.length - 1; i++) {
                int indexMenor = i;
                for (int j = i + 1; j < flota.length; j++) {
                    if (flota[j].getPuntos() < flota[indexMenor].getPuntos()) {
                        indexMenor = j; 
                    }
                }
                if (indexMenor != i) {
                    Nave temp = flota[i];
                    flota[i] = flota[indexMenor];
                    flota[indexMenor] = temp;
                }
            }
        }
        

        //Método que ordena por nombre de A a Z
        public static void ordenarPorNombreSeleccion(Nave[] flota){
            for (int i = 0; i < flota.length - 1; i++) {
                int indexMenor = i;
                for (int j = i + 1; j < flota.length; j++) {
                    if (flota[j].getNombre().compareTo(flota[indexMenor].getNombre()) < 0) {
                        indexMenor = j;
                    }
                }
                if (indexMenor != i) {
                    Nave temp = flota[i];
                    flota[i] = flota[indexMenor];
                    flota[indexMenor] = temp;
                }
            }
        }

        //Método que muestra las naves ordenadas por número de puntos de mayor a menor
        public static void ordenarPorPuntosInsercion(Nave[] flota) {
            Nave aux;
            int cont1, cont2;
            for (cont1 = 1; cont1 < flota.length; cont1++) {
                aux = flota[cont1];
                for(cont2=cont1-1; cont2 >= 0 && flota[cont2].getPuntos() > aux.getPuntos(); cont2--) {
                    flota[cont2 + 1] = flota[cont2];
                    flota[cont2] = aux;
                }
            }
        }
        
        //Método que muestra las naves ordenadas por nombre de Z a A
        public static void ordenarPorNombreInsercion(Nave[] flota){
            Nave aux;
            int cont1, cont2;
            for (cont1 = 1; cont1 < flota.length; cont1++) {
                aux = flota[cont1];
                for(cont2=cont1-1; cont2 >= 0 && flota[cont2].getNombre().compareTo(aux.getNombre()) > 0; cont2--) {
                    flota[cont2 + 1] = flota[cont2];
                    flota[cont2] = aux;
                }
            }
        }
}
*/
import java.util.*;
public class DemoBatalla {
    public static void main(String [] args){
        Nave [] misNaves = new Nave[8];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        boolean est;

        for (int i = 0; i < misNaves.length; i++) {
                System.out.println("Nave " + (i+1));
                System.out.print("Nombre: ");
                nomb = sc.next();
                System.out.println("Fila ");
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
            mostrarPorNombre(misNaves, nomb);
            mostrarPorPuntos(misNaves, punt);
            System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves));
            //leer un nombre
            //mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
            int pos=busquedaLinealNombre(misNaves,nomb);
            ordenarPorPuntosBurbuja(misNaves);
            mostrarNaves(misNaves);
            ordenarPorNombreBurbuja(misNaves);
            mostrarNaves(misNaves);
            //mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
            pos=busquedaBinariaNombre(misNaves,nombre);
            ordenarPorPuntosSeleccion(misNaves);
            mostrarNaves(misNaves);
            ordenarPorNombreSeleccion(misNaves);
            mostrarNaves(misNaves);
            ordenarPorPuntosInsercion(misNaves);
            mostrarNaves(misNaves);
            ordenarPorNombreInsercion(misNaves);
            mostrarNaves(misNaves);
            }
        
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
                if (flota[i].equals(s)){
                    return i;
                }
            }
            return -1;
        }




        //Método que ordena por número de puntos de menor a mayor
        public static void ordenarPorPuntosBurbuja(Nave[] flota){
            for (int i = 0; i < flota.length; i++) {
                for (int j = 0; j < flota.length; j++) {
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
                for (int j = 0; j < flota.length; j++) {
                    if(flota[j].getNombre().charAt(0) > flota[j + 1].getNombre().charAt(0)){
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
                else if (s.charAt(0) < flota[media].getNombre().charAt(0))
                    alta = media - 1;
                else
                    baja = media + 1;
            }
            return -1;
            /*REVISAR*/
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
                for (int j = i + 1; i  < flota.length; j++) {
                    if (flota[j].getNombre().charAt(0) < flota[indexMenor].getNombre().charAt(0)) {
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

        /* */

        //Método que muestra las naves ordenadas por número de puntos de mayor a menor
        public static void ordenarPorPuntosInsercion(Nave[] flota){
        
        }
        //Método que muestra las naves ordenadas por nombre de Z a A
        public static void ordenarPorNombreInsercion(Nave[] flota){
        }
}
package L7;

public class Soldado {
    private String nombre;
    private int vida;
    private int fila;
    private char columna;
    private boolean estado = false;

    //Metodos mutadores
    public void setNombre( String n){
        nombre = n;
    }
    public void setFila(int f){
        fila = f;
    }
    public void setColumna(char c){
        columna = c;    
    }
    public void setVida(int v){
        vida = v;
    }
    public void setEstado(boolean e){
        estado = e;
    }
    
    
    // Metodos accesores
    public String getNombre(){
        return nombre;
    }
    public int getFila(){
        return fila;
    }
    public char getColumna(){
        return columna;
    }
    public int getVida(){
        return vida;
    }
    public boolean getEstado(){
        return estado;
    }

    public String toString(){
        return "Nombre: " + getNombre() + "    Vida: " + getVida() + 
            "    Fila " + (getFila() + 1) + "    Columna: " + getColumna();
    }
}
public class Soldado {
    private String nombre;
    private int vida;
    private int fila;
    private String columna;
    private boolean estado;

    //Metodos mutadores
    public void setNombre( String n){
        nombre = n;
    }
    public void setFila(int f){
        fila = f;
    }
    public void setColumna(String c){
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
    public String getColumna(){
        return columna;
    }
    public int gerVida(){
        return vida;
    }
    public boolean getEstado(){
        return estado;
    }

    // Completar con otros métodos necesarios
}
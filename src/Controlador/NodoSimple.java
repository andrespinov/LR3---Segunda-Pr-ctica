package Controlador;

public class NodoSimple {
    
    private NodoSimple liga;
    private int dato;

    public NodoSimple(int dato) {
        this.dato = dato;
    }

    public NodoSimple getLiga() {
        return liga;
    }

    public void setLiga(NodoSimple liga) {
        this.liga = liga;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
    
}

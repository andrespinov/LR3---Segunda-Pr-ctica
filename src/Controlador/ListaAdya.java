package Controlador;

import java.util.ArrayList;

public class ListaAdya {

    private NodoSimple[] v;
    private String[] palabras;
    private ArrayList<String[]> caminos = new ArrayList<>();

    /**
     * Método constructor de la clase Lista ligada de Adyacencia
     *
     * @param p: vector que contiene las palabras a insertar en el grafo
     */
    public ListaAdya(String[] p) {
        v = new NodoSimple[p.length];
        palabras = new String[p.length];
        for (int i = 0; i < p.length; i++) {
            agregarPalabra(p[i]);
        }
    }

    /**
     * Método para agregar una palabra como vertice del grafo.
     *
     * @param p : palabra a insertar en el gráfo.
     */
    private void agregarPalabra(String p) {
        int i = 0;
        NodoSimple x;
        while (palabras[i] != null) {
            i++;
        }
        palabras[i] = p;
        int j = 0;
        while (palabras[j].compareTo(p) != 0) {
            if (adyacente(palabras[j], p)) {
                x = new NodoSimple(i);
                x.setLiga(v[j]);
                v[j] = x;
                x = new NodoSimple(j);
                x.setLiga(v[i]);
                v[i] = x;
            }
            j++;
        }
    }

    /**
     * Método que determina si una palabra es adyacente a otra (se diferencian
     * por una y sólo una letra).
     *
     * @param a: primera palabra a comparar
     * @param b: segunda palabra a comparar
     * @return
     */
    private boolean adyacente(String a, String b) {
        String mayor = a;
        String menor = b;
        if (a.length() < b.length()) {
            mayor = b;
            menor = a;
        }
        if (mayor.length() != menor.length() + 1 && mayor.length() != menor.length()) {
            return false;
        }
        String h;
        if (mayor.length() > menor.length()) {
            for (int i = 0; i < mayor.length(); i++) {
                h = eliminar(mayor, i);
                if (h.equals(menor)) {
                    return true;
                }
            }

        } else {
            int cont = 0;
            for (int i = 0; i < menor.length(); i++) {
                if (menor.charAt(i) != mayor.charAt(i)) {
                    cont++;
                }
            }
            if (cont == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que elimina el carácter en cierta posición de un String
     *
     * @param a: palabra a modificar
     * @param i: posición del caracter a eliminar
     * @return
     */
    private String eliminar(String a, int i) {
        String b = a.substring(0, i) + a.substring(i + 1, a.length());
        return b;
    }

    /**
     * Método que imprime en consóla cada vértice del grafo y sus vértices
     * adyacentes
     */
    public void escribirAdyacentes() {
        int i = 0;
        NodoSimple p;
        while (i < v.length) {
            p = v[i];
            System.out.println("adyacentes a " + palabras[i]);
            while (p != null) {
                System.out.println(palabras[p.getDato()]);
                p = p.getLiga();
            }
            i++;
        }
    }

    /**
     * Método para hallar todos los caminos posibles de un vértice a otro, este
     * método sólo se encarga de llamar al que realmente hace los recorridos, y
     * de retornar el ArrayList.
     *
     * @param a: vértice/palabra de partida
     * @param b: vértice/palabra de llegada
     * @return ArrayList de vectores (tipo String). Cada vector es un camino.
     */
    public ArrayList<String[]> caminos(String a, String b) {
        caminos.clear();
        int[] visitados = new int[palabras.length];
        int[] camino = new int[palabras.length];
        caminos(palabra(a), palabra(b), 0, visitados, camino);
        return caminos;
    }

    /**
     * Método que determina los caminos entre dos vértices del grafo
     *
     * @param i: vértice de partida
     * @param j: vértice de llegada
     * @param pos: número auxiliar
     * @param visitados: vector auxiliar
     * @param camino: vector auxiliar
     */
    private void caminos(int i, int j, int pos, int[] visitados, int[] camino) {
        int[] vec = visitados.clone();
        camino[pos] = i;
        if (i == j) {
            addCamino(camino, pos);
        }
        vec[i] = 1;
        NodoSimple x = v[i];
        int n;
        while (x != null) {
            n = x.getDato();
            if (vec[n] == 0) {
                caminos(n, j, pos + 1, vec, camino);
            }
            x = x.getLiga();
        }
    }

    /**
     * Añadir un camino al ArrayList de caminos
     *
     * @param vec: vector que contiene el camino
     * @param i: posición del vector hasta donde llega el camino
     */
    private void addCamino(int[] vec, int i) {
        String[] n = new String[i + 1];
        for (int j = 0; j <= i; j++) {
            n[j] = palabras[vec[j]];
        }
        caminos.add(n);
    }
    
    /**
     * Método que determina el número del vértice que le corresponde a una palabra en el grafo
     * @param p: palabra a determinar 
     * @return 
     */
    private int palabra(String p){
        int i = 0;
        while(palabras[i].compareTo(p) != 0) i++;
        return i;
    }

}

class ejercicios {

    public static void main(String[] s) {
        String[] g = {"cero", "casa", "camo", "cama", "caro", "ceros", "cara"};
        ListaAdya h = new ListaAdya(g);
        h.escribirAdyacentes();
        ArrayList<String[]> caminos = h.caminos("casa", "caro");
        int i = 0;
        while(i < caminos.size()){
            g = caminos.get(i);
            for (int j = 0; j < g.length; j++) {
                System.out.print(g[j] +" - ");
            }
            System.out.println("");
            i++;
        }
        
//        caminos = h.caminos(6, 0);
//        i = 0;
//        while(i < caminos.size()){
//            g = caminos.get(i);
//            for (int j = 0; j < g.length; j++) {
//                System.out.print(g[j] +" - ");
//            }
//            System.out.println("");
//            i++;
//        }
    }

    public static void ejemplo(int i, int[] visitados) {
        if (i >= 15) {
            return;
        }
        visitados[i] = 1;
        for (int j = 0; j < 15; j++) {
            System.out.print(visitados[j] + " - ");
        }
        System.out.println(i);
        ejemplo(i + 1, visitados);
        ejemplo(i + 3, visitados);
    }
}
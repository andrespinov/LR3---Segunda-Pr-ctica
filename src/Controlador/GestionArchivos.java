package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionArchivos {

    private FileReader fr;
    private BufferedReader buffer;

    public GestionArchivos(File archivo) throws FileNotFoundException {
        fr = new FileReader(archivo);
        buffer = new BufferedReader(fr);
    }

    public String[] generarVector() {
        String linea;
        ArrayList<String> palabras = new ArrayList<>();
        try {
            while ((linea = buffer.readLine()) != null) {
                palabras.add(normalizar(linea));
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] p = new String[palabras.size()];
        p = palabras.toArray(p);
        return p;
    }

    public String normalizar(String s) {
        String r = "";
        char a;
        for (int i = 0; i < s.length(); i++) {
            a = minuscula(s.charAt(i));
            if(a != ','){
                r = r + a;
            }
        }

        return r;
    }

    public char minuscula(char c) {
        char a = ',';
        if (Character.isLetterOrDigit(c)) {
            a = c;
            if ((int) c > 64 && (int) c < 91) {
                int b = (int) c + 32;
                a = (char) b;
            } else if ((int) c == 209) {
                a = (char) 241;
            }
            return a;
        }
        return  a;
    }
}

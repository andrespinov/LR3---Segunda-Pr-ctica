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
                palabras.add(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] p = new String[palabras.size()];
        p = palabras.toArray(p);
        return p;
    }
}

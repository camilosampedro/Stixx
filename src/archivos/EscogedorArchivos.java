/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author camilo
 */
public class EscogedorArchivos {

    private final File directorio;
    private ArrayList<File> archivosSeleccionados;
    private ArrayList<File> archivos;
    public final String[] extensionesValidas = {"mp3", "wav", "midi", "aac"};

    public EscogedorArchivos(File directorio) {
        this.directorio = directorio;
        archivos = new ArrayList();
        generarListaArchivos();
    }

    public ArrayList generarAleatorios(int cantidad) {
        archivosSeleccionados = new ArrayList();
        for (int i = 1; i < cantidad; i++) {
            archivosSeleccionados.add(archivos.get((int) (Math.random() * archivos.size())));
        }
        return null;
    }

    private void generarListaArchivos() {
        buscarArchivosMusica(directorio);
    }

    private void buscarArchivosMusica(File directorio) {
        for (File archivo : directorio.listFiles()) {
            String nombre = archivo.getName();
            String extension = nombre.substring(nombre.lastIndexOf('.') + 1, nombre.length());
            if (archivo.isDirectory()) {
                buscarArchivosMusica(archivo);
            }
            if (esMusica(extension)) {
                archivos.add(archivo);
            }
        }
    }

    private boolean esMusica(String extension) {
        for (String ext : extensionesValidas) {
            if (extension.equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }
    
    public int getNumeroCanciones(){
        return archivos.size();
    }

    public File getDirectorio() {
        return directorio;
    }

    public ArrayList<File> getArchivosSeleccionados() {
        return archivosSeleccionados;
    }

    public ArrayList<File> getArchivos() {
        return archivos;
    }

    public String[] getExtensionesValidas() {
        return extensionesValidas;
    }
    
    

}

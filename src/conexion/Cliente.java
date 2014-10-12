/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilo
 */
public class Cliente {

    static final byte SINERROR = 0;
    static final byte NOHAYCONEXION = 1;
    static final byte OTROERROR = 2;

    public static byte verificarConexion(String host) {
        try {
            InetAddress.getByName(host).isReachable(10);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return NOHAYCONEXION;
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return OTROERROR;
        }
        return SINERROR;
    }

    public static byte enviarArchivos(String host, ArrayList<File> archivos) {
        switch (verificarConexion(host)) {
            case SINERROR:
                System.out.println("Conexión éxitosa");
                for (File archivo : archivos) {
                    enviarArchivo(host,archivo);
                }
                break;
            case NOHAYCONEXION:
                break;
            case OTROERROR:
                break;
        }
        return SINERROR;
    }

    private static byte enviarArchivo(String host, File archivo) {
        DataInputStream input;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        //Fichero a transferir

        try {
            Socket client = new Socket(host, 5000);
            bis = new BufferedInputStream(new FileInputStream(archivo));
            bos = new BufferedOutputStream(client.getOutputStream());

            //Enviamos el nombre del fichero
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(archivo.getName());

            //Enviamos el fichero
            byteArray = new byte[8192];
            while ((in = bis.read(byteArray)) != -1) {
                bos.write(byteArray, 0, in);
            }

            bis.close();
            bos.close();

        } catch (Exception e) {
            System.err.println(e);
        }
        return SINERROR;
    }
}

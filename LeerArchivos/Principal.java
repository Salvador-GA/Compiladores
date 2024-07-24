package Compiladores.src.LeerArchivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que abre un archivo, y cuenta las palabras, comas y puntos que tiene el
 * texto
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Principal {
    public static void main(String[] args) {
        String archivo = "/home/salvador/Documentos/Compiladores/Compiladores/src/LeerArchivos/miPrograma.uam";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int caracter;

            //imprimir contenido caracter por caracter
            System.out.println("Contenido desdel archivo:\n");
            while ((caracter = br.read()) != -1) {
                System.out.print((char) caracter);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}

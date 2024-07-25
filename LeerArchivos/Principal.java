package Compiladores.src.LeerArchivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que abre un archivo, y cuenta las palabras, comas y puntos que tiene el
 * texto
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.1
 */
public class Principal {
    public static void main(String[] args) {
        // puede incluir la ruta relativa en su lugar, si el archivo miPrograma.uam se
        // encuentra en
        // la misma carpeta desde donde se esta ejecutadno el programa
        String archivo = "/home/salvador/Documentos/Compiladores/Compiladores/src/LeerArchivos/miPrograma.uam";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int caracter;

            // colocamos una marca desde el inicio del buffer y que sera valida hasta 5000
            // caracteres adelante
            br.mark(5000);
            
            // imprimir contenido caracter por caracter
            System.out.println("Contenido desdel archivo:\n");
            while ((caracter = br.read()) != -1) {
                System.out.print((char) caracter);
            }

            // regresamos a la marca establecida, al inicio del archivo en nuestro caso
            br.reset();
            // imprimir contenido caracter por caracter convirtiendolo a mayusculas
            System.out.println("\n\nContenido desdel archivo en mayusculas:\n");
            while ((caracter = br.read()) != -1) {
                if (Character.isAlphabetic(caracter)) {
                    System.out.print(Character.toUpperCase((char)caracter));
                } else {
                    System.out.print((char) caracter);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}

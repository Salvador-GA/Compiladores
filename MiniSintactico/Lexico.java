package Compiladores.src.MiniSintactico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @brief Clase Lexico que se encarga de realizar el análisis léxico de un archivo fuente.
 *
 * La clase Lexico se utiliza para leer un archivo fuente y convertir la entrada en tokens
 * que representan las distintas unidades léxicas del lenguaje.
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Lexico {
    /**
     * @brief Lector de búfer para leer el archivo fuente.
     */
    BufferedReader br;

    /**
     * @brief Constructor que inicializa el objeto Lexico con un archivo.
     *
     * @param nombreArchivo El nombre del archivo fuente a analizar.
     */
    public Lexico(String nombreArchivo) {
        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Obtiene el siguiente token del archivo fuente.
     *
     * Este método realiza el análisis léxico, leyendo carácter por carácter del archivo
     * y formando tokens que representan las distintas unidades léxicas como identificadores,
     * operadores, paréntesis, etc.
     *
     * @return Token El siguiente token identificado en el archivo fuente.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public Token nexToken() throws IOException {
        int caracter;
        String cadena = "";
        int estado = 0;
        boolean bandera = true;

        do {
            br.mark(1);
            caracter = br.read();
            char ch = (char) caracter;
            switch (estado) {
                case 0:
                    if (Character.isLetter(ch)) {
                        cadena += ch;
                        estado = 1;
                    } else if (ch == '+') {
                        cadena += ch;
                        return new Token("MAS", cadena);
                    } else if (ch == '*') {
                        cadena += ch;
                        return new Token("POR", cadena);
                    } else if (ch == '(') {
                        cadena += ch;
                        return new Token("PARABRE", cadena);
                    } else if (ch == ')') {
                        cadena += ch;
                        return new Token("PARCIERRA", cadena);
                    } else if (Character.isWhitespace(ch)) {
                        // Ignorar espacios en blanco
                    } else if (caracter == -1) {
                        return new Token("DELIMITADOR", "EOF");
                    } else {
                        cadena += ch;
                        bandera = false;
                    }
                    break;
                case 1:
                    if (Character.isLetterOrDigit(ch)) {
                        cadena += ch;
                    } else {
                        br.reset();
                        return new Token("ID", cadena);
                    }
                    break;
            }
        } while (bandera);
        return new Token("NoReconocido", cadena);
    }
}
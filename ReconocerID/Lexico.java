package Compiladores.src.ReconocerID;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase Lexico, abre el archivo fuente en el constructor e identifica lexemas
 * de ID en la funcion nextToken
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Lexico {
    BufferedReader br;

    /**
     * Constructor de la clase, abre el archivo para lectura con un BufferedReader
     * 
     * @param nombreArchivo
     */
    public Lexico(String nombreArchivo) {
        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve el siguiete token encontrado, puede ser un token ID o un token
     * NoReconocido para cualquier otro caso, ademas el caso especial del token EOF
     * cuando ya se ha llegado al final del archivo
     * 
     * @return el siguiente token encontrado
     * @throws IOException error al leer el archivo
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
                    if (Character.isLetter(ch) || ch == '_') {
                        cadena += ch;
                        estado = 1;
                    } else if (Character.isWhitespace(ch)) {
                        // Ignorar espacios en blanco incluye tab, saltos de linea, etc.
                    } else if (caracter == -1) {
                        // Se termino el archivo, regresamos un token que especifique esto
                        return new Token("EOF", "EOF");
                    } else {
                        cadena += ch;
                        bandera = false;
                    }
                    break;
                case 1:
                    if (Character.isLetterOrDigit(ch) || ch == '_') {
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

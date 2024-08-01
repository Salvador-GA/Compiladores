package Compiladores.src.ReconocerID;

import java.io.IOException;

/**
 * Clase principal, crea un objeto de la clase Lexico y le pide tokens hasta que
 * le responden con el token tipo EOF
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Principal {

    public static void main(String[] args) {
        String archivo = "/home/salvador/Documentos/Compiladores/Compiladores/src/ReconocerID/miPrograma.uam";
        Lexico lex = new Lexico(archivo);
        Token token;
        do {
            try {
                token = lex.nexToken();
                System.out.println(token.toString());
            } catch (IOException e) {
                e.printStackTrace();
                token = new Token("EOF", "EOF");
            }
        } while (!token.getTipo().equals("EOF"));
    }
}

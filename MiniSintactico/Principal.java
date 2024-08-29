package Compiladores.src.MiniSintactico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @brief Clase principal del analizador sintáctico.
 *
 * Esta clase representa la entrada principal al programa de análisis sintáctico. 
 * Utiliza un archivo de entrada, realiza el análisis léxico para convertir la entrada en tokens, 
 * y luego utiliza una pila para realizar el análisis sintáctico basado en una tabla de análisis sintáctico.
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Principal {

    /**
     * @brief Método principal que inicia el análisis sintáctico.
     *
     * Este método lee un archivo fuente, realiza el análisis léxico y luego realiza
     * el análisis sintáctico utilizando una tabla de análisis sintáctico predictivo.
     * 
     * @param args Argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        // Ruta al archivo fuente
        String archivo = "/home/salvador/Documentos/Compiladores/Compiladores/src/MiniSintactico/miPrograma.uam";
        
        // Inicializa el analizador léxico con el archivo fuente
        Lexico lex = new Lexico(archivo);
        
        // Inicializa un token vacío
        Token token = new Token(null, null);
        
        // Inicializa la tabla de análisis sintáctico
        TablaSintactica tablaSin = new TablaSintactica();
        
        // Pila para el análisis sintáctico
        Stack<String> pila = new Stack<>();
        pila.push("DELIMITADOR");
        pila.push("E");
        
        // Variables de control
        boolean avanzarCad = true;
        String sigSimbolo;
        ArrayList<String> sigProd;
        boolean error = false;

        do {
            // Avanza al siguiente token si corresponde
            if (avanzarCad) {
                try {
                    token = lex.nexToken();
                    avanzarCad = false;
                    System.out.println(token.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            // Siguiente símbolo en la pila
            sigSimbolo = pila.pop();
            
            // Verifica si el símbolo en la pila coincide con el token
            if (sigSimbolo.equals(token.getTIPO())) {
                avanzarCad = true;
            } else if (tablaSin.esTerminal(sigSimbolo)) {
                // Error de sintaxis si el símbolo es terminal y no coincide
                System.out.println("Error de sintaxis, recibió un " + token.getTIPO() + " y se esperaba un " + sigSimbolo);
                error = true;
                break;
            } else {
                // Busca la siguiente producción en la tabla sintáctica
                sigProd = tablaSin.calcularSig(sigSimbolo, token.getTIPO());
                if (sigProd.size() == 0) {
                    // Error de sintaxis si no hay producción para la combinación actual
                    System.out.println("Error de sintaxis, recibió un " + token.getTIPO() + " y se esperaba un " + sigSimbolo);
                    error = true;
                    break;
                } else {
                    // Apila la producción obtenida
                    for (String prod : sigProd) {
                        if (!prod.equals("VACIA")) {
                            pila.push(prod);
                        }
                    }
                }
            }
        } while (!pila.peek().equals("DELIMITADOR"));

        // Verifica si el análisis sintáctico fue exitoso
        if (!error) {
            System.out.println("Sintaxis correcta");
        }
    }
}


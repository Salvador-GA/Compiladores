package Compiladores.src.MiniSintactico;

/**
 * @brief Clase Token que representa una unidad léxica en el análisis léxico.
 *
 * Un objeto Token encapsula un tipo y un valor, que son utilizados durante el
 * análisis sintáctico para identificar y manejar las distintas partes del código fuente.
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Token {

    /**
     * @brief Tipo del token (e.g., ID, MAS, POR).
     */
    private String TIPO;

    /**
     * @brief Valor del token (e.g., el nombre del identificador, el símbolo del operador).
     */
    private String VALOR;

    /**
     * @brief Constructor que inicializa un token con un tipo y un valor.
     *
     * @param tipo El tipo del token.
     * @param valor El valor del token.
     */
    public Token(String tipo, String valor) {
        this.TIPO = tipo;
        this.VALOR = valor;
    }

    /**
     * @brief Obtiene el tipo del token.
     *
     * @return String El tipo del token.
     */
    public String getTIPO() {
        return TIPO;
    }

    /**
     * @brief Establece el tipo del token.
     *
     * @param tIPO El tipo del token.
     */
    public void setTIPO(String tIPO) {
        TIPO = tIPO;
    }

    /**
     * @brief Obtiene el valor del token.
     *
     * @return String El valor del token.
     */
    public String getVALOR() {
        return VALOR;
    }

    /**
     * @brief Establece el valor del token.
     *
     * @param vALOR El valor del token.
     */
    public void setVALOR(String vALOR) {
        VALOR = vALOR;
    }

    /**
     * @brief Convierte el token a una representación en cadena.
     *
     * Este método proporciona una descripción en cadena del token, que incluye
     * su tipo y valor.
     *
     * @return String Una cadena que representa el token.
     */
    public String toString() {
        return "Tipo = " + TIPO + " valor = " + VALOR;
    }
}
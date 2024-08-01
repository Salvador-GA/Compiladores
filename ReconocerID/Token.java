package Compiladores.src.ReconocerID;

/**
 * Clase Token, almacena el tipo y valor del token
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class Token {
    private String tipo, valor;

    /**
     * Constructor de la clase
     * 
     * @param tipo  del token
     * @param valor del token
     */
    public Token(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    /**
     * Devuelve el tipo del token
     * 
     * @return tipo del token
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de token
     * 
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el valor del token
     * 
     * @return valor del token
     */
    public String getValor() {
        return valor;
    }

    /**
     * Establece el valor de token
     * 
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Devuelve el token en un solo string
     * 
     * @return String que representa el token
     */
    public String toString() {
        return "Tipo = " + tipo + " valor = " + valor;
    }

}
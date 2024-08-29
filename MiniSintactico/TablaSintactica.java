package Compiladores.src.MiniSintactico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @brief Clase TablaSintactica que representa la tabla de análisis sintáctico predictivo para un lenguaje.
 *
 * Esta clase maneja una tabla de análisis sintáctico basada en un conjunto de reglas definidas
 * para un lenguaje específico. Los no terminales y terminales están representados por índices, y
 * la tabla se implementa utilizando estructuras de datos listas.
 * 
 * @author Salvador Gonzalez Arellano
 * @version 1.0
 */
public class TablaSintactica {
    /** @brief Índices para los no terminales. */
    private static final int E = 0;
    private static final int EP = 1;
    private static final int T = 2;
    private static final int TP = 3;
    private static final int F = 4;

    /** @brief Índices para los terminales. */
    private static final int ID = 0;
    private static final int MAS = 1;
    private static final int POR = 2;
    private static final int PARABRE = 3;
    private static final int PARCIERRA = 4;
    private static final int DELIMITADOR = 5;
    
    /** @brief Mapa que asocia los nombres de los símbolos con sus índices. */
    private Map<String, Integer> mapConstantes;
    
    /** @brief Lista que contiene los no terminales del lenguaje. */
    private ArrayList<String> noTerminales;

    /** 
     * @brief Tabla de análisis sintáctico.
     * 
     * Es una lista tridimensional donde cada entrada corresponde a una regla de producción.
     */
	private ArrayList<ArrayList<ArrayList<String>>> tablaAnSin;

    /**
     * @brief Constructor que inicializa la tabla de análisis sintáctico.
     *
     * Este constructor configura los índices para los terminales y no terminales,
     * inicializa la tabla de análisis sintáctico y define las reglas de producción.
     */
    public TablaSintactica() {
        // Inicializa el mapa de constantes
        mapConstantes = new HashMap<>();
        mapConstantes.put("E", E);
        mapConstantes.put("EP", EP);
        mapConstantes.put("T", T);
        mapConstantes.put("TP", TP);
        mapConstantes.put("F", F);
        mapConstantes.put("ID", ID);
        mapConstantes.put("MAS", MAS);
        mapConstantes.put("POR", POR);
        mapConstantes.put("PARABRE", PARABRE);
        mapConstantes.put("PARCIERRA", PARCIERRA);
        mapConstantes.put("DELIMITADOR", DELIMITADOR);

        // Inicializa la lista de no terminales
        noTerminales = new ArrayList<>();
        noTerminales.add("E");
        noTerminales.add("EP");
        noTerminales.add("T");
        noTerminales.add("TP");
        noTerminales.add("F");

        // Inicializa la tabla de análisis sintáctico
        tablaAnSin = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablaAnSin.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                tablaAnSin.get(i).add(new ArrayList<String>());
            }
        }

        // Definición de las reglas de producción en la tabla de análisis sintáctico
        // Tabla[E][ID] E -> T EP
        tablaAnSin.get(E).get(ID).addFirst("T");
        tablaAnSin.get(E).get(ID).addFirst("EP");

        // Tabla[T][ID] T -> F TP
        tablaAnSin.get(T).get(ID).addFirst("F");
        tablaAnSin.get(T).get(ID).addFirst("TP");

        // Tabla[F][ID] F -> ID
        tablaAnSin.get(F).get(ID).addFirst("ID");

        // Tabla[EP][MAS] EP -> + T EP
        tablaAnSin.get(EP).get(MAS).addFirst("MAS");
        tablaAnSin.get(EP).get(MAS).addFirst("T");
        tablaAnSin.get(EP).get(MAS).addFirst("EP");

        // Tabla[TP][MAS] TP -> VACIA
        tablaAnSin.get(TP).get(MAS).addFirst("VACIA");

        // Tabla[TP][POR] TP -> * F TP
        tablaAnSin.get(TP).get(POR).addFirst("POR");
        tablaAnSin.get(TP).get(POR).addFirst("F");
        tablaAnSin.get(TP).get(POR).addFirst("TP");

        // Tabla[E][PARABRE] E -> T EP
        tablaAnSin.get(E).get(PARABRE).addFirst("T");
        tablaAnSin.get(E).get(PARABRE).addFirst("EP");

        // Tabla[T][PARABRE] T -> F TP
        tablaAnSin.get(T).get(PARABRE).addFirst("F");
        tablaAnSin.get(T).get(PARABRE).addFirst("TP");

        // Tabla[F][PARABRE] F -> PARABRE E PARCIERRA
        tablaAnSin.get(F).get(PARABRE).addFirst("PARABRE");
        tablaAnSin.get(F).get(PARABRE).addFirst("E");
        tablaAnSin.get(F).get(PARABRE).addFirst("PARCIERRA");

        // Tabla[EP][PARCIERRA] EP -> VACIA
        tablaAnSin.get(EP).get(PARCIERRA).addFirst("VACIA");

        // Tabla[TP][PARCIERRA] TP -> VACIA
        tablaAnSin.get(TP).get(PARCIERRA).addFirst("VACIA");

        // Tabla[EP][DELIMITADOR] EP -> VACIA
        tablaAnSin.get(EP).get(DELIMITADOR).addFirst("VACIA");

        // Tabla[TP][DELIMITADOR] TP -> VACIA
        tablaAnSin.get(TP).get(DELIMITADOR).addFirst("VACIA");
    }

    /**
     * @brief Calcula la siguiente producción de acuerdo con los símbolos en la pila y en la entrada.
     *
     * @param elemPila El símbolo en la cima de la pila.
     * @param elemEntrada El símbolo actual de la entrada.
     * @return ArrayList<String> La producción correspondiente a los símbolos dados.
     */
    public ArrayList<String> calcularSig(String elemPila, String elemEntrada) {
        int iNoTerminal, iTerminal;

        iNoTerminal = mapConstantes.get(elemPila);
        iTerminal = mapConstantes.get(elemEntrada);
        return tablaAnSin.get(iNoTerminal).get(iTerminal);
    }

    /**
     * @brief Verifica si un símbolo es terminal.
     *
     * @param simbolo El símbolo a verificar.
     * @return boolean `true` si el símbolo es terminal, `false` en caso contrario.
     */
    public boolean esTerminal(String simbolo) {
        return !noTerminales.contains(simbolo);
    }
}
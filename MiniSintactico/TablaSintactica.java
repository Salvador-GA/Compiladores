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
        tablaAnSin.get(E).get(ID).add(0, "T");
        tablaAnSin.get(E).get(ID).add(0, "EP");

        // Tabla[T][ID] T -> F TP
        tablaAnSin.get(T).get(ID).add(0, "F");
        tablaAnSin.get(T).get(ID).add(0, "TP");

        // Tabla[F][ID] F -> ID
        tablaAnSin.get(F).get(ID).add(0, "ID");

        // Tabla[EP][MAS] EP -> + T EP
        tablaAnSin.get(EP).get(MAS).add(0, "MAS");
        tablaAnSin.get(EP).get(MAS).add(0, "T");
        tablaAnSin.get(EP).get(MAS).add(0, "EP");

        // Tabla[TP][MAS] TP -> VACIA
        tablaAnSin.get(TP).get(MAS).add(0, "VACIA");

        // Tabla[TP][POR] TP -> * F TP
        tablaAnSin.get(TP).get(POR).add(0, "POR");
        tablaAnSin.get(TP).get(POR).add(0, "F");
        tablaAnSin.get(TP).get(POR).add(0, "TP");

        // Tabla[E][PARABRE] E -> T EP
        tablaAnSin.get(E).get(PARABRE).add(0, "T");
        tablaAnSin.get(E).get(PARABRE).add(0, "EP");

        // Tabla[T][PARABRE] T -> F TP
        tablaAnSin.get(T).get(PARABRE).add(0, "F");
        tablaAnSin.get(T).get(PARABRE).add(0, "TP");

        // Tabla[F][PARABRE] F -> PARABRE E PARCIERRA
        tablaAnSin.get(F).get(PARABRE).add(0, "PARABRE");
        tablaAnSin.get(F).get(PARABRE).add(0, "E");
        tablaAnSin.get(F).get(PARABRE).add(0, "PARCIERRA");

        // Tabla[EP][PARCIERRA] EP -> VACIA
        tablaAnSin.get(EP).get(PARCIERRA).add(0, "VACIA");

        // Tabla[TP][PARCIERRA] TP -> VACIA
        tablaAnSin.get(TP).get(PARCIERRA).add(0, "VACIA");

        // Tabla[EP][DELIMITADOR] EP -> VACIA
        tablaAnSin.get(EP).get(DELIMITADOR).add(0, "VACIA");

        // Tabla[TP][DELIMITADOR] TP -> VACIA
        tablaAnSin.get(TP).get(DELIMITADOR).add(0, "VACIA");
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

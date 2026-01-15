package clases;

import java.util.Arrays;
import java.util.Map;

public class Convertidor {

    public static final Map<String, Double> FACTORES_PESO = Map.of(
            "kg", 1.0,
            "lb", 0.453592,
            "oz", 0.0283495,
            "g", 0.001
    );

    public static final Map<String, Double> FACTORES_LONGITUD = Map.of(
            "m", 1.0,
            "km", 1000.0,
            "cm", 0.01,
            "in", 0.0254,
            "ft", 0.3048,
            "yd", 0.9144,
            "mi", 1609.34
    );

    public static final Map<String, Double> FACTORES_VOLUMEN = Map.of(
            "l", 1.0,
            "ml", 0.001,
            "gal", 3.78541

    );

    public static Medida convertir(Medida original, String unidadDestino) {
        Map<String, Double> tabla = obtenerTabla(original.getAbreviatura());

        if (tabla == null || !tabla.containsKey(unidadDestino)) {
            throw new IllegalArgumentException("Conversion no válida");
        }

        double valorBase = original.getValor() * tabla.get(original.getAbreviatura());
        double valorFinal = valorBase / tabla.get(unidadDestino);

        return new Medida(
                obtenerNombre(unidadDestino),
                unidadDestino,
                obtenerSistema(unidadDestino),
                valorFinal
        );
    }

    private static Map<String, Double> obtenerTabla(String unidad) {
        if (FACTORES_PESO.containsKey(unidad)) return FACTORES_PESO;
        if (FACTORES_LONGITUD.containsKey(unidad)) return FACTORES_LONGITUD;
        if (FACTORES_VOLUMEN.containsKey(unidad)) return FACTORES_VOLUMEN;
        return null;
    }

    public static String obtenerNombre(String abrev) {
        Map<String, String> nombres = Map.ofEntries(
                Map.entry("kg", "Kilogramo"),
                Map.entry("lb", "Libra"),
                Map.entry("g", "Gramo"),
                Map.entry("oz", "Onza"),
                Map.entry("m", "Metro"),
                Map.entry("km", "Kilometro"),
                Map.entry("cm", "Centimetro"),
                Map.entry("in", "Pulgada"),
                Map.entry("ft", "Pie"),
                Map.entry("yd", "Yarda"),
                Map.entry("mi", "Milla"),
                Map.entry("l", "Litro"),
                Map.entry("ml", "Mililitro"),
                Map.entry("gal", "Galon")

        );

        return nombres.getOrDefault(abrev, abrev);
    }

    public static String obtenerSistema(String abrev) {
        String[] anglosajona = {"lb", "oz", "in", "ft", "yd", "mi", "gal"};
        return Arrays.asList(anglosajona).contains(abrev) ? "Anglosajon" : "SIU";
    }
}

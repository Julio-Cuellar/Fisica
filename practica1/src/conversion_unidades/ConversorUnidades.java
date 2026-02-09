package conversion_unidades;

import java.util.Map;

public class ConversorUnidades {

    private final Map<String, Double> factorConversionLongitud = Map.ofEntries(
            Map.entry("m", 1.0),
            Map.entry("km", 1000.0),
            Map.entry("cm", 0.01),
            Map.entry("mm", 0.001),
            Map.entry("mi", 1609.34),
            Map.entry("ft", 0.3048),
            Map.entry("in", 0.0254),
            Map.entry("yd", 0.9144)
    );

    private final Map<String, Double> factorConversionTiempo = Map.ofEntries(
            Map.entry("s", 1.0),
            Map.entry("ms", 0.001),
            Map.entry("min", 60.0),
            Map.entry("h", 3600.0),
            Map.entry("d", 86400.0),
            Map.entry("wk", 604800.0)
    );

    private final Map<String, Double> factorConversionMasa = Map.ofEntries(
            Map.entry("kg", 1.0),
            Map.entry("t", 1000.0),
            Map.entry("g", 0.001),
            Map.entry("mg", 0.000001),
            Map.entry("lb", 0.45359237),
            Map.entry("ton", 907.185),
            Map.entry("oz", 0.0283495)
    );

    private final Map<String, Double> factorConversionVolumen = Map.ofEntries(
            Map.entry("l", 1.0),
            Map.entry("m³", 1000.0),
            Map.entry("ml", 0.001),
            Map.entry("gal", 3.78541),
            Map.entry("qt", 0.946353),
            Map.entry("pt", 0.473176),
            Map.entry("cup", 0.236588),
            Map.entry("floz", 0.0295735)
    );

    public ConversorUnidades() {
    }

    public Double convertirLongitud(Double valor, String unidadOrigen, String unidadDestino) {
        return convertir(valor, unidadOrigen, unidadDestino, factorConversionLongitud, "longitud");
    }

    public Double convertirTiempo(Double valor, String unidadOrigen, String unidadDestino) {
        return convertir(valor, unidadOrigen, unidadDestino, factorConversionTiempo, "tiempo");
    }

    public Double convertirMasa(Double valor, String unidadOrigen, String unidadDestino) {
        return convertir(valor, unidadOrigen, unidadDestino, factorConversionMasa, "masa");
    }

    public Double convertirVolumen(Double valor, String unidadOrigen, String unidadDestino) {
        return convertir(valor, unidadOrigen, unidadDestino, factorConversionVolumen, "volumen");
    }

    public Double convertirVelocidad(Double valor, String unidadOrigen, String unidadDestino) {

        if (valor == null || Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("El valor debe ser un número válido");
        }

        String[] partesOrigen = unidadOrigen.toLowerCase().trim().split("/");
        String[] partesDestino = unidadDestino.toLowerCase().trim().split("/");

        if (partesOrigen.length != 2 || partesDestino.length != 2) {
            throw new IllegalArgumentException(
                    "Las unidades de velocidad deben estar en formato 'longitud/tiempo' (ej: km/h, m/s)"
            );
        }

        String longitudOrigen = partesOrigen[0];
        String tiempoOrigen = partesOrigen[1];
        String longitudDestino = partesDestino[0];
        String tiempoDestino = partesDestino[1];

        Double longitudConvertida = convertirLongitud(valor, longitudOrigen, longitudDestino);
        Double velocidadFinal = convertirTiempo(longitudConvertida, tiempoDestino, tiempoOrigen);

        return velocidadFinal;
    }

    public Double convertirAceleracion(Double valor, String unidadOrigen, String unidadDestino) {

        if (valor == null || Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("El valor debe ser un número válido");
        }

        String[] partesOrigen = unidadOrigen.toLowerCase().trim().split("/");
        String[] partesDestino = unidadDestino.toLowerCase().trim().split("/");

        if (partesOrigen.length != 2 || partesDestino.length != 2) {
            throw new IllegalArgumentException(
                    "Las unidades de aceleración deben estar en formato 'longitud/tiempo²' (ej: km/h², m/s²)"
            );
        }

        String longitudOrigen = partesOrigen[0];
        String tiempoOrigen = partesOrigen[1].replace("²", "");
        String longitudDestino = partesDestino[0];
        String tiempoDestino = partesDestino[1].replace("²", "");

        Double longitudConvertida = convertirLongitud(valor, longitudOrigen, longitudDestino);

        Double factorTiempo = obtenerFactorConversion(tiempoOrigen, tiempoDestino, factorConversionTiempo);
        Double aceleracionFinal = longitudConvertida * factorTiempo * factorTiempo;

        return aceleracionFinal;
    }

    private Double convertir(Double valor, String unidadOrigen, String unidadDestino,
                             Map<String, Double> factores, String tipoMagnitud) {

        if (valor == null || Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("El valor debe ser un número válido");
        }

        String origen = unidadOrigen.toLowerCase().trim();
        String destino = unidadDestino.toLowerCase().trim();

        Double factorOrigen = factores.get(origen);
        Double factorDestino = factores.get(destino);

        if (factorOrigen == null || factorDestino == null) {
            throw new IllegalArgumentException(
                    "Una de las unidades de " + tipoMagnitud + " no es válida. " +
                            "Unidades disponibles: " + factores.keySet()
            );
        }

        return (valor * factorOrigen) / factorDestino;
    }

    private Double obtenerFactorConversion(String origen, String destino, Map<String, Double> factores) {
        Double factorOrigen = factores.get(origen.toLowerCase());
        Double factorDestino = factores.get(destino.toLowerCase());

        if (factorOrigen == null || factorDestino == null) {
            throw new IllegalArgumentException("Unidad de tiempo no válida");
        }

        return factorDestino / factorOrigen;
    }
}

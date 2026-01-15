import clases.Convertidor;
import clases.Medida;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   CONVERSOR DE UNIDADES            ║");
        System.out.println("╚════════════════════════════════════╝\n");

        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerEnteroSeguro();

            switch (opcion) {
                case 1:
                    convertirPeso();
                    break;
                case 2:
                    convertirLongitud();
                    break;
                case 3:
                    convertirVolumen();
                    break;
                case 4:
                    continuar = false;
                    System.out.println("\n¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Intente nuevamente.\n");
            }
        }

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("═══════════════════════════════════");
        System.out.println("Seleccione el tipo de conversión:");
        System.out.println("1. Peso (kg, lb, g, oz)");
        System.out.println("2. Longitud (m, km, cm, in, ft, yd, mi)");
        System.out.println("3. Volumen (l, ml, gal, pt)");
        System.out.println("4. Salir");
        System.out.println("═══════════════════════════════════");
        System.out.print("Opción: ");
    }

    private static void convertirPeso() {
        System.out.println("\n--- CONVERSIÓN DE PESO ---");
        System.out.println("Unidades disponibles: \n 1. kg \n 2. lb\n 3. g\n 4. oz");
        realizarConversion();
    }

    private static void convertirLongitud() {
        System.out.println("\n--- CONVERSIÓN DE LONGITUD ---");
        System.out.println("Unidades disponibles: \n 1. m\n 2. km\n 3. cm\n 4. in\n 5. ft \n 6. yd\n 7. mi");
        realizarConversion();
    }

    private static void convertirVolumen() {
        System.out.println("\n--- CONVERSIÓN DE VOLUMEN ---");
        System.out.println("Unidades disponibles: \n 1. l\n 2. ml\n 3. gal\n 4. pt");
        realizarConversion();
    }

    private static void realizarConversion() {
        System.out.println("\n¿Desea convertir múltiples valores? (s/n): ");
        String respuesta = scanner.next().toLowerCase();
        scanner.nextLine();

        if (respuesta.equals("s")) {
            realizarConversionMultiple();
        } else {
            realizarConversionSimple();
        }
    }

    private static void realizarConversionSimple() {
        System.out.print("\nIngrese el valor a convertir: ");
        double valor = leerDoubleSeguro();

        System.out.print("Unidad de origen: ");
        String unidadOrigen = scanner.next().toLowerCase();

        System.out.print("Unidad de destino: ");
        String unidadDestino = scanner.next().toLowerCase();

        procesarYMostrarConversion(valor, unidadOrigen, unidadDestino);
    }

    private static void realizarConversionMultiple() {
        List<Double> valores = new ArrayList<>();

        System.out.println("\n--- INGRESO DE VALORES ---");
        System.out.println("Ingrese los valores a convertir (escriba 'fin' para terminar):");

        while (true) {
            System.out.print("Valor " + (valores.size() + 1) + ": ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("fin")) {
                break;
            }

            try {
                double valor = Double.parseDouble(input);
                valores.add(valor);
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada no válida. Ingrese un número o 'fin' para terminar.");
            }
        }

        if (valores.isEmpty()) {
            System.out.println("❌ No se ingresaron valores para convertir.\n");
            return;
        }

        System.out.print("\nUnidad de origen: ");
        String unidadOrigen = scanner.next().toLowerCase();

        System.out.print("Unidad de destino: ");
        String unidadDestino = scanner.next().toLowerCase();

        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║         RESULTADOS MÚLTIPLES            ║");
        System.out.println("╚═════════════════════════════════════════╝");

        for (int i = 0; i < valores.size(); i++) {
            System.out.println("\n--- Conversión " + (i + 1) + " ---");
            procesarYMostrarConversion(valores.get(i), unidadOrigen, unidadDestino);
        }
    }

    private static void procesarYMostrarConversion(double valor, String unidadOrigen, String unidadDestino) {
        try {
            Medida medidaOriginal = new Medida(
                    Convertidor.obtenerNombre(unidadOrigen),
                    unidadOrigen,
                    Convertidor.obtenerSistema(unidadOrigen),
                    valor
            );

            Medida medidaConvertida = Convertidor.convertir(medidaOriginal, unidadDestino);

            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│         RESULTADO               │");
            System.out.println("├─────────────────────────────────┤");
            System.out.printf("│ Original:   %-20s│%n", medidaOriginal);
            System.out.printf("│ Convertido: %-20s│%n", medidaConvertida);
            System.out.printf("│ Sistema:    %-20s│%n", medidaConvertida.getSistema());
            System.out.println("└─────────────────────────────────┘\n");

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
            System.out.println("Verifique que las unidades sean compatibles.\n");
        } catch (Exception e) {
            System.out.println("\n❌ Error inesperado: " + e.getMessage() + "\n");
        }
    }

    private static int leerEnteroSeguro() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("❌ Entrada no válida. Ingrese un número: ");
                scanner.nextLine();
            }
        }
    }

    private static double leerDoubleSeguro() {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("❌ Entrada no válida. Ingrese un número: ");
                scanner.nextLine();
            }
        }
    }
}

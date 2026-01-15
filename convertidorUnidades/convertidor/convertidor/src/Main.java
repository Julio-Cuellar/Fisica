import clases.Convertidor;
import clases.Medida;

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
            int opcion = scanner.nextInt();

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
                    System.out.println("\nOpción no válida. Intente nuevamente.\n");
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
        System.out.println("Unidades disponibles: " + "\n 1. kg " + "\n 2. lb" + "\n 3. g" + "\n 4. oz");
        realizarConversion();
    }

    private static void convertirLongitud() {
        System.out.println("\n--- CONVERSIÓN DE LONGITUD ---");
        System.out.println("Unidades disponibles: "+ "\n 1. m" + "\n 2. km" + "\n 3. cm" + "\n 4. in" + "\n 5. ft " + "\n 6. yd" + "\n 7. mi");
        realizarConversion();
    }

    private static void convertirVolumen() {
        System.out.println("\n--- CONVERSIÓN DE VOLUMEN ---");
        System.out.println("Unidades disponibles: " + "\n 1. l" + "\n 2. ml" + "\n 3. gal" + "\n 4. pt");
        realizarConversion();
    }

    private static void realizarConversion() {
        System.out.print("\nIngrese el valor a convertir: ");
        double valor = scanner.nextDouble();

        System.out.print("Unidad de origen: ");
        String unidadOrigen = scanner.next().toLowerCase();

        System.out.print("Unidad de destino: ");
        String unidadDestino = scanner.next().toLowerCase();

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
            System.out.println("\n❌ Error inesperado. Intente nuevamente.\n");
        }
    }
}
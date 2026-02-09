import conversion_unidades.ConversorUnidades;
import punto.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ConversorUnidades conversor = new ConversorUnidades();
    private static final CalculadoraVectores calcVectores = new CalculadoraVectores();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===========================================");
            System.out.println("       CALCULADORA FISICA");
            System.out.println("===========================================");
            System.out.println("1. Conversor de Unidades");
            System.out.println("2. Calculadora de Vectores");
            System.out.println("3. Operaciones con Puntos");
            System.out.println("0. Salir");
            System.out.println("===========================================");
            System.out.print("Elige una opción: ");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Debes ingresar un número.");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> menuConversor();
                case 2 -> menuVectores();
                case 3 -> menuPuntos();
                case 0 -> System.out.println("\nCerrando sistema... ¡Éxito en la BUAP!");
                default -> System.out.println("\n[ERROR] Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuConversor() {
        System.out.println("\n========================================");
        System.out.println("       CONVERSOR DE UNIDADES");
        System.out.println("========================================");
        System.out.println("1. Longitud");
        System.out.println("2. Masa");
        System.out.println("3. Tiempo");
        System.out.println("4. Volumen");
        System.out.println("5. Velocidad");
        System.out.println("6. Aceleración");
        System.out.println("0. Volver al menú principal");
        System.out.println("========================================");
        System.out.print("Selecciona magnitud: ");

        int tipo;
        try {
            tipo = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Debes ingresar un número.");
            sc.nextLine();
            return;
        }

        if (tipo == 0) return;

        switch (tipo) {
            case 1 -> submenuLongitud();
            case 2 -> submenuMasa();
            case 3 -> submenuTiempo();
            case 4 -> submenuVolumen();
            case 5 -> submenuVelocidad();
            case 6 -> submenuAceleracion();
            default -> System.out.println("\n[ERROR] Magnitud no válida.");
        }
    }

    private static void submenuLongitud() {
        System.out.println("\n--- CONVERSIÓN DE LONGITUD ---");
        System.out.println("Unidades disponibles:");
        System.out.println("  Sistema Internacional: m, km, cm, mm");
        System.out.println("  Sistema Anglosajón: mi, ft, in, yd");
        System.out.println("-------------------------------");

        try {
            System.out.print("Valor a convertir: ");
            double valor = sc.nextDouble();
            System.out.print("Unidad origen (ej: km): ");
            String origen = sc.next();
            System.out.print("Unidad destino (ej: mi): ");
            String destino = sc.next();

            Double resultado = conversor.convertirLongitud(valor, origen, destino);
            System.out.println("\n✓ Resultado: " + valor + " " + origen + " = " +
                    String.format("%.4f", resultado) + " " + destino);
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] El valor debe ser un número válido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void submenuMasa() {
        System.out.println("\n--- CONVERSIÓN DE MASA ---");
        System.out.println("Unidades disponibles:");
        System.out.println("  Sistema Internacional: kg, t, g, mg");
        System.out.println("  Sistema Anglosajón: lb, ton, oz");
        System.out.println("-------------------------------");

        try {
            System.out.print("Valor a convertir: ");
            double valor = sc.nextDouble();
            System.out.print("Unidad origen (ej: kg): ");
            String origen = sc.next();
            System.out.print("Unidad destino (ej: lb): ");
            String destino = sc.next();

            Double resultado = conversor.convertirMasa(valor, origen, destino);
            System.out.println("\n✓ Resultado: " + valor + " " + origen + " = " +
                    String.format("%.4f", resultado) + " " + destino);
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] El valor debe ser un número válido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void submenuTiempo() {
        System.out.println("\n--- CONVERSIÓN DE TIEMPO ---");
        System.out.println("Unidades disponibles:");
        System.out.println("  s, ms, min, h, d, wk");
        System.out.println("-------------------------------");

        try {
            System.out.print("Valor a convertir: ");
            double valor = sc.nextDouble();
            System.out.print("Unidad origen (ej: h): ");
            String origen = sc.next();
            System.out.print("Unidad destino (ej: min): ");
            String destino = sc.next();

            Double resultado = conversor.convertirTiempo(valor, origen, destino);
            System.out.println("\n✓ Resultado: " + valor + " " + origen + " = " +
                    String.format("%.4f", resultado) + " " + destino);
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] El valor debe ser un número válido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void submenuVolumen() {
        System.out.println("\n--- CONVERSIÓN DE VOLUMEN ---");
        System.out.println("Unidades disponibles:");
        System.out.println("  Sistema Internacional: l, m³, ml");
        System.out.println("  Sistema Anglosajón: gal, qt, pt, cup, floz");
        System.out.println("-------------------------------");

        try {
            System.out.print("Valor a convertir: ");
            double valor = sc.nextDouble();
            System.out.print("Unidad origen (ej: l): ");
            String origen = sc.next();
            System.out.print("Unidad destino (ej: gal): ");
            String destino = sc.next();

            Double resultado = conversor.convertirVolumen(valor, origen, destino);
            System.out.println("\n✓ Resultado: " + valor + " " + origen + " = " +
                    String.format("%.4f", resultado) + " " + destino);
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] El valor debe ser un número válido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void submenuVelocidad() {
        System.out.println("\n--- CONVERSIÓN DE VELOCIDAD ---");
        System.out.println("Unidades disponibles (formato: longitud/tiempo):");
        System.out.println("  Ejemplos comunes: km/h, m/s, mi/h, ft/s, cm/s");
        System.out.println("  Longitud: m, km, cm, mm, mi, ft, in, yd");
        System.out.println("  Tiempo: s, min, h");
        System.out.println("-------------------------------");

        try {
            System.out.print("Valor a convertir: ");
            double valor = sc.nextDouble();
            System.out.print("Unidad origen (ej: km/h): ");
            String origen = sc.next();
            System.out.print("Unidad destino (ej: m/s): ");
            String destino = sc.next();

            Double resultado = conversor.convertirVelocidad(valor, origen, destino);
            System.out.println("\n✓ Resultado: " + valor + " " + origen + " = " +
                    String.format("%.4f", resultado) + " " + destino);
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] El valor debe ser un número válido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void submenuAceleracion() {
        System.out.println("\n--- CONVERSIÓN DE ACELERACIÓN ---");
        System.out.println("Unidades disponibles (formato: longitud/tiempo²):");
        System.out.println("  Ejemplos comunes: m/s², km/h², ft/s², mi/h²");
        System.out.println("  Longitud: m, km, cm, mm, mi, ft, in, yd");
        System.out.println("  Tiempo²: s², min², h²");
        System.out.println("-------------------------------");

        try {
            System.out.print("Valor a convertir: ");
            double valor = sc.nextDouble();
            System.out.print("Unidad origen (ej: m/s²): ");
            String origen = sc.next();
            System.out.print("Unidad destino (ej: km/h²): ");
            String destino = sc.next();

            Double resultado = conversor.convertirAceleracion(valor, origen, destino);
            System.out.println("\n✓ Resultado: " + valor + " " + origen + " = " +
                    String.format("%.4f", resultado) + " " + destino);
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] El valor debe ser un número válido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void menuVectores() {
        System.out.println("\n========================================");
        System.out.println("       MÓDULO DE VECTORES R²");
        System.out.println("========================================");

        try {
            System.out.println("Ingresa coordenadas del Punto A:");
            System.out.print("  x: ");
            double x1 = sc.nextDouble();
            System.out.print("  y: ");
            double y1 = sc.nextDouble();

            System.out.println("\nIngresa coordenadas del Punto B:");
            System.out.print("  x: ");
            double x2 = sc.nextDouble();
            System.out.print("  y: ");
            double y2 = sc.nextDouble();

            Punto pA = new Punto(x1, y1);
            Punto pB = new Punto(x2, y2);

            System.out.println("\n========================================");
            System.out.println("       INFORMACIÓN DE PUNTOS");
            System.out.println("========================================");
            System.out.println("Punto A: " + pA.toString());
            System.out.println("Punto B: " + pB.toString());
            System.out.println("========================================");

            VectorR2 vector = calcVectores.calculcarPosicionVector(pA, pB);

            System.out.println("\n========================================");
            System.out.println("       INFORMACIÓN DEL VECTOR");
            System.out.println("========================================");
            calcVectores.mostrarInfoVector(vector);
            System.out.println("========================================");
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Las coordenadas deben ser números válidos.");
            sc.nextLine();
        }
    }

    private static void menuPuntos() {
        System.out.println("\n========================================");
        System.out.println("       MÓDULO DE PUNTOS");
        System.out.println("========================================");
        System.out.println("1. Calcular distancia entre dos puntos");
        System.out.println("2. Ver coordenadas de un punto");
        System.out.println("0. Volver");
        System.out.println("========================================");
        System.out.print("Opción: ");

        int op;
        try {
            op = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Debes ingresar un número.");
            sc.nextLine();
            return;
        }

        try {
            switch (op) {
                case 1 -> {
                    System.out.println("\n--- CALCULAR DISTANCIA ---");
                    System.out.println("Punto 1:");
                    System.out.print("  x: ");
                    double x1 = sc.nextDouble();
                    System.out.print("  y: ");
                    double y1 = sc.nextDouble();

                    System.out.println("Punto 2:");
                    System.out.print("  x: ");
                    double x2 = sc.nextDouble();
                    System.out.print("  y: ");
                    double y2 = sc.nextDouble();

                    Punto p1 = new Punto(x1, y1);
                    Punto p2 = new Punto(x2, y2);
                    System.out.println("\n✓ La distancia entre " + p1 + " y " + p2 +
                            " es: " + String.format("%.4f", p1.distanciaA(p2)) + " unidades");
                }
                case 2 -> {
                    System.out.println("\n--- CREAR PUNTO ---");
                    System.out.print("  x: ");
                    double x = sc.nextDouble();
                    System.out.print("  y: ");
                    double y = sc.nextDouble();
                    Punto p = new Punto(x, y);
                    System.out.println("\n✓ Punto creado: " + p.toString());
                }
                case 0 -> System.out.println("\nVolviendo al menú principal...");
                default -> System.out.println("\n[ERROR] Opción no válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\n[ERROR] Las coordenadas deben ser números válidos.");
            sc.nextLine();
        }
    }
}

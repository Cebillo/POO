package edu.unl.cc.view;

import edu.unl.cc.domain.planilla;

import java.util.Locale;
import java.util.Scanner;

public class Planilla {


    private static final Scanner scanner = new Scanner(System.in);
    private static planilla planilla;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = (int) leerNumero("Elija una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> registrarPlanilla();
                case 2 -> actualizarLecturaActual();
                case 3 -> mostrarCalculos();
                case 4 -> mostrarPlanilla();
                case 5 -> ejecutarCasosDePrueba();
                case 6 -> System.out.println("Programa finalizado. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 6);

        scanner.close();
    }

    //Menú
    private static void mostrarMenu() {
        System.out.println("""
                ===== PLANILLA DE CONSUMO ELÉCTRICO =====
                1. Registrar planilla
                2. Actualizar lectura actual
                3. Ver consumo, subtotal, IVA y total
                4. Ver planilla completa
                5. Ejecutar casos de prueba
                6. Salir
                =========================================""");
    }

    // Opciones
    private static void registrarPlanilla() {
        System.out.print("Número de medidor: ");
        String medidor = scanner.nextLine();
        System.out.print("Nombre del titular: ");
        String titular = scanner.nextLine();
        double anterior = leerNumero("Lectura anterior (kWh): ");
        double actual = leerNumero("Lectura actual (kWh): ");

        try {
            planilla = new planilla(medidor, titular, anterior, actual);
            System.out.println("Planilla registrada correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }
    }

    private static void actualizarLecturaActual() {
        if (!hayPlanilla()) {
            return;
        }
        double nueva = leerNumero("Nueva lectura actual (kWh): ");
        try {
            planilla.setLecturaActual(nueva);
            System.out.println("Lectura actual actualizada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }
    }

    private static void mostrarCalculos() {
        if (!hayPlanilla()) {
            return;
        }
        System.out.printf(Locale.US, """
                Consumo  : %.0f kWh
                Subtotal : $%.2f
                IVA (15%%): $%.2f
                Total    : $%.2f%n""",
                planilla.calcularConsumo(), planilla.calcularSubtotal(),
                planilla.calcularIva(), planilla.calcularTotal());
    }

    private static void mostrarPlanilla() {
        if (hayPlanilla()) {
            System.out.println(planilla);
        }
    }

    private static void ejecutarCasosDePrueba() {
        probarPlanilla("M-001", "Ana Torres", 1200, 1350);    // total $20.13
        probarPlanilla("M-002", "Luis Pérez", 1000, 1300);    // total $51.75
        probarPlanilla("M-003", "María Gómez", 1200, 1150);   // mensaje de validación
    }

    private static void probarPlanilla(String medidor, String titular,
                                       double anterior, double actual) {
        try {
            planilla prueba = new planilla(medidor, titular, anterior, actual);
            System.out.println(prueba);
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }
        System.out.println();
    }

    // Métodos auxiliares
    private static boolean hayPlanilla() {
        if (planilla == null) {
            System.out.println("Primero debe registrar una planilla (opción 1).");
            return false;
        }
        return true;
    }


    private static double leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Escriba un número.");
            }
        }
    }
}
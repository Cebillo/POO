package edu.unl.cc.view;

import edu.unl.cc.domain.CuentaBancaria;
import java.util.Scanner;

public class BancoExecutor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancaria cuenta = null;
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del teclado

            switch (opcion) {
                case 1:
                    cuenta = ejecutarCrearCuenta(scanner);
                    break;

                case 2:
                    if (validarCuenta(cuenta)) {
                        System.out.println("\n1. Depositar");
                        System.out.println("2. Retirar");
                        System.out.print("Elija una opción: ");
                        int subOpcion = scanner.nextInt();

                        if (subOpcion == 1) {
                            ejecutarDepositar(scanner, cuenta);
                        } else if (subOpcion == 2) {
                            ejecutarRetirar(scanner, cuenta);
                        } else {
                            System.out.println("Opción de transacción inválida.");
                        }
                    }
                    break;

                case 3:
                    if (validarCuenta(cuenta)) {
                        ejecutarConsultar(cuenta);
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 4);

        scanner.close();
    }

    // --- MÉTODOS AUXILIARES DE LA VISTA (EJECUTOR) ---

    private static void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("      MENÚ - BANCO NACIONAL       ");
        System.out.println("==================================");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar o Retirar dinero");
        System.out.println("3. Consultar saldo e historial");
        System.out.println("4. Salir");
        System.out.print("Elija una opción: ");
    }

    private static CuentaBancaria ejecutarCrearCuenta(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numero = scanner.nextLine();

        System.out.print("Ingrese el nombre del titular: ");
        String titular = scanner.nextLine();

        System.out.print("Ingrese el saldo inicial: ");
        double saldoInicial = scanner.nextDouble();

        System.out.println("¡Cuenta creada exitosamente!");
        return new CuentaBancaria(numero, titular, saldoInicial);
    }

    private static void ejecutarDepositar(Scanner scanner, CuentaBancaria cuenta) {
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();

        if (monto > 0) {
            cuenta.depositar(monto);
            System.out.println("Depósito exitoso. Saldo actual: " + cuenta.getSaldo());
        } else {
            System.out.println("Error: El monto a depositar debe ser mayor a cero.");
        }
    }

    private static void ejecutarRetirar(Scanner scanner, CuentaBancaria cuenta) {
        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();

        boolean exito = cuenta.retirar(monto);
        if (exito) {
            System.out.println("Retiro exitoso. Saldo actual: " + cuenta.getSaldo());
        } else {
            System.out.println("Error: Fondos insuficientes o monto no válido.");
        }
    }

    private static void ejecutarConsultar(CuentaBancaria cuenta) {
        System.out.println("\n--- DATOS DE LA CUENTA ---");
        System.out.println("Número de Cuenta: " + cuenta.getNumeroCuenta());
        System.out.println("Titular: " + cuenta.getTitular());
        System.out.println("Saldo Actual: " + cuenta.getSaldo());

        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        if (cuenta.getHistorial().isEmpty()) {
            System.out.println("No hay movimientos registrados.");
        } else {
            for (String movimiento : cuenta.getHistorial()) {
                System.out.println(movimiento);
            }
        }
    }

    private static boolean validarCuenta(CuentaBancaria cuenta) {
        if (cuenta == null) {
            System.out.println("Error: Primero debe crear una cuenta (Opción 1).");
            return false;
        }
        return true;
    }
}
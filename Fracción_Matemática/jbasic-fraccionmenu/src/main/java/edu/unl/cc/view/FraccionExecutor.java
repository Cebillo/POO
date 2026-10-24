package edu.unl.cc.view;

/**
 * Fraccion problem
 * @author AlvaroYhalenV.
 */
import edu.unl.cc.domain.Fraccion;
import java.util.Scanner;

public class FraccionExecutor {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Fraccion fraccionActual = null;
        int opcion = 0;

        do {
            System.out.println("\n================ MENÚ DE FRACCIONES ================");
            System.out.println("1. Ingresar el numerador y denominador de la Fracción.");
            System.out.println("2. Mostrar su fracción simplificada.");
            System.out.println("3. Mostrar su valor aproximada.");
            System.out.println("4. Operación a Realizar ( + , - , * , / ).");
            System.out.println("5. Salir.");
            System.out.print("Elija una opción: ");

            try {
                opcion = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido.");
                teclado.next(); // Limpiar el buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    while (true) {
                        try {
                            System.out.print("Ingrese el numerador: ");
                            int num = teclado.nextInt();
                            System.out.print("Ingrese el denominador: ");
                            int den = teclado.nextInt();
                            fraccionActual = new Fraccion(num, den);
                            System.out.println("✅ Fracción guardada con éxito: " + fraccionActual);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠️ " + e.getMessage() + " Intente nuevamente.\n");
                        }
                    }
                    break;

                case 2:
                    if (fraccionActual == null) {
                        System.out.println("⚠️ Primero debe ingresar una fracción (Opción 1).");
                    } else {
                        System.out.println("🔹 Fracción simplificada: " + fraccionActual);
                    }
                    break;

                case 3:
                    if (fraccionActual == null) {
                        System.out.println("⚠️ Primero debe ingresar una fracción (Opción 1).");
                    } else {
                        System.out.println("🔹 Valor aproximado (Decimal): " + fraccionActual.obtenerValorDecimal());
                    }
                    break;

                case 4:
                    if (fraccionActual == null) {
                        System.out.println("⚠️ Primero debe ingresar una fracción base (Opción 1).");
                        break;
                    }

                    System.out.print("Ingrese el operador para la operación (+, -, *, /): ");
                    char operador = teclado.next().charAt(0);

                    Fraccion otraFraccion = null;
                    while (true) {
                        try {
                            System.out.print("Ingrese el numerador de la segunda fracción: ");
                            int num2 = teclado.nextInt();
                            System.out.print("Ingrese el denominador de la segunda fracción: ");
                            int den2 = teclado.nextInt();
                            otraFraccion = new Fraccion(num2, den2);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠️ " + e.getMessage() + " Intente nuevamente.\n");
                        }
                    }

                    Fraccion resultado = null;
                    try {
                        switch (operador) {
                            case '+':
                                resultado = fraccionActual.sumar(otraFraccion);
                                break;
                            case '-':
                                resultado = fraccionActual.restar(otraFraccion);
                                break;
                            case '*':
                                resultado = fraccionActual.multiplicar(otraFraccion);
                                break;
                            case '/':
                                resultado = fraccionActual.dividir(otraFraccion);
                                break;
                            default:
                                System.out.println("❌ Operador no válido.");
                                continue;
                        }

                        if (resultado != null) {
                            System.out.println("\n--- RESULTADO DE LA OPERACIÓN ---");
                            System.out.println("Notación de fracción: " + resultado);
                            System.out.println("Punto flotante: " + resultado.obtenerValorDecimal());
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️ Error en la operación: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("¡Saliendo del programa. Hasta luego!");
                    break;

                default:
                    System.out.println("❌ Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);

        teclado.close();
    }
}
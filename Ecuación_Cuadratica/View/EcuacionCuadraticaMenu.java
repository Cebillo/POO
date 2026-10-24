package edu.unl.cc.ape.view;

/**
 * Ecuación cuadrática: ax^2 + bx + c = 0
 * @author MatiasSantiagoCalvaG.
 */

import edu.unl.cc.ape.domain.EcuacionCuadratica;
import java.util.Locale;

public class EcuacionCuadraticaMenu {

    public static void main(String[] args) {
        EcuacionCuadratica ecuacion = null;
        int opcion;

        do {
            System.out.println("\n===== ECUACIÓN CUADRÁTICA ax² + bx + c = 0 =====");
            System.out.println("1. Ingresar coeficientes a, b, c");
            System.out.println("2. Calcular y mostrar las raíces");
            System.out.println("3. Salir");
            opcion = DatosEntrada.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    double a = DatosEntrada.leerDouble("a: ");
                    double b = DatosEntrada.leerDouble("b: ");
                    double c = DatosEntrada.leerDouble("c: ");
                    try {
                        ecuacion = new EcuacionCuadratica(a, b, c);
                        System.out.println("Ecuación ingresada: " + ecuacion);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (ecuacion == null) {
                        System.out.println("Primero debe ingresar los coeficientes (opción 1).");
                    } else {
                        System.out.println("Ecuación: " + ecuacion);
                        System.out.printf(Locale.US, "Discriminante: %.4f%n",
                                ecuacion.calcularDiscriminante());
                        System.out.println("Tipo: " + ecuacion.tipoRaices());
                        System.out.println("Raíces: " + ecuacion.calcularRaices());
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número del 1 al 3.");
            }
        } while (opcion != 3);
    }
}


package edu.unl.cc.ape.view;

/**
 * Ecuación cuadrática: ax^2 + bx + c = 0
 * @author MatiasSantiagoCalvaG.
 */

import java.util.Scanner;

public class DatosEntrada {

    private static final Scanner SC = new Scanner(System.in);

    private DatosEntrada() {
    }

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SC.hasNextLine() ? SC.nextLine().trim() : "";
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: ingrese un número entero.");
            }
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje).replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (!Double.isNaN(valor) && !Double.isInfinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException e) {

            }
            System.out.println("Entrada inválida: ingrese un número real.");
        }
    }
}
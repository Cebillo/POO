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
        Fraccion f1 = null;
        Fraccion f2 = null;

        while (true) {
            try {
                System.out.println("Ingrese el numerador de la fraccion 1:  ");
                int numerador1 = teclado.nextInt();
                System.out.println("Ingrese el denominador de la fraccion 1:  ");
                int denominador1 = teclado.nextInt();
                f1 = new Fraccion(numerador1, denominador1);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("¡Alerta capturada con éxito!: " + e.getMessage() + "\nPor favor, intente ingresar la fracción 1 nuevamente.\n");
            }
        }

        while (true) {
            try {
                System.out.println("Ingrese el numerador de la fraccion 2:  ");
                int numerador2 = teclado.nextInt();
                System.out.println("Ingrese el denominador de la fraccion 2:  ");
                int denominador2 = teclado.nextInt();
                f2 = new Fraccion(numerador2, denominador2);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("¡Alerta capturada con éxito!: " + e.getMessage() + "\nPor favor, intente ingresar la fracción 2 nuevamente.\n");
            }
        }

        try {
            System.out.println("El valor decimal de la fraccion 1 es:" + f1.obtenerValorDecimal());
            System.out.println("El valor decimal de la fraccion 2 es:" + f2.obtenerValorDecimal());

            System.out.println("La fraccion inversa de la fraccion 1 es:" + f1.inversa());
            System.out.println("La fraccion inversa de la fraccion 2 es:" + f2.inversa());

            Fraccion resultadoSuma = f1.sumar(f2);
            System.out.println("Suma: " + f1 + " + " + f2 + " = " + resultadoSuma);

            Fraccion resultadoResta = f1.restar(f2);
            System.out.println("Resta: " + f1 + " - " + f2 + " = " + resultadoResta);

            Fraccion resultadoMultiplicacion = f1.multiplicar(f2);
            System.out.println("Multiplicación: " + f1 + " * " + f2 + " = " + resultadoMultiplicacion);

            Fraccion resultadoDivision = f1.dividir(f2);
            System.out.println("Division:" + f1 + "/" + f2 + "=" + resultadoDivision);

        } catch (IllegalArgumentException e) {
            System.out.println("Alerta en las operaciones: " + e.getMessage());
        }
    }
}
package edu.unl.cc.ape.domain;

/**
 * Ecuación cuadrática: ax^2 + bx + c = 0
 * @author MatiasSantiagoCalvaG.
 */

import java.util.Locale;

public class EcuacionCuadratica {

    private double a;
    private double b;
    private double c;

    public EcuacionCuadratica(double a, double b, double c) {
        setA(a);
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        if (a == 0) {
            throw new IllegalArgumentException(
                    "Error: el coeficiente 'a' no puede ser cero (no sería una ecuación cuadrática).");
        }
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double calcularDiscriminante() {
        return b * b - 4 * a * c;
    }

    public String tipoRaices() {
        double d = calcularDiscriminante();
        if (d > 0) {
            return "Dos raíces reales distintas";
        } else if (d == 0) {
            return "Una raíz real doble";
        }
        return "Dos raíces complejas conjugadas";
    }

    public String calcularRaices() {
        double d = calcularDiscriminante();
        double denominador = 2 * a;
        double parteReal = -b / denominador + 0.0;   // "+ 0.0" evita mostrar -0.0

        if (d > 0) {
            double raiz = Math.sqrt(d);
            double r1 = (-b + raiz) / denominador;
            double r2 = (-b - raiz) / denominador;
            return String.format(Locale.US, "x1 = %.4f, x2 = %.4f",
                    Math.min(r1, r2), Math.max(r1, r2));
        } else if (d == 0) {
            return String.format(Locale.US, "x = %.4f (raíz doble)", parteReal);
        }
        double imaginaria = Math.sqrt(-d) / Math.abs(denominador);
        return String.format(Locale.US, "x1 = %.4f + %.4fi, x2 = %.4f - %.4fi",
                parteReal, imaginaria, parteReal, imaginaria);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "(%.2f)x² + (%.2f)x + (%.2f) = 0", a, b, c);
    }
}

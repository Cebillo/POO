package edu.unl.cc.domain;


public final class planilla {

    // Tarifas
    private static final double TARIFA_1 = 0.10;   // primeros 100 kWh
    private static final double TARIFA_2 = 0.15;   // de 101 a 200 kWh
    private static final double TARIFA_3 = 0.20;   // más de 200 kWh
    private static final double LIMITE_BLOQUE_1 = 100;
    private static final double LIMITE_BLOQUE_2 = 200;
    private static final double IVA = 0.15;

    // Atributos (encapsulados): solo lo necesario
    private final String numeroMedidor;
    private final String nombreTitular;
    private final double lecturaAnterior;
    private double lecturaActual;

    /**
     * @throws IllegalArgumentException si algún dato es inválido o la lectura
     *                                  actual es menor que la anterior.
     */
    public planilla(String numeroMedidor, String nombreTitular,
                    double lecturaAnterior, double lecturaActual) {
        if (numeroMedidor == null || numeroMedidor.isBlank()) {
            throw new IllegalArgumentException("El número de medidor no puede estar vacío.");
        }
        if (nombreTitular == null || nombreTitular.isBlank()) {
            throw new IllegalArgumentException("El nombre del titular no puede estar vacío.");
        }
        if (lecturaAnterior < 0) {
            throw new IllegalArgumentException("La lectura anterior no puede ser negativa.");
        }
        this.numeroMedidor = numeroMedidor;
        this.nombreTitular = nombreTitular;
        this.lecturaAnterior = lecturaAnterior;
        setLecturaActual(lecturaActual);   // reutiliza la validación
    }

    // ---------- Getters ----------
    public String getNumeroMedidor() {
        return numeroMedidor;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public double getLecturaAnterior() {
        return lecturaAnterior;
    }

    public double getLecturaActual() {
        return lecturaActual;
    }

    // ---------- Setter con validación ----------
    public void setLecturaActual(double lecturaActual) {
        if (lecturaActual < lecturaAnterior) {
            throw new IllegalArgumentException(
                    "La lectura actual (" + lecturaActual
                            + ") no puede ser menor que la anterior (" + lecturaAnterior + ").");
        }
        this.lecturaActual = lecturaActual;
    }

    // ---------- Cálculos ----------
    public double calcularConsumo() {
        return lecturaActual - lecturaAnterior;
    }

    public double calcularSubtotal() {
        double consumo = calcularConsumo();
        double subtotal;

        if (consumo <= LIMITE_BLOQUE_1) {
            subtotal = consumo * TARIFA_1;
        } else if (consumo <= LIMITE_BLOQUE_2) {
            subtotal = LIMITE_BLOQUE_1 * TARIFA_1
                    + (consumo - LIMITE_BLOQUE_1) * TARIFA_2;
        } else {
            subtotal = LIMITE_BLOQUE_1 * TARIFA_1
                    + (LIMITE_BLOQUE_2 - LIMITE_BLOQUE_1) * TARIFA_2
                    + (consumo - LIMITE_BLOQUE_2) * TARIFA_3;
        }
        return subtotal;
    }

    public double calcularIva() {
        Double iva = Double.valueOf(calcularSubtotal() * IVA);   // clase Wrapper Double
        return iva.doubleValue();
    }

    public double calcularTotal() {
        Double total = Double.valueOf(calcularSubtotal() + calcularIva());
        return Math.round(total * 100) / 100.0;   // redondeo a 2 decimales
    }

    // ---------- Estado de la planilla ----------
    @Override
    public String toString() {
        return String.format(java.util.Locale.US,
                """
                ===== PLANILLA DE CONSUMO ELÉCTRICO =====
                Medidor          : %s
                Titular          : %s
                Lectura anterior : %.0f kWh
                Lectura actual   : %.0f kWh
                Consumo          : %.0f kWh
                Subtotal         : $%.2f
                IVA (15%%)        : $%.2f
                Total a pagar    : $%.2f
                =========================================""",
                numeroMedidor, nombreTitular,
                lecturaAnterior, lecturaActual, calcularConsumo(),
                calcularSubtotal(), calcularIva(), calcularTotal());
    }
}

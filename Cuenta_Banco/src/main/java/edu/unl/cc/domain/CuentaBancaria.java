/**
 * @autor:Edgar Fabricio Rios Cuenca
 */
package edu.unl.cc.domain;
import java.util.ArrayList;

// Creo mi clase
public class CuentaBancaria {
    // Atributos privados
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private ArrayList<String> historial;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }
    // Depositar dineron en la Cuenta Bancaria
    public boolean depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            historial.add(String.format("Deposito: +%.2f", monto));
            return true;
        }
        return false;
    }
    // Realizar un retiro de la Cuenta Bancaria
    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            historial.add(String.format("Retiro: -%.2f", monto));
            return true;
        }
        return false;
    }

    // Métodos Get tradicionales
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }
}
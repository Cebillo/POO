package edu.unl.cc.domain;
    /**
     * Fraccion problem
     * @author AlvaroYhalenV.
     */
//Definicion de clase

public class Fraccion {
    private int numerador;
    private int denominador;
//Constructor de la clase

    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = verificarDenominador(denominador);
    }
//Metodos de la clase fraccion

    private int verificarDenominador(int valor){
        if(valor==0){
            throw new IllegalArgumentException("EL DENOMINADOR NO PUEDE SER 0 - INTENTE NUEVAMENTE");
        }
        return valor;
    }

    private int mcd(int a, int b) {
        return b == 0 ? Math.abs(a) : mcd(b, a % b);
    }

    public void simplificar() {
        int mcdVal = mcd(this.numerador, this.denominador);
        this.numerador /= mcdVal;
        this.denominador /= mcdVal;
    }

    public Fraccion sumar(Fraccion otra){
        int nuevoNumerador = (this.numerador * otra.denominador) + (otra.numerador * this.denominador);
        int nuevoDenominador = (this.denominador) * (otra.denominador);
        Fraccion resultado = new Fraccion(nuevoNumerador,nuevoDenominador);
        resultado.simplificar();
        return resultado;
    }

    public Fraccion restar(Fraccion otra){
        int nuevoNumerador = (this.numerador * otra.denominador) - (otra.numerador * this.denominador);
        int nuevoDenominador = (this.denominador) * (otra.denominador);
        Fraccion resultado = new Fraccion(nuevoNumerador,nuevoDenominador);
        resultado.simplificar();
        return resultado;
    }

    public Fraccion multiplicar(Fraccion otra){
        int nuevoNumerador = (this.numerador*otra.numerador);
        int nuevoDenominador = (this.denominador* otra.denominador);
        Fraccion resultado = new Fraccion(nuevoNumerador,nuevoDenominador);
        resultado.simplificar();
        return resultado;
    }

    public Fraccion dividir(Fraccion otra){
        int nuevoNumerador = (this.numerador*otra.denominador);
        int nuevoDenominador = (this.denominador* otra.numerador);
        Fraccion resultado = new Fraccion(nuevoNumerador,nuevoDenominador);
        resultado.simplificar();
        return resultado;
    }

    public double obtenerValorDecimal() {
        return (double) this.numerador / this.denominador;
    }

    public Fraccion inversa(){
        int nuevoNumerador = (this.denominador);
        int nuevoDenominador = (this.numerador);
        if (nuevoDenominador == 0) {
            return null;
        }else {
            Fraccion resultado = new Fraccion(nuevoNumerador, nuevoDenominador);
            resultado.simplificar();
            return resultado;
        }
    }

//Getters and Setters

    public int getNumerador() {
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    @Override
    public String toString() {
        return "[" + this.numerador + " / " + this.denominador + "]";
    }

}


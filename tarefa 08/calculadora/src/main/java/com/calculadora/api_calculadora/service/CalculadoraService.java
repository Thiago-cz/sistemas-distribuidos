package com.calculadora.api_calculadora.service;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class CalculadoraService {

    public double somar(double a, double b) {
        return a + b;
    }


    public double subtrair(double a, double b) {
        return a - b;
    }


    public double multiplicar(double a, double b) {
        return a * b;
    }


    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero é invalida");
        }
        return a / b;
    }


    public double raiz(double n) {
        if (n < 0) {
            throw new ArithmeticException("Raiz de número negativos.");
        }
        return Math.sqrt(n);
    }


    public double potencia(double b, double e) {
        return Math.pow(b, e);
    }


    public double inverso(double n) {
        if (n == 0) {
            throw new ArithmeticException("Impossivel calcular o inverso de zero.");
        }
        return 1 / n;
    }


    public double modulo(double n) {
        return Math.abs(n);
    }


    public long fatorial(int n) {
        if (n < 0) {
            throw new ArithmeticException("Fatorial de número negativo");
        }
        long resultado = 1;

        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public double media(List<Double> values) {
        if (values.size() != 5) {
            throw new IllegalArgumentException("A media deve calculada para exatamente 5 numeros");
        }
        return values.stream().mapToDouble(Double::doubleValue).sum() / values.size();
    }

    public double mediana(List<Double> values) {
        if (values.size() != 5) {
            throw new IllegalArgumentException("A media deve calculada para exatamente 5 numeros");
        }
        Collections.sort(values);
        return values.get(2); 
    }
}

package com.calculadora.api_calculadora.controller;

import com.calculadora.api_calculadora.service.CalculadoraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @GetMapping("/soma")
    public double somar(@RequestParam double a, @RequestParam double b) {
        return calculadoraService.somar(a, b);
    }

    @GetMapping("/subtracao")
    public double subtrair(@RequestParam double a, @RequestParam double b) {
        return calculadoraService.subtrair(a, b);
    }

    @GetMapping("/multiplicacao")
    public double multiplicar(@RequestParam double a, @RequestParam double b) {
        return calculadoraService.multiplicar(a, b);
    }

    @GetMapping("/dividisao")
    public double dividir(@RequestParam double a, @RequestParam double b) {
        return calculadoraService.dividir(a, b);
    }

    @GetMapping("/raiz")
    public double raiz(@RequestParam double a) {
        return calculadoraService.raiz(a);
    }

    @GetMapping("/potencia")
    public double potencia(@RequestParam double base, @RequestParam double expoente) {
        return calculadoraService.potencia(base, expoente);
    }

    @GetMapping("/inverso")
    public double inverso(@RequestParam double a) {
        return calculadoraService.inverso(a);
    }

    @GetMapping("/modulo")
    public double modulo(@RequestParam double a) {
        return calculadoraService.modulo(a);
    }

    @GetMapping("/fatorial")
    public long fatorial(@RequestParam int a) {
        return calculadoraService.fatorial(a);
    }

    @GetMapping("/media")
    public double media(@RequestParam List<Double> valores) {
        return calculadoraService.media(valores);
    }

    @GetMapping("/mediana")
    public double mediana(@RequestParam List<Double> valores) {
        return calculadoraService.mediana(valores);
    }
}

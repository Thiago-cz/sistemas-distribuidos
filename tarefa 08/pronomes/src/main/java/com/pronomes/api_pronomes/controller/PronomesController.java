package com.pronomes.api_pronomes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PronomesController {

    @GetMapping("/saudacao")
    public String saudacao(
            @RequestParam(value = "tratamento", required = false, defaultValue = "Seu") String tratamento,
            @RequestParam(value = "nome", required = false, defaultValue = "Zé") String nome,
            @RequestParam(value = "sobrenome", required = false, defaultValue = "da Silva") String sobrenome) {
        if ((tratamento.isEmpty() || tratamento.isBlank()) &&
                (nome.isEmpty() || nome.isBlank()) &&
                (sobrenome.isEmpty() || sobrenome.isBlank()))
            throw new IllegalArgumentException("Deve passar pelo menos um parametro");
        return String.format("(%s; %s, %s)", tratamento, sobrenome, nome);
    }
}

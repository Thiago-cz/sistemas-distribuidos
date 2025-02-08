package com.example.thiagao.controller;

import com.example.thiagao.service.CpfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CPF")
public class CpfController {

    private final CpfService cpfService;

    public CpfController(CpfService cpfService) {
        this.cpfService = cpfService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Boolean> validateCpf(@PathVariable String cpf) {
        boolean isValid = cpfService.isValid(cpf);
        return ResponseEntity.ok(isValid);
    }
}
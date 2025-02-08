package com.example.thiagao.controller;

import com.example.thiagao.service.CnpjService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CNPJ")
public class CnpjController {

    private final CnpjService cnpjService;

    public CnpjController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Boolean> validateCnpj(@PathVariable String cnpj) {
        boolean isValid = cnpjService.isValid(cnpj);
        return ResponseEntity.ok(isValid);
    }
}
package com.example.thiagao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        // CPF válido:http:// localhost:8080/CPF/529.982.247-25
        // CPF inválido:http:// localhost:8080/CPF/111.111.111-11
        // CNPJ válido:http:// localhost:8080/CNPJ/33.381.926/0001-40
        // CNPJ inválido:http:// localhost:8080/CNPJ/11.111.111/1111-11
    }
}
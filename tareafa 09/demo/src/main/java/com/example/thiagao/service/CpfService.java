package com.example.thiagao.service;

import org.springframework.stereotype.Service;

import com.example.thiagao.exception.InvalidCpfException;

@Service
public class CpfService {

    

    public boolean isValid(String cpf) {
        if (!isValidFormat(cpf)) {
            throw new InvalidCpfException("Formato inválido de CPF");
        }

        String sanitized = cpf.replaceAll("[^\\d]", "");

        if (!isValidDigits(sanitized)) {
            throw new InvalidCpfException("Dígitos verificadores inválidos");
        }

        return true;
    }

    public boolean isValidFormat(String cpf) {
        return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$");
    }

    public boolean isValidDigits(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11 || allSameDigits(cpf))
            return false;

        int dig1 = calculateDigit(cpf.substring(0, 9), 10);
        int dig2 = calculateDigit(cpf.substring(0, 9) + dig1, 11);

        return cpf.equals(cpf.substring(0, 9) + dig1 + dig2);
    }

    private int calculateDigit(String num, int weight) {
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            sum += Integer.parseInt(num.substring(i, i + 1)) * weight--;
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private boolean allSameDigits(String cpf) {
        return cpf.chars().allMatch(c -> c == cpf.charAt(0));
    }
}
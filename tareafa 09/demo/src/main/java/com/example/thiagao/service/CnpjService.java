package com.example.thiagao.service;

import com.example.thiagao.exception.InvalidCnpjException;
import org.springframework.stereotype.Service;

@Service
public class CnpjService {

    public boolean isValid(String cnpj) {
        if (!isValidFormat(cnpj)) {
            throw new InvalidCnpjException("Formato inválido de CNPJ");
        }
        
        String sanitized = cnpj.replaceAll("[^\\d]", "");
        
        if (!isValidDigits(sanitized)) {
            throw new InvalidCnpjException("Dígitos verificadores inválidos");
        }
        
        return true;
    }

    public boolean isValidFormat(String cnpj) {
        return cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$|^\\d{14}$");
    }

    public boolean isValidDigits(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");
        
        if (cnpj.length() != 14 || allSameDigits(cnpj)) return false;

        int dig1 = calculateDigit(cnpj.substring(0, 12), new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
        int dig2 = calculateDigit(cnpj.substring(0, 12) + dig1, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});

        return cnpj.equals(cnpj.substring(0, 12) + dig1 + dig2);
    }

    private int calculateDigit(String str, int[] weights) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += Integer.parseInt(str.substring(i, i+1)) * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private boolean allSameDigits(String cnpj) {
        return cnpj.chars().allMatch(c -> c == cnpj.charAt(0));
    }
}
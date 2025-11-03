package com.desafio.backend.util;

public class CpfValidator {
    public static boolean isValidCPF(String cpf) {
        String s = MaskUtils.onlyDigits(cpf);
        if (s == null || s.length() != 11) return false;
        if (s.matches("(\\d)\\1{10}")) return false; // todos iguais

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) sum += (s.charAt(i) - '0') * (10 - i);
            int r = 11 - (sum % 11);
            char dig1 = (r == 10 || r == 11) ? '0' : (char)('0' + r);

            sum = 0;
            for (int i = 0; i < 10; i++) sum += (s.charAt(i) - '0') * (11 - i);
            r = 11 - (sum % 11);
            char dig2 = (r == 10 || r == 11) ? '0' : (char)('0' + r);

            return dig1 == s.charAt(9) && dig2 == s.charAt(10);
        } catch (Exception e) {
            return false;
        }
    }
}

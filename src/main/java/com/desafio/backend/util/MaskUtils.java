package com.desafio.backend.util;

public class MaskUtils {

    public static String onlyDigits(String s) {
        if (s == null) return null;
        return s.replaceAll("\\D", "");
    }

    public static String CPF(String cpf) {
        String d = onlyDigits(cpf);
        if (d == null || d.length() != 11) return cpf;
        return d.substring(0,3)+"."+d.substring(3,6)+"."+d.substring(6,9)+"-"+d.substring(9);
        }

    public static String maskCEP(String cep) {
        String d = onlyDigits(cep);
        if (d == null || d.length() != 8) return cep;
        return d.substring(0,5) + "-" + d.substring(5);
    }

    public static String Phone(String phone) {
        String d = onlyDigits(phone);
        if (d == null) return phone;
        if (d.length() == 10) { // fixo: (XX) XXXX-XXXX
            return String.format("(%s) %s-%s", d.substring(0,2), d.substring(2,6), d.substring(6));
        } else if (d.length() == 11) { // celular: (XX) XXXXX-XXXX
            return String.format("(%s) %s-%s", d.substring(0,2), d.substring(2,7), d.substring(7));
        }
        return phone;
    }
}

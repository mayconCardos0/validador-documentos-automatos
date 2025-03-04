/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.validador_documentos.service;

import org.springframework.stereotype.Service;

@Service
public class RgService {

    enum Estado {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, ERRO
    }

    public boolean validarRg(String rg) {
        if(rg == null) return false;
        
        boolean rgValido = validarFormatoRg(rg);

        if (rgValido) {
            rgValido = validaDigitosRg(rg);
        }

        return rgValido;
    }

    private boolean validarFormatoRg(String rg) {
        Estado estado = Estado.Q0;

        boolean temPontuacao = false;

        for (char c : rg.toCharArray()) {
            switch (estado) {
                case Q0:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q1;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q1:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q2;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q2:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q4;
                    } else if (c == '.') {
                        temPontuacao = true;
                        estado = Estado.Q3;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q3:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q4;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q4:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q5;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q5:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q6;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q6:
                    if (Character.isDigit(c) && !temPontuacao) {
                        estado = Estado.Q8;
                    } else if (c == '.' && temPontuacao) {
                        estado = Estado.Q7;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q7:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q8;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q8:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q9;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q9:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q10;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q10:
                    if (Character.isDigit(c) && !temPontuacao) {
                        estado = Estado.Q12;
                    } else if (c == '-') {
                        estado = Estado.Q11;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                case Q11:
                    if (Character.isDigit(c) || Character.toUpperCase(c) == 'X' ) {
                        estado = Estado.Q12;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;
                default:
                    estado = Estado.ERRO;
            }
            
            if (estado == Estado.ERRO) {
                break;
            }
        }

        // Verifica se o estado final foi alcançado
        return estado == Estado.Q12;
    }

    private boolean validaDigitosRg(String rg) {
        rg = rg.replaceAll("[^0-9Xx]", "");


        if (rg.length() != 9) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex.: "11.111.111-11")
        if (rg.matches("(\\d)\\1{8}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 8; i++) {
            soma += Character.getNumericValue(rg.charAt(i)) * (2 + i);
        }

        int resto = soma % 11;
        int digitoVerificador = 11 - resto;

        char esperado;
        if (digitoVerificador == 10) {
            esperado = 'X';
        } else if (digitoVerificador == 11) {
            esperado = '0';
        } else {
            esperado = Character.forDigit(digitoVerificador, 10);
        }

        return rg.charAt(8) == esperado;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.validador_documentos.service;

import org.springframework.stereotype.Service;

@Service
public class CpfService {

    enum Estado {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, ERRO
    }

    public boolean validarCpf(String cpf) {
        if(cpf == null) return false;
        
        boolean cpfValido = validaFormatoCpf(cpf);

        if (cpfValido) {
            cpfValido = validaDigitosCpf(cpf);
        }

        return cpfValido;
    }

    private boolean validaFormatoCpf(String cpf) {
        Estado estado = Estado.Q0;

        boolean temPontuacao = false;

        for (char c : cpf.toCharArray()) {
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
                        estado = Estado.Q3;
                    } else {
                        return false;
                    }
                    break;

                case Q3:
                    if (c == '.') {
                        temPontuacao = true;
                        estado = Estado.Q4;
                    } else if (Character.isDigit(c)) {
                        estado = Estado.Q5;
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
                    if (Character.isDigit(c)) {
                        estado = Estado.Q7;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;

                case Q7:
                    if (c == '.' && temPontuacao) {
                        estado = Estado.Q8;
                    } else if (Character.isDigit(c) && !temPontuacao) {
                        estado = Estado.Q9;
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
                    if (Character.isDigit(c)) {
                        estado = Estado.Q11;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;

                case Q11:
                    if (Character.isDigit(c) && !temPontuacao) {
                        estado = Estado.Q13;

                    } else if (c == '-') {
                        estado = Estado.Q12;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;

                case Q12:
                    if (Character.isDigit(c)) {
                        estado = Estado.Q13;
                    } else {
                        estado = Estado.ERRO;
                    }
                    break;

                case Q13:
                    if (!Character.isDigit(c)) {
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

        return estado == Estado.Q13;
    }

    private boolean validaDigitosCpf(String cpf) {
        
        cpf = cpf.replaceAll("[^0-9Xx]", "");

        if (cpf.length() != 11) {
            return false;
        }
        
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        // Cálculo do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 >= 10) {
            digito1 = 0;
        }

        // Cálculo do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) {
            digito2 = 0;
        }

        // Verifica os dígitos
        return Character.getNumericValue(cpf.charAt(9)) == digito1 && Character.getNumericValue(cpf.charAt(10)) == digito2;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.validador_documentos.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CpfServiceTests {

    private CpfService cpfService;

    @BeforeEach
    void setUp() {
        cpfService = new CpfService();
    }

    @Test
    void naoDeveAceitarEntradaNula() {
        String entrada = null;
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarEntradaVazia() {
        String entrada = "";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarCpfPontuadoSemTracoNoFinal() {
        String entrada = "123.456.78909";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarCpfComPontoErrado() {
        String entrada = "1234.567.890-9";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarCpfComLetras() {
        String entrada = "ABC.DEF.GHI-JK";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarCpfComNumerosInsuficientes() {
        String entrada = "123456789";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void deveAceitarCpfRealComMascara() {
        String entrada = "123.456.789-09";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertTrue(resultado);
    }

    @Test
    void deveAceitarCpfRealSemMascara() {
        String entrada = "12345678909";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertTrue(resultado);
    }

    @Test
    void deveAceitarCpfRealComTraco() {
        String entrada = "123456789-09";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertTrue(resultado);
    }

    @Test
    void naoDeveAceitarCpfComTodosDigitosIguais() {
        String entrada = "111.111.111-11";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarCpfComDigitosInvalidos() {
        String entrada = "123.456.789-00";
        Boolean resultado = cpfService.validarCpf(entrada);
        assertFalse(resultado);
    }
}

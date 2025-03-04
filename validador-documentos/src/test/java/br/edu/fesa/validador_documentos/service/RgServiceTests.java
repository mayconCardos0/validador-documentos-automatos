/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.validador_documentos.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RgServiceTests {

    private RgService rgService;

    @BeforeEach
    void setUp() {
        rgService = new RgService();
    }

    @Test
    void naoDeveAceitarEntradaNula() {
        String entrada = null;
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarEntradaVazia() {
        String entrada = "";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarRgPontuadoSemTracoNoFinal() {
        String entrada = "12.540.3902";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarRgComPontoErrado() {
        String entrada = "125.403.90-2";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarRgComLetras() {
        String entrada = "AB.CDE.FGH-I";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarRgComNumerosInsuficientes() {
        String entrada = "123456789";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void deveAceitarRgRealComMascara() {
        String entrada = "12.540.390-2";
        Boolean resultado = rgService.validarRg(entrada);
        assertTrue(resultado);
    }

    @Test
    void deveAceitarRgRealSemMascara() {
        String entrada = "125403902";
        Boolean resultado = rgService.validarRg(entrada);
        assertTrue(resultado);
    }

    @Test
    void deveAceitarRgRealComTraco() {
        String entrada = "12540390-2";
        Boolean resultado = rgService.validarRg(entrada);
        assertTrue(resultado);
    }
    
    @Test
    void deveAceitarRgComDigitoX(){
        String entrada = "08.301.410-X";
        Boolean resultado = rgService.validarRg(entrada);
        assertTrue(resultado);
    }

    @Test
    void naoDeveAceitarRgComTodosDigitosIguais() {
        String entrada = "11.111.111-1";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }

    @Test
    void naoDeveAceitarRgComDigitosInvalidos() {
        String entrada = "12.540.390-0";
        Boolean resultado = rgService.validarRg(entrada);
        assertFalse(resultado);
    }
}

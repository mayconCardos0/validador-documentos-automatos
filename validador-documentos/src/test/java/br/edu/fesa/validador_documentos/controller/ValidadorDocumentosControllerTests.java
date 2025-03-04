import br.edu.fesa.validador_documentos.controller.ValidadorDocumentosController;
import br.edu.fesa.validador_documentos.service.CpfService;
import br.edu.fesa.validador_documentos.service.RgService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidadorDocumentosControllerTest {

    @Mock
    private CpfService cpfService;

    @Mock
    private RgService rgService;

    @InjectMocks
    private ValidadorDocumentosController controller;

    @Test
    void deveCarregarPagina() {
        String viewName = controller.index();
        assertEquals("validador/index", viewName);
    }

    @Test
    void deveAceitarChamadaDeCpf() {
        String documento = "12345678909";
        when(cpfService.validarCpf(documento)).thenReturn(true);

        boolean resultado = controller.validarDoc(documento, "cpf");

        assertTrue(resultado);
        verify(cpfService, times(1)).validarCpf(documento);
    }

    @Test
    void deveAceitarChamadaDeRg() {
        String documento = "1234567";
        when(rgService.validarRg(documento)).thenReturn(true);

        boolean resultado = controller.validarDoc(documento, "rg");

        assertTrue(resultado);
        verify(rgService, times(1)).validarRg(documento);
    }

    @Test
    void naoDeveAceitarTipoDesconhecido() {
        String documento = "000000000";
        boolean resultado = controller.validarDoc(documento, "passaporte");

        assertFalse(resultado);
        verifyNoInteractions(cpfService, rgService);
    }
}

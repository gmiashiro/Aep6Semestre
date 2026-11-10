package br.com.sextoSementre.aep.controller;

import br.com.sextoSementre.aep.dto.ObservacaoRequest;
import br.com.sextoSementre.aep.dto.ObservacaoResponse;
import br.com.sextoSementre.aep.service.ObservacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObservacaoControllerTest {

    @Mock
    private ObservacaoService observacaoService;

    private ObservacaoController observacaoController;

    @BeforeEach
    void setUp() {
        observacaoController = new ObservacaoController(observacaoService);
    }


    @Test
    void deveCriarObservacaoComSucesso() {
        ObservacaoRequest observacaoRequest = new ObservacaoRequest(
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        ObservacaoResponse observacaoResponse = new ObservacaoResponse(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(observacaoService.criar(any(ObservacaoRequest.class)))
                .thenReturn(observacaoResponse);

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/observacoes");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ObservacaoResponse resultado = observacaoController.criar(observacaoRequest).getBody();

        assertNotNull(resultado);
        assertEquals("1", resultado.id());
        assertEquals("1", resultado.plantaId());
        assertEquals("1", resultado.localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.data());
        assertEquals("Essa planta está em bom estado", resultado.descricao());
    }


    @Test
    void deveListarTodasAsObservacoes() {
        ObservacaoResponse observacaoResponse = new ObservacaoResponse(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        List<ObservacaoResponse> observacoes = List.of(observacaoResponse);


        when(observacaoService.listar()).thenReturn(observacoes);


        List<ObservacaoResponse> resultado = observacaoController.listar();

        assertEquals(1, resultado.size());
        assertEquals("1", resultado.get(0).id());
        assertEquals("1", resultado.get(0).plantaId());
        assertEquals("1", resultado.get(0).localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.get(0).data());
        assertEquals("Essa planta está em bom estado", resultado.get(0).descricao());
    }


    @Test
    void deveBuscarObservacaoPorId() {
        ObservacaoResponse observacaoResponse = new ObservacaoResponse(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );


        when(observacaoService.buscar("1")).thenReturn(observacaoResponse);


        ObservacaoResponse resultado = observacaoController.buscar("1");

        assertEquals("1", resultado.id());
        assertEquals("1", resultado.plantaId());
        assertEquals("1", resultado.localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.data());
        assertEquals("Essa planta está em bom estado", resultado.descricao());
    }


    @Test
    void deveAlterarObservacaoPorId() {
        ObservacaoRequest observacaoRequest = new ObservacaoRequest(
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        ObservacaoResponse observacaoResponse = new ObservacaoResponse(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(observacaoService.atualizar("1", observacaoRequest))
                .thenReturn(observacaoResponse);

        ObservacaoResponse resultado = observacaoController.atualizar("1", observacaoRequest);

        assertEquals("1", resultado.id());
        assertEquals("1", resultado.plantaId());
        assertEquals("1", resultado.localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.data());
        assertEquals("Essa planta está em bom estado", resultado.descricao());
    }


    @Test
    void deveDeletarObservacaoPorId() {
        observacaoController.excluir("1");


        verify(observacaoService, times(1))
                .excluir("1");
    }
    
}

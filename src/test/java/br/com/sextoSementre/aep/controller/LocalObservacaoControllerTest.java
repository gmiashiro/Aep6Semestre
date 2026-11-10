package br.com.sextoSementre.aep.controller;

import br.com.sextoSementre.aep.dto.LocalObservacaoRequest;
import br.com.sextoSementre.aep.dto.LocalObservacaoResponse;
import br.com.sextoSementre.aep.service.LocalObservacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocalObservacaoControllerTest {

    @Mock
    private LocalObservacaoService localObservacaoService;

    private LocalObservacaoController localObservacaoController;

    @BeforeEach
    void setUp() {
        localObservacaoController = new LocalObservacaoController(localObservacaoService);
    }


    @Test
    void deveCriarLocalObservacaoComSucesso() {
        LocalObservacaoRequest localObservacaoRequest = new LocalObservacaoRequest(
                "Avenida do centro",
                new LocalObservacaoRequest.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        LocalObservacaoResponse localSalvo = new LocalObservacaoResponse(
                "1",
                "Avenida do centro",
                new LocalObservacaoResponse.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        when(localObservacaoService.criar(any(LocalObservacaoRequest.class)))
                .thenReturn(localSalvo);

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/locais-observacao");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        LocalObservacaoResponse resultado = localObservacaoController.criar(localObservacaoRequest).getBody();

        assertNotNull(resultado);
        assertEquals("1", resultado.id());
        assertEquals("Avenida do centro", resultado.nome());
        assertEquals("Avenida do centro", resultado.endereco().logradouro());
        assertEquals("testecity", resultado.endereco().cidade());
        assertEquals("TE", resultado.endereco().uf());
    }



    @Test
    void deveListarTodosOsLocaisObservacao() {
        LocalObservacaoResponse localSalvo = new LocalObservacaoResponse(
                "1",
                "Avenida do centro",
                new LocalObservacaoResponse.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        List<LocalObservacaoResponse> locais = List.of(localSalvo);


        when(localObservacaoService.listar()).thenReturn(locais);


        List<LocalObservacaoResponse> resultado = localObservacaoController.listar();

        assertEquals(1, resultado.size());
        assertEquals("1", resultado.get(0).id());
        assertEquals("Avenida do centro", resultado.get(0).nome());
        assertEquals("Avenida do centro", resultado.get(0).endereco().logradouro());
        assertEquals("testecity", resultado.get(0).endereco().cidade());
        assertEquals("TE", resultado.get(0).endereco().uf());
    }


    @Test
    void deveBuscarLocalObservacaoPorId() {
        LocalObservacaoResponse localSalvo = new LocalObservacaoResponse(
                "1",
                "Avenida do centro",
                new LocalObservacaoResponse.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );


        when(localObservacaoService.buscar("1")).thenReturn(localSalvo);


        LocalObservacaoResponse resultado = localObservacaoController.buscar("1");

        assertEquals("1", resultado.id());
        assertEquals("Avenida do centro", resultado.nome());
        assertEquals("Avenida do centro", resultado.endereco().logradouro());
        assertEquals("testecity", resultado.endereco().cidade());
        assertEquals("TE", resultado.endereco().uf());
    }


    @Test
    void deveAlterarLocalObservacaoPorId() {
        LocalObservacaoRequest localObservacaoRequest = new LocalObservacaoRequest(
                "Rua da esquina",
                new LocalObservacaoRequest.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        LocalObservacaoResponse localSalvo = new LocalObservacaoResponse(
                "1",
                "Rua da esquina",
                new LocalObservacaoResponse.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        when(localObservacaoService.atualizar("1", localObservacaoRequest))
                .thenReturn(localSalvo);

        LocalObservacaoResponse resultado = localObservacaoController.atualizar("1", localObservacaoRequest);

        assertNotNull(resultado);
        assertEquals("1", resultado.id());
        assertEquals("Rua da esquina", resultado.nome());
        assertEquals("Avenida do centro", resultado.endereco().logradouro());
        assertEquals("testecity", resultado.endereco().cidade());
        assertEquals("TE", resultado.endereco().uf());
    }

    @Test
    void deveDeletarLocalObservacaoPorId() {
        localObservacaoController.excluir("1");


        verify(localObservacaoService, times(1))
                .excluir("1");
    }
    
}

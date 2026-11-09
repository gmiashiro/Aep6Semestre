package br.com.sextoSementre.aep.service;

import br.com.sextoSementre.aep.dto.LocalObservacaoRequest;
import br.com.sextoSementre.aep.dto.LocalObservacaoResponse;
import br.com.sextoSementre.aep.mapper.LocalObservacaoMapper;
import br.com.sextoSementre.aep.model.LocalObservacao;
import br.com.sextoSementre.aep.repository.LocalObservacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocalObservacaoServiceTest {

    @Mock
    private LocalObservacaoRepository localObservacaoRepository;

    private LocalObservacaoMapper localObservacaoMapper = new LocalObservacaoMapper();

    private LocalObservacaoService localObservacaoService;

    @BeforeEach
    void setUp() {
        localObservacaoService = new LocalObservacaoService(localObservacaoRepository, localObservacaoMapper);
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

        LocalObservacao localSalvo = new LocalObservacao(
                "1",
                "Avenida do centro",
                new LocalObservacao.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        when(localObservacaoRepository.insert(any(LocalObservacao.class)))
                .thenReturn(localSalvo);

        LocalObservacaoResponse resultado = localObservacaoService.criar(localObservacaoRequest);

        assertEquals("1", resultado.id());
        assertEquals("Avenida do centro", resultado.nome());
        assertEquals("Avenida do centro", resultado.endereco().logradouro());
        assertEquals("testecity", resultado.endereco().cidade());
        assertEquals("TE", resultado.endereco().uf());
    }


    @Test
    void deveListarTodosOsLocaisObservacao() {
        LocalObservacao localSalvo = new LocalObservacao(
                "1",
                "Avenida do centro",
                new LocalObservacao.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        List<LocalObservacao> locais = List.of(localSalvo);


        when(localObservacaoRepository.findAll()).thenReturn(locais);


        List<LocalObservacaoResponse> resultado = localObservacaoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("1", resultado.get(0).id());
        assertEquals("Avenida do centro", resultado.get(0).nome());
        assertEquals("Avenida do centro", resultado.get(0).endereco().logradouro());
        assertEquals("testecity", resultado.get(0).endereco().cidade());
        assertEquals("TE", resultado.get(0).endereco().uf());
    }


    @Test
    void deveBuscarLocalObservacaoPorId() {
        LocalObservacao localSalvo = new LocalObservacao(
                "1",
                "Avenida do centro",
                new LocalObservacao.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        when(localObservacaoRepository.findById("1")).thenReturn(Optional.of(localSalvo));

        LocalObservacaoResponse resultado = localObservacaoService.buscar("1");

        assertEquals("1", resultado.id());
        assertEquals("Avenida do centro", resultado.nome());
        assertEquals("Avenida do centro", resultado.endereco().logradouro());
        assertEquals("testecity", resultado.endereco().cidade());
        assertEquals("TE", resultado.endereco().uf());
    }


    @Test
    void deveDeletarLocalObservacaoPorId() {
        LocalObservacao localSalvo = new LocalObservacao(
                "1",
                "Avenida do centro",
                new LocalObservacao.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        when(localObservacaoRepository.findById("1")).thenReturn(Optional.of(localSalvo));

        localObservacaoService.excluir("1");

        verify(localObservacaoRepository, times(1))
                .findById("1");

        verify(localObservacaoRepository, times(1))
                .delete(localSalvo);
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

        LocalObservacao localSalvo = new LocalObservacao(
                "1",
                "Avenida do centro",
                new LocalObservacao.Endereco(
                        "Avenida do centro",
                        "testecity",
                        "TE"
                )
        );

        when(localObservacaoRepository.findById("1")).thenReturn(Optional.of(localSalvo));

        when(localObservacaoRepository.save(any(LocalObservacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LocalObservacaoResponse resultado = localObservacaoService.atualizar("1", localObservacaoRequest);

        assertNotNull(resultado);
        assertEquals("1", resultado.id());
        assertEquals("Rua da esquina", resultado.nome());
        assertEquals("Avenida do centro", resultado.endereco().logradouro());
        assertEquals("testecity", resultado.endereco().cidade());
        assertEquals("TE", resultado.endereco().uf());
    }
}

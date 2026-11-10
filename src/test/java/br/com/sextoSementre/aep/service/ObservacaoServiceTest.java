package br.com.sextoSementre.aep.service;

import br.com.sextoSementre.aep.dto.ObservacaoRequest;
import br.com.sextoSementre.aep.dto.ObservacaoResponse;
import br.com.sextoSementre.aep.exception.RecursoNaoEncontradoException;
import br.com.sextoSementre.aep.mapper.ObservacaoMapper;
import br.com.sextoSementre.aep.model.Observacao;
import br.com.sextoSementre.aep.repository.LocalObservacaoRepository;
import br.com.sextoSementre.aep.repository.ObservacaoRepository;
import br.com.sextoSementre.aep.repository.PlantaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObservacaoServiceTest {

    @Mock
    private ObservacaoRepository observacaoRepository;

    @Mock
    private PlantaRepository plantaRepository;

    @Mock
    private LocalObservacaoRepository localObservacaoRepository;

    private ObservacaoMapper observacaoMapper = new ObservacaoMapper();

    private ObservacaoService observacaoService;

    @BeforeEach
    void setUp() {
        observacaoService = new ObservacaoService(observacaoRepository, observacaoMapper, plantaRepository, localObservacaoRepository);
    }


    @Test
    void deveCriarObservacaoComSucesso() {
        ObservacaoRequest observacaoRequest = new ObservacaoRequest(
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        Observacao observacao = new Observacao(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(observacaoRepository.insert(any(Observacao.class))).thenReturn(observacao);

        when(plantaRepository.existsById("1")).thenReturn(true);

        when(localObservacaoRepository.existsById("1")).thenReturn(true);

        ObservacaoResponse resultado = observacaoService.criar(observacaoRequest);

        assertEquals("1", resultado.id());
        assertEquals("1", resultado.plantaId());
        assertEquals("1", resultado.localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.data());
        assertEquals("Essa planta está em bom estado", resultado.descricao());
    }


    @Test
    void deveListarTodosAsObservacoes() {
        Observacao observacaoSalva = new Observacao(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        List<Observacao> observacoes = List.of(observacaoSalva);

        when(observacaoRepository.findAll()).thenReturn(observacoes);

        List<ObservacaoResponse> resultado = observacaoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("1", resultado.get(0).id());
        assertEquals("1", resultado.get(0).plantaId());
        assertEquals("1", resultado.get(0).localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.get(0).data());
        assertEquals("Essa planta está em bom estado", resultado.get(0).descricao());
    }

    @Test
    void deveBuscarObservacaoPorId() {
        Observacao observacaoSalva = new Observacao(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(observacaoRepository.findById("1")).thenReturn(Optional.of(observacaoSalva));

        ObservacaoResponse resultado = observacaoService.buscar("1");

        assertEquals("1", resultado.id());
        assertEquals("1", resultado.plantaId());
        assertEquals("1", resultado.localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.data());
        assertEquals("Essa planta está em bom estado", resultado.descricao());
    }

    @Test
    void deveDeletarObservacaoPorId() {
        Observacao observacaoSalva = new Observacao(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(observacaoRepository.findById("1")).thenReturn(Optional.of(observacaoSalva));

        observacaoService.excluir("1");

        verify(observacaoRepository, times(1))
                .findById("1");

        verify(observacaoRepository, times(1))
                .delete(observacaoSalva);
    }

    @Test
    void deveAlterarObservacaoPorId() {
        ObservacaoRequest observacaoRequest = new ObservacaoRequest(
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        Observacao observacao = new Observacao(
                "1",
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(observacaoRepository.findById("1")).thenReturn(Optional.of(observacao));

        when(observacaoRepository.save(any(Observacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(plantaRepository.existsById("1")).thenReturn(true);

        when(localObservacaoRepository.existsById("1")).thenReturn(true);

        ObservacaoResponse resultado = observacaoService.atualizar("1", observacaoRequest);

        assertNotNull(resultado);
        assertEquals("1", resultado.id());
        assertEquals("1", resultado.plantaId());
        assertEquals("1", resultado.localId());
        assertEquals(LocalDate.parse("2026-11-09"), resultado.data());
        assertEquals("Essa planta está em bom estado", resultado.descricao());
    }

    @Test
    void deveRejeitarCriarObservacaoSeNaoExistirPlantaComId() {
        ObservacaoRequest observacaoRequest = new ObservacaoRequest(
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(plantaRepository.existsById("1")).thenReturn(false);

        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> observacaoService.criar(observacaoRequest)
        );
    }

    @Test
    void deveRejeitarCriarObservacaoSeNaoExistirLocalComId() {
        ObservacaoRequest observacaoRequest = new ObservacaoRequest(
                "1",
                "1",
                LocalDate.parse("2026-11-09"),
                "Essa planta está em bom estado"
        );

        when(plantaRepository.existsById("1")).thenReturn(true);

        when(localObservacaoRepository.existsById("1")).thenReturn(false);

        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> observacaoService.criar(observacaoRequest)
        );
    }
}

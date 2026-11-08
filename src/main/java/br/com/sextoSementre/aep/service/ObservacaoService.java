package br.com.sextoSementre.aep.service;

import br.com.sextoSementre.aep.dto.ObservacaoRequest;
import br.com.sextoSementre.aep.dto.ObservacaoResponse;
import br.com.sextoSementre.aep.exception.RecursoNaoEncontradoException;
import br.com.sextoSementre.aep.mapper.ObservacaoMapper;
import br.com.sextoSementre.aep.model.Observacao;
import br.com.sextoSementre.aep.repository.ObservacaoRepository;
import br.com.sextoSementre.aep.repository.LocalObservacaoRepository;
import br.com.sextoSementre.aep.repository.PlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservacaoService {

    private final ObservacaoRepository repository;
    private final ObservacaoMapper mapper;
    private final PlantaRepository plantas;
    private final LocalObservacaoRepository locais;

    public ObservacaoService(ObservacaoRepository repository, ObservacaoMapper mapper,
                             PlantaRepository plantas, LocalObservacaoRepository locais) {
        this.repository = repository;
        this.mapper = mapper;
        this.plantas = plantas;
        this.locais = locais;
    }

    public ObservacaoResponse criar(ObservacaoRequest request) {
        validarReferencias(request);
        return mapper.toResponse(repository.insert(mapper.toModel(null, request)));
    }

    public List<ObservacaoResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ObservacaoResponse buscar(String id) {
        return mapper.toResponse(encontrar(id));
    }

    public ObservacaoResponse atualizar(String id, ObservacaoRequest request) {
        Observacao existente = encontrar(id);
        validarReferencias(request);
        return mapper.toResponse(repository.save(mapper.toModel(existente.id(), request)));
    }

    public void excluir(String id) {
        repository.delete(encontrar(id));
    }

    private Observacao encontrar(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Observação", id));
    }

    private void validarReferencias(ObservacaoRequest request) {
        if (!plantas.existsById(request.plantaId())) {
            throw new RecursoNaoEncontradoException("Planta", request.plantaId());
        }
        if (!locais.existsById(request.localId())) {
            throw new RecursoNaoEncontradoException("Local de observação", request.localId());
        }
    }

}

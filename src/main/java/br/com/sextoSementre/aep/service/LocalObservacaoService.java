package br.com.sextoSementre.aep.service;

import br.com.sextoSementre.aep.dto.LocalObservacaoRequest;
import br.com.sextoSementre.aep.dto.LocalObservacaoResponse;
import br.com.sextoSementre.aep.exception.RecursoNaoEncontradoException;
import br.com.sextoSementre.aep.mapper.LocalObservacaoMapper;
import br.com.sextoSementre.aep.model.LocalObservacao;
import br.com.sextoSementre.aep.repository.LocalObservacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalObservacaoService {

    private final LocalObservacaoRepository repository;
    private final LocalObservacaoMapper mapper;

    public LocalObservacaoService(LocalObservacaoRepository repository, LocalObservacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public LocalObservacaoResponse criar(LocalObservacaoRequest request) {
        return mapper.toResponse(repository.insert(mapper.toModel(null, request)));
    }

    public List<LocalObservacaoResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public LocalObservacaoResponse buscar(String id) {
        return mapper.toResponse(encontrar(id));
    }

    public LocalObservacaoResponse atualizar(String id, LocalObservacaoRequest request) {
        LocalObservacao existente = encontrar(id);
        return mapper.toResponse(repository.save(mapper.toModel(existente.id(), request)));
    }

    public void excluir(String id) {
        repository.delete(encontrar(id));
    }

    private LocalObservacao encontrar(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Local de observação", id));
    }

}

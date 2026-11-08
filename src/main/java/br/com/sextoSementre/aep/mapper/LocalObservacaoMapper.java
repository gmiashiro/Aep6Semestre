package br.com.sextoSementre.aep.mapper;

import br.com.sextoSementre.aep.dto.LocalObservacaoRequest;
import br.com.sextoSementre.aep.dto.LocalObservacaoResponse;
import br.com.sextoSementre.aep.model.LocalObservacao;
import org.springframework.stereotype.Component;

@Component
public class LocalObservacaoMapper {

    public LocalObservacao toModel(String id, LocalObservacaoRequest request) {
        var endereco = request.endereco();
        return new LocalObservacao(id, request.nome(),
                new LocalObservacao.Endereco(endereco.logradouro(), endereco.cidade(), endereco.uf()));
    }

    public LocalObservacaoResponse toResponse(LocalObservacao local) {
        var endereco = local.endereco();
        return new LocalObservacaoResponse(local.id(), local.nome(),
                new LocalObservacaoResponse.Endereco(endereco.logradouro(), endereco.cidade(), endereco.uf()));
    }
}

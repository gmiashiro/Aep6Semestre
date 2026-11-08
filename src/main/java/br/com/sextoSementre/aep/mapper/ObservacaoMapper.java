package br.com.sextoSementre.aep.mapper;

import br.com.sextoSementre.aep.dto.ObservacaoRequest;
import br.com.sextoSementre.aep.dto.ObservacaoResponse;
import br.com.sextoSementre.aep.model.Observacao;
import org.springframework.stereotype.Component;

@Component
public class ObservacaoMapper {

    public Observacao toModel(String id, ObservacaoRequest request) {
        return new Observacao(id, request.plantaId(), request.localId(), request.data(), request.descricao());
    }

    public ObservacaoResponse toResponse(Observacao observacao) {
        return new ObservacaoResponse(observacao.id(), observacao.plantaId(), observacao.localId(), observacao.data(), observacao.descricao());
    }
}

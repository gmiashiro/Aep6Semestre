package br.com.sextoSementre.aep.repository;

import br.com.sextoSementre.aep.model.Observacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObservacaoRepository extends MongoRepository<Observacao, String> {
}

package br.com.sextoSementre.aep.repository;

import br.com.sextoSementre.aep.model.LocalObservacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalObservacaoRepository extends MongoRepository<LocalObservacao, String> {
}

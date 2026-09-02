package br.com.sextoSementre.aep.repository;

import br.com.sextoSementre.aep.model.PlantaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantaRepository extends MongoRepository<PlantaModel, String> {
}

package br.com.sextoSementre.aep.repository;

import br.com.sextoSementre.aep.model.PlantaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlantaRepository extends MongoRepository<PlantaModel, String> {
    Optional<PlantaModel> findByNomeCientifico(String nomeCientifico);

    void deleteByNomeCientifico(String nomeCientifico);
}

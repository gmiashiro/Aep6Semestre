package br.com.sextoSementre.aep.service;

import br.com.sextoSementre.aep.model.PlantaModel;
import br.com.sextoSementre.aep.repository.PlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepository;

    public PlantaService(PlantaRepository plantaRepository) {
        this.plantaRepository = plantaRepository;
    }

    public PlantaModel criarPlanta(PlantaModel plantaModel) {
        return plantaRepository.save(plantaModel);
    }

    public List<PlantaModel> listarPlantas() {
        return plantaRepository.findAll();
    }

    public Optional<PlantaModel> buscarPlanta(String id) {
        return plantaRepository.findById(id);
    }

    public Optional<PlantaModel> buscarPlantaPorNomeCientifico(String nomeCientifico) {
        return plantaRepository.findByNomeCientifico(nomeCientifico);
    }

    public void deletarPlanta(String id) {
        plantaRepository.deleteById(id);
    }

    public void deletarPlantaPorNomeCientifico(String nomeCientifico) {
        plantaRepository.deleteByNomeCientifico(nomeCientifico);
    }


}

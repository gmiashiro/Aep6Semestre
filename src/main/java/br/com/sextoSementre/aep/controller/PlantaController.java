package br.com.sextoSementre.aep.controller;

import br.com.sextoSementre.aep.model.PlantaModel;
import br.com.sextoSementre.aep.service.PlantaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plantas")
public class PlantaController {

    private final PlantaService plantaService;

    PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @PostMapping
    public ResponseEntity<PlantaModel> criarPlanta(@RequestBody PlantaModel plantaModel) {
        PlantaModel request = plantaService.criarPlanta(plantaModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(plantaModel.getId()).toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<PlantaModel>> listarPlantas() {
        List<PlantaModel> request = plantaService.listarPlantas();
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public Optional<PlantaModel> buscarPlantaPorId(@PathVariable String id) {
        return plantaService.buscarPlanta(id);
    }

    @GetMapping("/nome/{nomeCientifico}")
    public Optional<PlantaModel> buscarPlantaPorNomeCientifico(@PathVariable String nomeCientifico) {
        return plantaService.buscarPlantaPorNomeCientifico(nomeCientifico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPlanta(@PathVariable String id) {
        plantaService.deletarPlanta(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/nome/{nomeCientifico}")
    public ResponseEntity<?> deletarPlantaPorNomeCientifico(@PathVariable String nomeCientifico) {
        plantaService.deletarPlantaPorNomeCientifico(nomeCientifico);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantaModel> alterarPlanta(@RequestBody PlantaModel plantaModel, @PathVariable String id) {
        PlantaModel request = plantaService.alterarPlanta(plantaModel, id);

        return ResponseEntity.status(201).body(request);
    }

    @PutMapping("/nome/{nomeCientifico}")
    public ResponseEntity<PlantaModel> alterarPlantaPorNomeCientifico(@RequestBody PlantaModel plantaModel, @PathVariable String nomeCientifico) {
        PlantaModel request = plantaService.alterarPlantaPorNomeCientifico(plantaModel, nomeCientifico);

        return ResponseEntity.status(201).body(request);
    }

}
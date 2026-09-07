package br.com.sextoSementre.aep.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sextoSementre.aep.model.PlantaModel;
import br.com.sextoSementre.aep.repository.PlantaRepository;

@ExtendWith(MockitoExtension.class)
class PlantaServiceTest {

    @Mock
    private PlantaRepository plantaRepository;

    private PlantaService plantaService;

    @BeforeEach
    void setUp() {
        plantaService = new PlantaService(plantaRepository);
    }

    @Test
    void deveCriarPlantaNoRepository() {
        PlantaModel planta = planta();
        when(plantaRepository.save(planta)).thenReturn(planta);

        PlantaModel resultado = plantaService.criarPlanta(planta);

        assertSame(planta, resultado);
        verify(plantaRepository).save(planta);
        verifyNoMoreInteractions(plantaRepository);
    }

    @Test
    void deveListarTodasAsPlantas() {
        List<PlantaModel> plantas = List.of(planta());
        when(plantaRepository.findAll()).thenReturn(plantas);

        List<PlantaModel> resultado = plantaService.listarPlantas();

        assertEquals(plantas, resultado);
        verify(plantaRepository).findAll();
    }

    @Test
    void deveBuscarPlantaPorId() {
        PlantaModel planta = planta();
        when(plantaRepository.findById("id-1")).thenReturn(Optional.of(planta));

        Optional<PlantaModel> resultado = plantaService.buscarPlanta("id-1");

        assertEquals(Optional.of(planta), resultado);
        verify(plantaRepository).findById("id-1");
    }

    @Test
    void deveBuscarPlantaPorNomeCientifico() {
        PlantaModel planta = planta();
        when(plantaRepository.findByNomeCientifico("Roseira")).thenReturn(Optional.of(planta));

        Optional<PlantaModel> resultado = plantaService.buscarPlantaPorNomeCientifico("Roseira");

        assertEquals(Optional.of(planta), resultado);
        verify(plantaRepository).findByNomeCientifico("Roseira");
    }

    @Test
    void deveDeletarPlantaPorId() {
        plantaService.deletarPlanta("id-1");

        verify(plantaRepository).deleteById("id-1");
    }

    @Test
    void deveDeletarPlantaPorNomeCientifico() {
        plantaService.deletarPlantaPorNomeCientifico("Roseira");

        verify(plantaRepository).deleteByNomeCientifico("Roseira");
    }

    @Test
    void deveAlterarPlantaUsandoIdDaUrl() {
        PlantaModel planta = planta();
        when(plantaRepository.save(planta)).thenReturn(planta);

        PlantaModel resultado = plantaService.alterarPlanta(planta, "id-da-url");

        assertEquals("id-da-url", planta.getId());
        assertSame(planta, resultado);
        verify(plantaRepository).save(planta);
    }

    @Test
    void deveAlterarPlantaUsandoNomeCientificoDaUrl() {
        PlantaModel planta = planta();
        when(plantaRepository.save(planta)).thenReturn(planta);

        PlantaModel resultado = plantaService.alterarPlantaPorNomeCientifico(planta, "nome-da-url");

        assertEquals("nome-da-url", planta.getNomeCientifico());
        assertSame(planta, resultado);
        verify(plantaRepository).save(planta);
    }

    private PlantaModel planta() {
        return new PlantaModel(
                "Médio",
                "id-1",
                "Roseira",
                List.of("Rosa"),
                null,
                false,
                true,
                1.5,
                20,
                "Fértil",
                null,
                "Primavera",
                List.of("Vermelho")
        );
    }
}

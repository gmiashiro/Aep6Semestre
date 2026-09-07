package br.com.sextoSementre.aep.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.sextoSementre.aep.model.PlantaModel;
import br.com.sextoSementre.aep.service.PlantaService;

@ExtendWith(MockitoExtension.class)
class PlantaControllerTest {

    @Mock
    private PlantaService plantaService;

    private PlantaController plantaController;
    private PlantaModel planta;

    @BeforeEach
    void setUp() {
        plantaController = new PlantaController(plantaService);
        planta = new PlantaModel(
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

    @AfterEach
    void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void deveCriarPlantaEInformarLocalizacao() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/plantas");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(plantaService.criarPlanta(planta)).thenReturn(planta);

        ResponseEntity<PlantaModel> resposta = plantaController.criarPlanta(planta);

        assertEquals(201, resposta.getStatusCode().value());
        assertSame(planta, resposta.getBody());
        assertEquals("/plantas/id-1", resposta.getHeaders().getLocation().getPath());
        verify(plantaService).criarPlanta(planta);
    }

    @Test
    void deveListarPlantas() {
        List<PlantaModel> plantas = List.of(planta);
        when(plantaService.listarPlantas()).thenReturn(plantas);

        ResponseEntity<List<PlantaModel>> resposta = plantaController.listarPlantas();

        assertEquals(200, resposta.getStatusCode().value());
        assertSame(plantas, resposta.getBody());
        verify(plantaService).listarPlantas();
    }

    @Test
    void deveBuscarPlantaPorId() {
        when(plantaService.buscarPlanta("id-1")).thenReturn(Optional.of(planta));

        Optional<PlantaModel> resposta = plantaController.buscarPlantaPorId("id-1");

        assertEquals(Optional.of(planta), resposta);
        verify(plantaService).buscarPlanta("id-1");
    }

    @Test
    void deveBuscarPlantaPorNomeCientifico() {
        when(plantaService.buscarPlantaPorNomeCientifico("Roseira")).thenReturn(Optional.of(planta));

        Optional<PlantaModel> resposta = plantaController.buscarPlantaPorNomeCientifico("Roseira");

        assertEquals(Optional.of(planta), resposta);
        verify(plantaService).buscarPlantaPorNomeCientifico("Roseira");
    }

    @Test
    void deveDeletarPlantaPorId() {
        ResponseEntity<?> resposta = plantaController.deletarPlanta("id-1");

        assertEquals(204, resposta.getStatusCode().value());
        verify(plantaService).deletarPlanta("id-1");
    }

    @Test
    void deveDeletarPlantaPorNomeCientifico() {
        ResponseEntity<?> resposta = plantaController.deletarPlantaPorNomeCientifico("Roseira");

        assertEquals(204, resposta.getStatusCode().value());
        verify(plantaService).deletarPlantaPorNomeCientifico("Roseira");
    }

    @Test
    void deveAlterarPlantaPorId() {
        when(plantaService.alterarPlanta(planta, "id-1")).thenReturn(planta);

        ResponseEntity<PlantaModel> resposta = plantaController.alterarPlanta(planta, "id-1");

        assertEquals(201, resposta.getStatusCode().value());
        assertSame(planta, resposta.getBody());
        verify(plantaService).alterarPlanta(planta, "id-1");
    }

    @Test
    void deveAlterarPlantaPorNomeCientifico() {
        when(plantaService.alterarPlantaPorNomeCientifico(planta, "Roseira")).thenReturn(planta);

        ResponseEntity<PlantaModel> resposta = plantaController.alterarPlantaPorNomeCientifico(planta, "Roseira");

        assertEquals(201, resposta.getStatusCode().value());
        assertSame(planta, resposta.getBody());
        verify(plantaService).alterarPlantaPorNomeCientifico(planta, "Roseira");
    }
}

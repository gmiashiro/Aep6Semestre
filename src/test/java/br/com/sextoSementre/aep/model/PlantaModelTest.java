package br.com.sextoSementre.aep.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import br.com.sextoSementre.aep.enums.EstadoConservacao;
import br.com.sextoSementre.aep.enums.Luminosidade;

class PlantaModelTest {

    @Test
    void deveInicializarTodosOsAtributos() {
        PlantaModel planta = planta();

        assertEquals("id-1", planta.getId());
        assertEquals("Roseira", planta.getNomeCientifico());
        assertEquals(List.of("Rosa"), planta.getNomesPopulares());
        assertEquals(EstadoConservacao.SEGURA, planta.getEstadoConservacao());
        assertFalse(planta.getVenenosa());
        assertTrue(planta.getSeguraConsumoHumano());
        assertEquals(1.5, planta.getAlturaAproximada());
        assertEquals("Médio", planta.getPorte());
        assertEquals(20, planta.getLongevidade());
        assertEquals("Fértil", planta.getTipoSolo());
        assertEquals(Luminosidade.SOL_PLENO, planta.getLuminosidade());
        assertEquals("Primavera", planta.getPeriodoFloracao());
        assertEquals(List.of("Vermelho"), planta.getCorDasFlores());
    }

    @Test
    void deveAtualizarTodosOsAtributos() {
        PlantaModel planta = planta();
        List<String> nomesPopulares = List.of("Rosa nova");
        List<String> cores = List.of("Branco");

        planta.setId("id-2");
        planta.setNomeCientifico("Nome novo");
        planta.setNomesPopulares(nomesPopulares);
        planta.setEstadoConservacao(EstadoConservacao.VULNERAVEL);
        planta.setVenenosa(true);
        planta.setSeguraConsumoHumano(false);
        planta.setAlturaAproximada(2.5);
        planta.setPorte("Grande");
        planta.setLongevidade(30);
        planta.setTipoSolo("Argiloso");
        planta.setLuminosidade(Luminosidade.SOMBRA);
        planta.setPeriodoFloracao("Inverno");
        planta.setCorDasFlores(cores);

        assertEquals("id-2", planta.getId());
        assertEquals("Nome novo", planta.getNomeCientifico());
        assertEquals(nomesPopulares, planta.getNomesPopulares());
        assertEquals(EstadoConservacao.VULNERAVEL, planta.getEstadoConservacao());
        assertTrue(planta.getVenenosa());
        assertFalse(planta.getSeguraConsumoHumano());
        assertEquals(2.5, planta.getAlturaAproximada());
        assertEquals("Grande", planta.getPorte());
        assertEquals(30, planta.getLongevidade());
        assertEquals("Argiloso", planta.getTipoSolo());
        assertEquals(Luminosidade.SOMBRA, planta.getLuminosidade());
        assertEquals("Inverno", planta.getPeriodoFloracao());
        assertEquals(cores, planta.getCorDasFlores());
    }

    private PlantaModel planta() {
        return new PlantaModel(
                "Médio",
                "id-1",
                "Roseira",
                List.of("Rosa"),
                EstadoConservacao.SEGURA,
                false,
                true,
                1.5,
                20,
                "Fértil",
                Luminosidade.SOL_PLENO,
                "Primavera",
                List.of("Vermelho")
        );
    }
}

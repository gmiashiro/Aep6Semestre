package br.com.sextoSementre.aep.enums;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class EnumsTest {

    @Test
    void deveConterTodosOsEstadosDeConservacao() {
        assertArrayEquals(
                new EstadoConservacao[]{
                        EstadoConservacao.SEGURA,
                        EstadoConservacao.QUASE_AMEACADA,
                        EstadoConservacao.VULNERAVEL,
                        EstadoConservacao.EM_PERIGO,
                        EstadoConservacao.CRITICAMENTE_EM_PERIGO,
                        EstadoConservacao.EXTINTA_NA_NATUREZA,
                        EstadoConservacao.DADOS_INSUFICIENTES
                },
                EstadoConservacao.values()
        );
    }

    @Test
    void deveConterTodosOsNiveisDeLuminosidade() {
        assertArrayEquals(
                new Luminosidade[]{
                        Luminosidade.SOMBRA,
                        Luminosidade.MEIA_SOMBRA,
                        Luminosidade.SOL_PLENO
                },
                Luminosidade.values()
        );
    }
}

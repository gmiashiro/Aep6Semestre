package br.com.sextoSementre.aep.dto;

import java.time.LocalDate;

public record ObservacaoResponse(String id, String plantaId, String localId, LocalDate data, String descricao) {
}

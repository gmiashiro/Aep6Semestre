package br.com.sextoSementre.aep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "observacoes")
public record Observacao(@Id String id, String plantaId, String localId, LocalDate data, String descricao) {
}

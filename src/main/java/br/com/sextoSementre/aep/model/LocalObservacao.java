package br.com.sextoSementre.aep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locais_observacao")
public record LocalObservacao(@Id String id, String nome, Endereco endereco) {
    public record Endereco(String logradouro, String cidade, String uf) {}

}

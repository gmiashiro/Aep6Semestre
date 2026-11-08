package br.com.sextoSementre.aep.dto;

public record LocalObservacaoResponse(String id, String nome, Endereco endereco) {
    public record Endereco(String logradouro, String cidade, String uf) {}

}

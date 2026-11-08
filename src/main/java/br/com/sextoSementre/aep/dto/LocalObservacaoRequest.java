package br.com.sextoSementre.aep.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LocalObservacaoRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotNull @Valid Endereco endereco
) {
    public record Endereco(
            @NotBlank @Size(max = 160) String logradouro,
            @NotBlank @Size(max = 120) String cidade,
            @NotBlank @Pattern(regexp = "[A-Z]{2}") String uf
    ) {}

}

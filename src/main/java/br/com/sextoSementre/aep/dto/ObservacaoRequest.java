package br.com.sextoSementre.aep.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ObservacaoRequest(
        @NotBlank String plantaId,
        @NotBlank String localId,
        @NotNull @PastOrPresent LocalDate data,
        @NotBlank @Size(max = 1000) String descricao
) {
}

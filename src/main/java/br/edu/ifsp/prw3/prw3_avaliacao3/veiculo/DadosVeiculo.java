package br.edu.ifsp.prw3.prw3_avaliacao3.veiculo;

import jakarta.validation.constraints.NotBlank;

public record DadosVeiculo(
        @NotBlank String marca,
        @NotBlank String modelo,
        @NotBlank String ano) {
}
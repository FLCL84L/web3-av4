package br.edu.ifsp.prw3.prw3_avaliacao3.mecanico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosMecanico(
        @NotBlank String nome,
        @NotNull Integer anosExperiencia) {
}
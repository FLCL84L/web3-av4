package br.edu.ifsp.prw3.prw3_avaliacao3.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank String login,
        @NotBlank String senha) {
}

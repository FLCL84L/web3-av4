package br.edu.ifsp.prw3.prw3_avaliacao3.conserto;

import br.edu.ifsp.prw3.prw3_avaliacao3.mecanico.DadosMecanico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoConserto(
        @NotNull Long id,
        String dataSaida,
        DadosMecanico mecanico) {
}
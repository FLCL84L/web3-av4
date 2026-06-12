package br.edu.ifsp.prw3.prw3_avaliacao3.conserto;

import br.edu.ifsp.prw3.prw3_avaliacao3.mecanico.Mecanico;
import br.edu.ifsp.prw3.prw3_avaliacao3.veiculo.Veiculo;

public record DadosDetalhamentoConserto(Long id, String dataEntrada, String dataSaida, Mecanico mecanico, Veiculo veiculo) {
    public DadosDetalhamentoConserto(Conserto conserto) {
        this(conserto.getId(), conserto.getDataEntrada(), conserto.getDataSaida(), conserto.getMecanico(), conserto.getVeiculo());
    }
}
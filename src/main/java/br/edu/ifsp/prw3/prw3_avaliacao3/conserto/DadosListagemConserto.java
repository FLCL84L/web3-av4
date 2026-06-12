package br.edu.ifsp.prw3.prw3_avaliacao3.conserto;

import br.edu.ifsp.prw3.prw3_avaliacao3.mecanico.Mecanico;
import br.edu.ifsp.prw3.prw3_avaliacao3.veiculo.Veiculo;

public record DadosListagemConserto(Long id, String dataEntrada, String dataSaida, String mecanicoNome, String veiculoMarca, String veiculoModelo) {
    public DadosListagemConserto(Conserto conserto) {
        this(conserto.getId(), conserto.getDataEntrada(), conserto.getDataSaida(), conserto.getMecanico().getNome(), conserto.getVeiculo().getMarca(), conserto.getVeiculo().getModelo());
    }
}
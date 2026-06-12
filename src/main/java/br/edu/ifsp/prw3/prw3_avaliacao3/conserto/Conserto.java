package br.edu.ifsp.prw3.prw3_avaliacao3.conserto;

import br.edu.ifsp.prw3.prw3_avaliacao3.mecanico.Mecanico;
import br.edu.ifsp.prw3.prw3_avaliacao3.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "consertos")
@Entity(name = "Conserto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conserto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataEntrada;
    private String dataSaida;

    @Embedded
    private Mecanico mecanico;

    @Embedded
    private Veiculo veiculo;

    private Boolean ativo; // Para a exclusão lógica

    // Construtor que recebe o DTO de cadastro
    public Conserto(DadosCadastroConserto dados) {
        this.ativo = true; // Por padrão, um novo conserto está ativo
        this.dataEntrada = dados.dataEntrada();
        this.dataSaida = dados.dataSaida();
        this.mecanico = new Mecanico(dados.mecanico().nome(), dados.mecanico().anosExperiencia());
        this.veiculo = new Veiculo(dados.veiculo().marca(), dados.veiculo().modelo(), dados.veiculo().ano());
    }

    // Metodo para atualizar informações
    public void atualizarInformacoes(DadosAtualizacaoConserto dados) {
        if (dados.dataSaida() != null) {
            this.dataSaida = dados.dataSaida();
        }
        if (dados.mecanico() != null) {
            if (dados.mecanico().nome() != null) {
                this.mecanico = new Mecanico(dados.mecanico().nome(), this.mecanico.getAnosExperiencia());
            }
            if (dados.mecanico().anosExperiencia() != null) {
                this.mecanico = new Mecanico(this.mecanico.getNome(), dados.mecanico().anosExperiencia());
            }
        }
    }

    // Metodo para exclusão lógica
    public void excluir() {
        this.ativo = false;
    }
}
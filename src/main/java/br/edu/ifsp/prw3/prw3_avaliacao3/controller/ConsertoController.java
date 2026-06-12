package br.edu.ifsp.prw3.prw3_avaliacao3.controller;


import br.edu.ifsp.prw3.prw3_avaliacao3.conserto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    // ========== 1. POST - CADASTRAR UM NOVO CONSERTO ==========
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroConserto dados,
            UriComponentsBuilder uriBuilder) {

        // 1. Criar o objeto entidade a partir do DTO recebido
        var conserto = new Conserto(dados);

        // 2. Salvar no banco de dados
        repository.save(conserto);

        // 3. Construir a URL do novo recurso (ex: /consertos/5)
        var uri = uriBuilder
                .path("/consertos/{id}")
                .buildAndExpand(conserto.getId())
                .toUri();

        // 4. Retornar status 201 Created com o corpo contendo os dados detalhados
        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoConserto(conserto));
    }

    // ========== 2. GET - LISTAR TODOS OS CONSERTOS (DADOS COMPLETOS) ==========
    @GetMapping
    public ResponseEntity listarTodos() {
        var lista = repository.findAll();
        return ResponseEntity.ok(lista);
    }

    // ========== 3. GET - LISTAR APENAS DADOS RESUMIDOS (COM PAGINAÇÃO E APENAS ATIVOS) ==========
    @GetMapping("/lista-resumida")
    public ResponseEntity listarResumido(
            @PageableDefault(size = 10, sort = {"dataEntrada"}, direction = Sort.Direction.DESC)
            Pageable paginacao) {

        // Busca apenas consertos ativos, com paginação
        Page<DadosListagemConserto> pagina = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemConserto::new);

        return ResponseEntity.ok(pagina);
    }

    // ========== 4. GET - BUSCAR UM CONSERTO ESPECÍFICO PELO ID ==========
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        var consertoOptional = repository.findById(id);

        if (consertoOptional.isPresent()) {
            var conserto = consertoOptional.get();
            // Retorna os dados detalhados (completo)
            return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== 5. PUT - ATUALIZAR DADOS DE UM CONSERTO ==========
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoConserto dados) {
        try {
            // Busca a referência do conserto
            var conserto = repository.getReferenceById(dados.id());

            // Atualiza as informações permitidas
            conserto.atualizarInformacoes(dados);

            // Retorna status 200 OK com os dados atualizados
            return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));

        } catch (jakarta.persistence.EntityNotFoundException e) {
            // Se o ID não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // ========== 6. DELETE - EXCLUSÃO LÓGICA (INATIVAR UM CONSERTO) ==========
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        try {
            var conserto = repository.getReferenceById(id);

            // Metodo que seta ativo = false
            conserto.excluir();

            // Retorna 204 No Content (sucesso sem corpo na resposta)
            return ResponseEntity.noContent().build();

        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
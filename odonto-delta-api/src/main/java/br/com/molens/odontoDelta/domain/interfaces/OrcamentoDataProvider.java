package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.gateway.entity.Orcamento;

import java.util.Optional;

public interface OrcamentoDataProvider {

    Optional<Orcamento> buscarPorId(Long orcamentoId, Long empresaId);

    Long inserir(Orcamento orcamento);

    Orcamento atualizar(Orcamento orcamento);

    void remover(Long id);

    ListaPaginada<Orcamento> buscaPaginadaComFiltro(Orcamento.Filtro filtro);

}

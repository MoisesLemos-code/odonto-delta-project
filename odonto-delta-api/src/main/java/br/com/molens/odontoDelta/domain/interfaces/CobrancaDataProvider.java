package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;

import java.util.Optional;

public interface CobrancaDataProvider {

    Optional<Cobranca> buscarPorId(Long cobrancaId, Long empresaId);

    Optional<Cobranca> buscarPorId(Long cobrancaId);

    Cobranca inserir(Cobranca cobranca);

    Cobranca atualizar(Cobranca cobranca);

    void remover(Long id);

    ListaPaginada<Cobranca> buscaPaginadaComFiltro(Cobranca.Filtro filtro);

    Integer buscarUltimoId(Long empresaId);
}

package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.gateway.entity.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilDataProvider {

    Optional<Perfil> buscarPorId(Long perfilId, Long empresaId);

    Long inserir(Perfil perfil);

    Perfil atualizar(Perfil perfil);

    void remover(Long id);

    ListaPaginada<Perfil> buscaPaginadaComFiltro(Perfil.Filtro filtro);

    boolean existeNome(String nome, Long empresaId);

    Optional<Perfil> buscarPorNome(String nome, Long empresaId);

    List<Perfil> buscarPorEmpresaId(Long empresaId);

    List<Perfil> buscarAtivosPorEmpresaId(Long empresaId);
}

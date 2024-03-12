package br.com.molens.odontoDelta.gateway.repository.permissao;

import br.com.molens.odontoDelta.gateway.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>, QuerydslPredicateExecutor<Permissao> {

}

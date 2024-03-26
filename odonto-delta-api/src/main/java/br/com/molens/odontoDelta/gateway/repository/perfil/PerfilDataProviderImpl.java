package br.com.molens.odontoDelta.gateway.repository.perfil;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.QPerfil;
import br.com.molens.odontoDelta.utils.HelpUtil;
import br.com.molens.odontoDelta.utils.ConstrutorPaginacao;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PerfilDataProviderImpl implements PerfilDataProvider {

    @Autowired
    private PerfilRepository repository;

    @Override
    public Optional<Perfil> buscarPorId(Long perfilId, Long empresaId) {
        return repository.buscarPorId(perfilId, empresaId);
    }

    @Override
    public Long inserir(Perfil perfil) {
        perfil.setId(null);
        Perfil perfilEntity = repository.save(perfil);
        return perfilEntity.getId();
    }

    @Override
    public Perfil atualizar(Perfil perfil) {
        return repository.save(perfil);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ListaPaginada<Perfil> buscaPaginadaComFiltro(Perfil.Filtro filtro) {

        Page<Perfil> entidadesEncontradas = buscarComPaginacao(montarFiltroDeBusca(filtro), ConstrutorPaginacao.extrairPaginacao(filtro));

        List<Perfil> entidades = new ArrayList<>(entidadesEncontradas.getContent());

        return ListaPaginada.<Perfil>builder()
                .items(entidades)
                .totalElements(entidadesEncontradas.getTotalElements())
                .totalPages((long) entidadesEncontradas.getTotalPages())
                .build();
    }

    @Override
    public boolean existeNome(String nome, Long empresaId) {
        return repository.existsByNomeAndEmpresaId(nome, empresaId);
    }

    @Override
    public Optional<Perfil> buscarPorNome(String nome, Long empresaId) {
        return repository.buscarPorNomeEmpresa(nome, empresaId);
    }

    private BooleanExpression montarFiltroDeBusca(Perfil.Filtro filtro){
        QPerfil query = QPerfil.perfil;
        BooleanExpression expressaoDeBusca = query.id.isNotNull();

        if (!StringUtils.isEmpty(filtro.getConteudo())) {
            BooleanExpression filtrosParaBusca = compararSemAcentuacao(query.nome, filtro.getConteudo())
                    .or(compararSemAcentuacao(query.descricao, filtro.getConteudo()));
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }
        expressaoDeBusca.and(query.empresa.id.eq(filtro.getEmpresaId()));
        return expressaoDeBusca;
    }

    private BooleanExpression compararSemAcentuacao(StringExpression path, String value) {
        StringExpression expr = Expressions.stringTemplate("upper(translate({0}, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC'))", path.trim());
        return expr.containsIgnoreCase(HelpUtil.retiraAcentuacao(value.trim()));
    }

    private Page<Perfil> buscarComPaginacao(BooleanExpression expressaoBusca, Pageable paginacao) {
        return repository.findAll(expressaoBusca, paginacao);
    }
}

package br.com.molens.odontoDelta.gateway.repository.orcamento;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.OrcamentoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Orcamento;
import br.com.molens.odontoDelta.gateway.entity.QOrcamento;
import br.com.molens.odontoDelta.utils.ConstrutorPaginacao;
import br.com.molens.odontoDelta.utils.HelpUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class OrcamentoDataProviderImpl implements OrcamentoDataProvider {

    @Autowired
    private OrcamentoRepository repository;

    @Override
    public Optional<Orcamento> buscarPorId(Long cobrancaId, Long empresaId) {
        return repository.buscarPorId(cobrancaId, empresaId);
    }

    @Override
    public Long inserir(Orcamento cobranca) {
        cobranca.setId(null);

        Orcamento cobrancaSalva = repository.save(cobranca);

        return cobrancaSalva.getId();
    }

    @Override
    public Orcamento atualizar(Orcamento cobranca) {
        return repository.save(cobranca);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ListaPaginada<Orcamento> buscaPaginadaComFiltro(Orcamento.Filtro filtro) {
        Page<Orcamento> entidadesEncontradas = buscarComPaginacao(montarFiltroDeBusca(filtro), ConstrutorPaginacao.extrairPaginacao(filtro));

        List<Orcamento> entidades = new ArrayList<>(entidadesEncontradas.getContent());

        return ListaPaginada.<Orcamento>builder()
                .items(entidades)
                .totalElements(entidadesEncontradas.getTotalElements())
                .totalPages((long) entidadesEncontradas.getTotalPages())
                .build();
    }

    private BooleanExpression montarFiltroDeBusca(Orcamento.Filtro filtro){
        QOrcamento query = QOrcamento.orcamento;
        BooleanExpression expressaoDeBusca = query.empresa.id.eq(filtro.getEmpresaId());

        if(StringUtils.isNotEmpty(filtro.getAtributo())){

            if(filtro.getAtributo().equals("Codigo")){
                if(filtro.getTipo().equals("COMECA_COM")){
                    BooleanExpression filtrosParaBusca = query.codigo.startsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("CONTEM")){
                    BooleanExpression filtrosParaBusca = compararSemAcentuacao(query.codigo, filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("TERMINA_COM")){
                    BooleanExpression filtrosParaBusca = query.codigo.endsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                }
            } else if(filtro.getAtributo().equals("Status")){
                BooleanExpression filtrosParaBusca = query.status.eq(Orcamento.EnumStatusOrcamento.valueOf(filtro.getConteudo()));
                expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
            }
        } else {
            if (StringUtils.isNotEmpty(filtro.getConteudo())) {
                BooleanExpression filtrosParaBusca = compararSemAcentuacao(query.codigo, filtro.getConteudo())
                        .or(compararSemAcentuacao(query.paciente.nome, filtro.getConteudo()));
                expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
            }
        }

        if (Objects.nonNull(filtro.getPacienteId())) {
            BooleanExpression filtrosParaBusca = query.paciente.id.eq(filtro.getPacienteId());
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }


        return expressaoDeBusca;
    }

    private BooleanExpression compararSemAcentuacao(StringExpression path, String value) {
        StringExpression expr = Expressions.stringTemplate("upper(translate({0}, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC'))", path.trim());
        return expr.containsIgnoreCase(HelpUtil.retiraAcentuacao(value.trim()));
    }

    private Page<Orcamento> buscarComPaginacao(BooleanExpression expressaoBusca, Pageable paginacao) {
        return repository.findAll(expressaoBusca, paginacao);
    }

}

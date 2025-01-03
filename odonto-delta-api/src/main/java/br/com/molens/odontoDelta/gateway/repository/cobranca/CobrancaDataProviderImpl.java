package br.com.molens.odontoDelta.gateway.repository.cobranca;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.gateway.entity.QCobranca;
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
public class CobrancaDataProviderImpl implements CobrancaDataProvider {

    @Autowired
    private CobrancaRepository repository;

    @Override
    public Optional<Cobranca> buscarPorId(Long cobrancaId, Long empresaId) {
        return repository.buscarPorId(cobrancaId, empresaId);
    }

    @Override
    public Optional<Cobranca> buscarPorId(Long cobrancaId) {
        return repository.findById(cobrancaId);
    }

    @Override
    public Cobranca inserir(Cobranca cobranca) {
        cobranca.setId(null);
        return repository.save(cobranca);
    }

    @Override
    public Cobranca atualizar(Cobranca cobranca) {
        return repository.save(cobranca);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ListaPaginada<Cobranca> buscaPaginadaComFiltro(Cobranca.Filtro filtro) {
        Page<Cobranca> entidadesEncontradas = buscarComPaginacao(montarFiltroDeBusca(filtro), ConstrutorPaginacao.extrairPaginacao(filtro));

        List<Cobranca> entidades = new ArrayList<>(entidadesEncontradas.getContent());

        return ListaPaginada.<Cobranca>builder()
                .items(entidades)
                .totalElements(entidadesEncontradas.getTotalElements())
                .totalPages((long) entidadesEncontradas.getTotalPages())
                .build();
    }

    @Override
    public Integer buscarUltimoId(Long empresaId) {
        Integer cobrancaId = repository.buscarUltimoId(empresaId);

        return Objects.nonNull(cobrancaId) ? cobrancaId : 1;
    }

    private BooleanExpression montarFiltroDeBusca(Cobranca.Filtro filtro){
        QCobranca query = QCobranca.cobranca;
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
            }
        } else {
            if (StringUtils.isNotEmpty(filtro.getConteudo())) {
                BooleanExpression filtrosParaBusca = compararSemAcentuacao(query.codigo, filtro.getConteudo())
                        .or(compararSemAcentuacao(query.paciente.nome, filtro.getConteudo()));
                expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
            }
        }

        if(StringUtils.isNotEmpty(filtro.getStatusEnum())){
            BooleanExpression filtrosParaBusca = query.status.eq(Cobranca.EnumStatusCobranca.valueOf(filtro.getStatusEnum()).name());
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }
        if(Objects.nonNull(filtro.getDataVencimento())){
            BooleanExpression filtrosParaBusca = query.dataVencimento.before(filtro.getDataVencimento());
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
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

    private Page<Cobranca> buscarComPaginacao(BooleanExpression expressaoBusca, Pageable paginacao) {
        return repository.findAll(expressaoBusca, paginacao);
    }

}

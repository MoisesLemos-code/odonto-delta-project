package br.com.molens.odontoDelta.gateway.dataprovider.repository.paciente;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.QPaciente;
import br.com.molens.odontoDelta.utils.AcetuacaoUtils;
import br.com.molens.odontoDelta.utils.ConstrutorPaginacao;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PacienteDataProviderImpl implements PacienteDataProvider {

    @Autowired
    private PacienteRepository repository;

    @Override
    public Optional<Paciente> buscarPorId(Long pacienteId, Long empresaId) {
        return repository.buscarPorId(pacienteId, empresaId);
    }

    @Override
    public boolean existeCnpjCpf(String cpfCnpj) {
        return repository.existsByCnpjCpf(cpfCnpj);
    }

    @Override
    public Long inserir(Paciente paciente) {
        paciente.setId(null);
        Paciente pacienteEntity = repository.save(paciente);

        return pacienteEntity.getId();
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ListaPaginada<Paciente> buscaPaginadaComFiltro(Paciente.Filtro filtro) {

        Page<Paciente> entidadesEncontradas = buscarComPaginacao(montarFiltroDeBusca(filtro), ConstrutorPaginacao.extrairPaginacao(filtro));

        List<Paciente> entidades = new ArrayList<>(entidadesEncontradas.getContent());

        return ListaPaginada.<Paciente>builder()
                .items(entidades)
                .totalElements(entidadesEncontradas.getTotalElements())
                .totalPages((long) entidadesEncontradas.getTotalPages())
                .build();
    }

    @Override
    public Optional<Paciente> buscarPorCnpjCpf(String cnpjCpf) {
        return repository.buscarPorCnpjCpf(cnpjCpf);
    }

    private BooleanExpression montarFiltroDeBusca(Paciente.Filtro filtro){
        QPaciente clienteQuery = QPaciente.paciente;
        BooleanExpression expressaoDeBusca = clienteQuery.id.isNotNull();

        if (!StringUtils.isEmpty(filtro.getConteudo())) {
            BooleanExpression filtrosParaBusca = compararSemAcentuacao(clienteQuery.nome, filtro.getConteudo())
                    .or(compararSemAcentuacao(clienteQuery.cnpjCpf, filtro.getConteudo()))
                    .or(compararSemAcentuacao(clienteQuery.email, filtro.getConteudo()))
                    .or(compararSemAcentuacao(clienteQuery.telefone, filtro.getConteudo()))
                    .and(clienteQuery.empresa.id.eq(filtro.getEmpresaId()));
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }
        return expressaoDeBusca;
    }

    private BooleanExpression compararSemAcentuacao(StringExpression path, String value) {
        StringExpression expr = Expressions.stringTemplate("upper(translate({0}, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC'))", path.trim());
        return expr.containsIgnoreCase(AcetuacaoUtils.retiraAcentuacao(value.trim()));
    }

    private Page<Paciente> buscarComPaginacao(BooleanExpression expressaoBusca, Pageable paginacao) {
        return repository.findAll(expressaoBusca, paginacao);
    }
}

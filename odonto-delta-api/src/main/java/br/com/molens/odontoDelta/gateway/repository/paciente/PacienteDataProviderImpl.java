package br.com.molens.odontoDelta.gateway.repository.paciente;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.gateway.entity.QPaciente;
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
public class PacienteDataProviderImpl implements PacienteDataProvider {

    @Autowired
    private PacienteRepository repository;

    @Override
    public Optional<Paciente> buscarPorId(Long pacienteId, Long empresaId) {
        return repository.buscarPorId(pacienteId, empresaId);
    }

    @Override
    public boolean existeCnpjCpf(String cpfCnpj, Long empresaId) {
        return repository.existsByCnpjCpfAndEmpresaId(cpfCnpj, empresaId);
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
    public Optional<Paciente> buscarPorCnpjCpf(String cnpjCpf, Long empresaId) {
        return repository.buscarPorCnpjCpfEmpresa(cnpjCpf, empresaId);
    }

    private BooleanExpression montarFiltroDeBusca(Paciente.Filtro filtro){
        QPaciente clienteQuery = QPaciente.paciente;
        BooleanExpression expressaoDeBusca = clienteQuery.empresa.id.eq(filtro.getEmpresaId());

        if(Objects.nonNull(filtro.getOrtodontia())){
            BooleanExpression filtrosParaBusca = clienteQuery.ortodontia.eq(filtro.getOrtodontia());
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }
        if(Objects.nonNull(filtro.getMunicipioId()) && filtro.getMunicipioId() > 0){
            BooleanExpression filtrosParaBusca = clienteQuery.municipio.id.eq(filtro.getMunicipioId());
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }
        if(Objects.nonNull(filtro.getEstadoId()) && filtro.getEstadoId() > 0){
            BooleanExpression filtrosParaBusca = clienteQuery.municipio.estado.id.eq(filtro.getEstadoId());
            expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
        }

        if(StringUtils.isNotEmpty(filtro.getAtributo())){

            if(filtro.getAtributo().equals("Nome")){
                if(filtro.getTipo().equals("COMECA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.nome.startsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("CONTEM")){
                    BooleanExpression filtrosParaBusca = compararSemAcentuacao(clienteQuery.nome, filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("TERMINA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.nome.endsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                }
            } else if(filtro.getAtributo().equals("Documento")){
                if(filtro.getTipo().equals("COMECA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.cnpjCpf.startsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("CONTEM")){
                    BooleanExpression filtrosParaBusca = compararSemAcentuacao(clienteQuery.cnpjCpf, filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("TERMINA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.cnpjCpf.endsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                }
            } else if(filtro.getAtributo().equals("E-mail")){
                if(filtro.getTipo().equals("COMECA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.email.startsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("CONTEM")){
                    BooleanExpression filtrosParaBusca = compararSemAcentuacao(clienteQuery.email, filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("TERMINA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.email.endsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                }
            } else if(filtro.getAtributo().equals("Telefone")){
                if(filtro.getTipo().equals("COMECA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.telefone.startsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("CONTEM")){
                    BooleanExpression filtrosParaBusca = compararSemAcentuacao(clienteQuery.telefone, filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                } else if(filtro.getTipo().equals("TERMINA_COM")){
                    BooleanExpression filtrosParaBusca = clienteQuery.telefone.endsWith(filtro.getConteudo());
                    expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
                }
            }
        } else {
            if (StringUtils.isNotEmpty(filtro.getConteudo())) {
                BooleanExpression filtrosParaBusca = compararSemAcentuacao(clienteQuery.nome, filtro.getConteudo())
                        .or(compararSemAcentuacao(clienteQuery.cnpjCpf, filtro.getConteudo()))
                        .or(compararSemAcentuacao(clienteQuery.telefone, filtro.getConteudo()));
                expressaoDeBusca = expressaoDeBusca.and(filtrosParaBusca);
            }
        }


        return expressaoDeBusca;
    }

    private BooleanExpression compararSemAcentuacao(StringExpression path, String value) {
        StringExpression expr = Expressions.stringTemplate("upper(translate({0}, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC'))", path.trim());
        return expr.containsIgnoreCase(HelpUtil.retiraAcentuacao(value.trim()));
    }

    private Page<Paciente> buscarComPaginacao(BooleanExpression expressaoBusca, Pageable paginacao) {
        return repository.findAll(expressaoBusca, paginacao);
    }
}

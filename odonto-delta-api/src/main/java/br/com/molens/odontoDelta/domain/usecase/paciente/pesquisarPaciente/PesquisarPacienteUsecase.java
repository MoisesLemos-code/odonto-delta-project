package br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.exception.PesquisarPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente.converter.BuscaPesquisaPacienteOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class PesquisarPacienteUsecase {

    private PacienteDataProvider pacienteDataProvider;
    private BuscaPesquisaPacienteOutputConverter outputConverter;

    public PesquisarPacienteOutput executar(PesquisarPacienteInput input) {
        validarDadosEntrada(input);
        Paciente.Filtro filtro = criarFiltro(input);
        ListaPaginada<Paciente> entidadesEncontradas = buscar(filtro);

        return outputConverter.to(entidadesEncontradas);
    }

    private void validarDadosEntrada(PesquisarPacienteInput input) {
        if (Objects.isNull(input.getEmpresaId())) {
            throw new PesquisarPacienteException("Identificador de empresa inválido.");
        }
    }

    private Paciente.Filtro criarFiltro(PesquisarPacienteInput input) {
        Paciente.Filtro filtro = new Paciente.Filtro();
        filtro.setEmpresaId(input.getEmpresaId());
        filtro.setConteudo(input.getConteudo());
        filtro.setSize(50);
        filtro.setPage(0);
        filtro.setSort("nome");
        filtro.setDirection("ASC");

        return filtro;
    }

    private ListaPaginada<Paciente> buscar(Paciente.Filtro filtro) {
        return pacienteDataProvider.buscaPaginadaComFiltro(filtro);
    }

}

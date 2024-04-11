package br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.exception.BuscaPaginadaPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.converter.BuscaPaginadaPacienteFiltroConverter;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.converter.BuscaPaginadaPacienteOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscaPaginadaPacienteUsecase {

    private PacienteDataProvider pacienteDataProvider;

    private BuscaPaginadaPacienteFiltroConverter filtroConverter;
    private BuscaPaginadaPacienteOutputConverter outputConverter;

    public BuscaPaginadaPacienteOutput executar(BuscaPaginadaPacienteInput input){
        validarDadosEntrada(input);
        Paciente.Filtro filtro = criarFiltro(input);
        ListaPaginada<Paciente> entidadesEncontradas = buscar(filtro);

        return outputConverter.to(entidadesEncontradas);
    }

    private void validarDadosEntrada(BuscaPaginadaPacienteInput input) {
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscaPaginadaPacienteException("Identificador de empresa inválido.");
        }
    }

    private Paciente.Filtro criarFiltro(BuscaPaginadaPacienteInput input) {
        return this.filtroConverter.to(input);
    }

    private ListaPaginada<Paciente> buscar(Paciente.Filtro filtro) {
        return pacienteDataProvider.buscaPaginadaComFiltro(filtro);
    }
}

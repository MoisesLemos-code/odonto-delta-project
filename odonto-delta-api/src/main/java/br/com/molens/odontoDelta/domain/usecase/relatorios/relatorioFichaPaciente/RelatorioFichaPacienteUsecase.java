package br.com.molens.odontoDelta.domain.usecase.relatorios.relatorioFichaPaciente;

import br.com.molens.odontoDelta.domain.exception.RelatorioFichaPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.FichaPacienteDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.gateway.entity.FichaPaciente;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.relatorios.SistemaDeRelatorioIntegration;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class RelatorioFichaPacienteUsecase {

    private PacienteDataProvider pacienteDataProvider;
    private FichaPacienteDataProvider fichaPacienteDataProvider;
    private SistemaDeRelatorioIntegration sistemaDeRelatorioIntegration;

    public byte[] executar(RelatorioFichaPacienteInput input){
        validarEntrada(input);
        Paciente paciente = buscarPaciente(input);
        FichaPaciente fichaPaciente = buscarFichaPaciente(input);

        return gerarArquivo(paciente, fichaPaciente);
    }

    private byte[] gerarArquivo(Paciente paciente, FichaPaciente fichaPaciente) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nomePaciente", paciente.getNome());
        parametros.put("documento", paciente.getCnpjCpf());
        return sistemaDeRelatorioIntegration.gerarPdf(SistemaDeRelatorioIntegration.OpcoesRelatorio.builder()
                .html("relatorios/html/fichaPaciente/template").nomeTemplate("fichaPaciente").datasource(parametros).build()).getContent();
    }

    private void validarEntrada(RelatorioFichaPacienteInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new RelatorioFichaPacienteException("Informe o id da empresa.");
        }

        if (Objects.isNull(input.getPacienteId())) {
            throw new RelatorioFichaPacienteException("Informe o id do paciente.");
        }
    }

    private FichaPaciente buscarFichaPaciente(RelatorioFichaPacienteInput input) {
        List<FichaPaciente> fichaPacienteList =  fichaPacienteDataProvider.buscarPorPaciente(input.getPacienteId());

        if(!fichaPacienteList.isEmpty())
            return fichaPacienteList.get(0);

        return FichaPaciente.builder().build();
    }

    private Paciente buscarPaciente(RelatorioFichaPacienteInput input) {
        return pacienteDataProvider.buscarPorId(input.getPacienteId(), input.getEmpresaId()).orElseThrow(() -> new RelatorioFichaPacienteException("Paciente não encontrado."));
    }
}

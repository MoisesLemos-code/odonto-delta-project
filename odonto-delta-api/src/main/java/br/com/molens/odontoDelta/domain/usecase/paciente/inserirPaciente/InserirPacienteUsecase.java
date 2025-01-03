package br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente;

import br.com.molens.odontoDelta.domain.exception.InserirPacienteException;
import br.com.molens.odontoDelta.domain.exception.JaExistePacienteCnpjCpfException;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.fichaPaciente.inserirFichaPaciente.InserirFichaPacienteUsecase;
import br.com.molens.odontoDelta.domain.usecase.paciente.inserirPaciente.converter.InserirPacienteOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Municipio;
import br.com.molens.odontoDelta.utils.HelpUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class InserirPacienteUsecase {

    private PacienteDataProvider pacienteDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private MunicipioDataProvider municipioDataProvider;
    private InserirFichaPacienteUsecase inserirFichaPacienteUsecase;
    private InserirPacienteOutputConverter outputConverter;

    public InserirPacienteOutput executar(InserirPacienteInput input) {
        validarDadosEntrada(input);
        validarEmpresa(input);
        validarMunicipio(input);
        validarPacienteJaCadastrado(input);
        Long pacienteId = inserirPaciente(input);
        inserirFichaPaciente(pacienteId, input.getEmpresaId());

        return InserirPacienteOutput.builder().pacienteId(pacienteId).build();
    }

    private void validarDadosEntrada(InserirPacienteInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirPacienteException("Identificador de empresa inválido.");
        }
    }

    private void validarEmpresa(InserirPacienteInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());
        if (!empresa.isPresent()) {
            throw new InserirPacienteException("Empresa não identificada.");
        }
    }

    private void validarMunicipio(InserirPacienteInput input) {
        Optional<Municipio> municipio = municipioDataProvider.buscarPorId(input.getEmpresaId());
        if (!municipio.isPresent()) {
            throw new InserirPacienteException("Municipio não identificado.");
        }
    }

    private void validarPacienteJaCadastrado(InserirPacienteInput input) {
        if (pacienteDataProvider.existeCnpjCpf(HelpUtil.obterApenasNumeros(input.getCnpjCpf()), input.getEmpresaId())) {
            throw new JaExistePacienteCnpjCpfException();
        }
    }

    private Long inserirPaciente(InserirPacienteInput input) {
        Long pacienteId = pacienteDataProvider.inserir(outputConverter.from(input));

        return pacienteId;
    }

    private void inserirFichaPaciente(Long pacienteId, Long empresaId) {
        inserirFichaPacienteUsecase.executar(InserirFichaPacienteInput.builder()
                .pacienteId(pacienteId)
                .empresaId(empresaId)
                .build());
    }
}

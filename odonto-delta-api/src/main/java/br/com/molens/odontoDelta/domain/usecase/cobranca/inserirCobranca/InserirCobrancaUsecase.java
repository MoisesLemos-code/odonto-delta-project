package br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca;

import br.com.molens.odontoDelta.domain.exception.InserirPacienteException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PacienteDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.converter.InserirCobrancaOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class InserirCobrancaUsecase {

    private CobrancaDataProvider cobrancaDataProvider;
    private PacienteDataProvider pacienteDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private InserirCobrancaOutputConverter outputConverter;

    public InserirCobrancaOutput executar(InserirCobrancaInput input) {
        validarDadosEntrada(input);
        validarEmpresa(input);
        validarPaciente(input);
        Long cobrancaId = inserirCobranca(input);

        return InserirCobrancaOutput.builder().cobrancaId(cobrancaId).build();
    }

    private void validarDadosEntrada(InserirCobrancaInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirPacienteException("Identificador de empresa inválido.");
        }

        if (Objects.isNull(input.getPacienteId())) {
            throw new InserirPacienteException("Identificador de paciente inválido.");
        }
    }

    private void validarEmpresa(InserirCobrancaInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());
        if (!empresa.isPresent()) {
            throw new InserirPacienteException("Empresa não identificada.");
        }
    }

    private void validarPaciente(InserirCobrancaInput input) {
        Optional<Paciente> paciente = pacienteDataProvider.buscarPorId(input.getPacienteId(), input.getEmpresaId());
        if (!paciente.isPresent()) {
            throw new InserirPacienteException("Paciente não identificado.");
        }
    }

    private Long inserirCobranca(InserirCobrancaInput input) {
        Cobranca cobrancaPre = outputConverter.from(input);
        Integer ultimoId = cobrancaDataProvider.buscarUltimoId(input.getEmpresaId());

        String codigo = gerarCodigoCobranca(cobrancaPre, ultimoId);
        cobrancaPre.setCodigo(codigo);

        Cobranca cobranca = cobrancaDataProvider.inserir(cobrancaPre);

        return cobranca.getId();
    }

    private String gerarCodigoCobranca(Cobranca cobranca, Integer ultimoId) {
        String orcamento = "";
        if(Objects.nonNull(cobranca.getOrcamento()))
            orcamento = "OS" + cobranca.getOrcamento().getId();

        return "CO" + formatarNumeroId(ultimoId) + "PA" + cobranca.getPaciente().getId() + orcamento;
    }

    private static String formatarNumeroId(Integer id) {
        DecimalFormat df = new DecimalFormat("000000");
        return df.format(id);
    }
}

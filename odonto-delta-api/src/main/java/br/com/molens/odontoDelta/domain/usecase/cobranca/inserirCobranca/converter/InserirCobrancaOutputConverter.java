package br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.converter;

import br.com.molens.odontoDelta.domain.exception.DataEmFormatoIncorretoException;
import br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca.InserirCobrancaInput;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Orcamento;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class InserirCobrancaOutputConverter extends GenericConverter<Cobranca, InserirCobrancaInput> {

    @Override
    public Cobranca from(InserirCobrancaInput source) {
        Cobranca target = super.from(source);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if(Objects.nonNull(source.getDataVencimento())){
                target.setDataVencimento(sdf.parse(source.getDataVencimento()));
            }

            if (Objects.nonNull(source.getEmpresaId())) {
                target.setEmpresa(Empresa.builder()
                        .id(source.getEmpresaId())
                        .build());
            }

            if (Objects.nonNull(source.getPacienteId())) {
                target.setPaciente(Paciente.builder()
                        .id(source.getPacienteId())
                        .build());
            }

            if (Objects.nonNull(source.getOrcamentoId())) {
                target.setOrcamento(Orcamento.builder()
                        .id(source.getOrcamentoId())
                        .build());
            }

            if(Objects.nonNull(source.getStatus())){
                target.setStatus(source.getStatus());
            }

        } catch (ParseException e) {
            throw new DataEmFormatoIncorretoException();
        }

        return target;
    }
}
package br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.converter;

import br.com.molens.odontoDelta.domain.exception.DataEmFormatoIncorretoException;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdOutput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Municipio;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.HelpUtil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class AtualizarPacienteOutputConverter extends GenericConverter<Paciente, AtualizarPacienteInput> {

    @Override
    public Paciente from(AtualizarPacienteInput source) {
        Paciente target = super.from(source);

        try {

            target.setId(source.getPacienteId());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if(Objects.nonNull(source.getDataNascimento())){
                target.setDataNascimento(sdf.parse(source.getDataNascimento()));
            }
            target.setCnpjCpf(HelpUtil.obterApenasNumeros(source.getCnpjCpf()));
            target.setCep(HelpUtil.obterApenasNumeros(source.getCep()));
            target.setTelefone(HelpUtil.obterApenasNumeros(source.getTelefone()));

            if (Objects.nonNull(source.getEmpresaId())) {
                target.setEmpresa(Empresa.builder()
                        .id(source.getEmpresaId())
                        .build());
            }

            if (Objects.nonNull(source.getMunicipioId())) {
                target.setMunicipio(Municipio.builder()
                        .id(source.getMunicipioId())
                        .build());
            }

        } catch (ParseException e) {
            throw new DataEmFormatoIncorretoException();
        }

        return target;
    }
}
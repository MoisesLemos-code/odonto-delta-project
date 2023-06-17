package br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.converter;

import br.com.molens.odontoDelta.domain.exception.DataEmFormatoIncorretoException;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteInput;
import br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente.AtualizarPacienteOuput;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class AtualizarPacienteOutputConverter extends GenericConverter<Paciente, AtualizarPacienteOuput> {

    @Override
    public AtualizarPacienteOuput to(Paciente source) {
        AtualizarPacienteOuput target = super.to(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }


        return target;
    }

    public Paciente from(AtualizarPacienteInput source) {
        Paciente target = new Paciente();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            target.setId(source.getPacienteId());
            target.setNome(source.getNome());
            target.setEmail(source.getEmail());
            target.setCnpjCpf(source.getCnpjCpf());
            target.setTelefone(source.getTelefone());
            target.setRg(source.getRg());
            target.setDataNascimento(sdf.parse(source.getDataNascimento()));
            target.setCep(source.getCep());
            target.setBairro(source.getBairro());
            target.setLogradouro(source.getLogradouro());
            target.setLogradouroNumero(source.getLogradouroNumero());
            target.setComplemento(source.getComplemento());
            target.setSituacao(source.getSituacao());

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
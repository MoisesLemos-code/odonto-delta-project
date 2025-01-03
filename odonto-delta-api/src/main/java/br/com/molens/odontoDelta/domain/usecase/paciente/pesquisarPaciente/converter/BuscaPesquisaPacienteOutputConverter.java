package br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente.converter;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente.PesquisarPacienteOutput;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.stream.Collectors;

public class BuscaPesquisaPacienteOutputConverter {

    public PesquisarPacienteOutput to(ListaPaginada<Paciente> from) {
       OutputItemConverter outputDataItemConverter = new OutputItemConverter();

        return PesquisarPacienteOutput
                .builder()
                .totalElements(from.getTotalElements())
                .totalPages(from.getTotalPages())
                .items(from
                        .getItems()
                        .stream()
                        .map(outputDataItemConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

    private static class OutputItemConverter extends GenericConverter<Paciente, PesquisarPacienteOutput.Item> {
    }
}

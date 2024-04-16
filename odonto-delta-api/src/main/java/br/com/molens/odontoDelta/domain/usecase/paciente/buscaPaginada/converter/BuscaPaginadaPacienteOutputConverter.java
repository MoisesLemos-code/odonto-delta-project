package br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteOutput;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilOutput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Objects;
import java.util.stream.Collectors;

public class BuscaPaginadaPacienteOutputConverter {

    public BuscaPaginadaPacienteOutput to(ListaPaginada<Paciente> from) {
        BuscarClientesOutputItemConverter outputDataItemConverter = new BuscarClientesOutputItemConverter();

        return BuscaPaginadaPacienteOutput
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

    private static class BuscarClientesOutputItemConverter extends GenericConverter<Paciente, BuscaPaginadaPacienteOutput.Item> {

        @Override
        public BuscaPaginadaPacienteOutput.Item to(Paciente source) {
            BuscaPaginadaPacienteOutput.Item target = super.to(source);

            if (Objects.nonNull(source.getMunicipio())) {
                target.setCidade(source.getMunicipio().getNome());
                target.setEstado(source.getMunicipio().getEstado().getSigla());
            }

            return target;
        }
    }
}

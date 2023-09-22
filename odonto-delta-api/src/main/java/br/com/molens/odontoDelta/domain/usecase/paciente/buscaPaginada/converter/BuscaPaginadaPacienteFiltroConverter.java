package br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada.BuscaPaginadaPacienteInput;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaPaginadaPacienteFiltroConverter extends GenericConverter<BuscaPaginadaPacienteInput, Paciente.Filtro> {

}
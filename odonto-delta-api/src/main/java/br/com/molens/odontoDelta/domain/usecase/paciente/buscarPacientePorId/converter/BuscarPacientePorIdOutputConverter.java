package br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.converter;

import br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId.BuscarPacientePorIdOutput;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Paciente;
import br.com.molens.odontoDelta.utils.HelpUtil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class BuscarPacientePorIdOutputConverter extends GenericConverter<Paciente, BuscarPacientePorIdOutput> {

    @Override
    public BuscarPacientePorIdOutput to(Paciente source) {
        BuscarPacientePorIdOutput target = super.to(source);

        if(Objects.nonNull(source.getDataNascimento())){
            target.setIdade(HelpUtil.obterIdade(source.getDataNascimento()));
        }

        if (Objects.nonNull(source.getMunicipio())) {
            target.setCidade(source.getMunicipio().getNome());
            target.setEstado(source.getMunicipio().getEstado().getSigla());
            target.setMunicipioId(source.getMunicipio().getId());
            target.setEstadoId(source.getMunicipio().getEstado().getId());
        }

        return target;
    }
}
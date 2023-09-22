package br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.converter;

import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.BuscaPaginadaPerfilInput;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscaPaginadaPerfilFiltroConverter extends GenericConverter<BuscaPaginadaPerfilInput, Perfil.Filtro> {

}
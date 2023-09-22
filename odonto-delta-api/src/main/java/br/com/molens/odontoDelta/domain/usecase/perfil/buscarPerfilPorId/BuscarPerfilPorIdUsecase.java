package br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId;

import br.com.molens.odontoDelta.domain.exception.BuscarPacientePorIdException;
import br.com.molens.odontoDelta.domain.exception.BuscarPerfilPorIdException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscarPerfilPorId.converter.BuscarPerfilPorIdOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BuscarPerfilPorIdUsecase {

    private PerfilDataProvider perfilDataProvider;
    private BuscarPerfilPorIdOutputConverter outputConverter;

    public BuscarPerfilPorIdOutput executar(BuscarPerfilPorIdInput input) {
        validarDadosEntrada(input);
        return outputConverter.to(buscarPorId(input));
    }

    private void validarDadosEntrada(BuscarPerfilPorIdInput input) {

        if (input.getPerfilId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de perfil inválido.");
        }
        if (input.getEmpresaId() == 0) {
            throw new BuscarPacientePorIdException("Identificador de empresa inválido.");
        }
    }

    private Perfil buscarPorId(BuscarPerfilPorIdInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId());

        if (!perfil.isPresent()) {
            throw new BuscarPerfilPorIdException("Perfil não encontrado.");
        }
        return perfil.get();
    }
}

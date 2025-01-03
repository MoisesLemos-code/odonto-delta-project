package br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil;

import br.com.molens.odontoDelta.domain.exception.AtualizarPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil.converter.AtualizarPerfilOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarPerfilUsecase {

    private PerfilDataProvider perfilDataProvider;
    private AtualizarPerfilOutputConverter outputConverter;

    public AtualizarPerfilOutput executar(AtualizarPerfilInput input) {
        validarDadosEntrada(input);
        buscarPerfil(input);
        validarPerfilNomeJaCadastrado(input);
        Perfil perfil = atualizarPerfil(outputConverter.from(input));
        return outputConverter.to(perfil);
    }

    private void validarDadosEntrada(AtualizarPerfilInput input) {

        if (Objects.isNull(input.getId())) {
            throw new AtualizarPerfilException("Identificador de perfil é obrigatório.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new AtualizarPerfilException("Identificador de empresa inválido.");
        }
    }

    private void buscarPerfil(AtualizarPerfilInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorId(input.getId(), input.getEmpresaId());

        if (!perfil.isPresent()) {
            throw new AtualizarPerfilException("Perfil não encontrado.");
        }
        if(perfil.get().getNome().equals("Administrador")){
            throw new AtualizarPerfilException("Não é possível atualizar o perfil administrador!");
        }
    }

    private void validarPerfilNomeJaCadastrado(AtualizarPerfilInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorNome(input.getNome(), input.getEmpresaId());
        if (perfil.isPresent()) {
            if (!Objects.equals(perfil.get().getId(), input.getId())) {
                throw new AtualizarPerfilException("Já existe um perfil com esse nome!");
            }
        }
    }

    private Perfil atualizarPerfil(Perfil perfil) {
        return perfilDataProvider.atualizar(perfil);
    }

}

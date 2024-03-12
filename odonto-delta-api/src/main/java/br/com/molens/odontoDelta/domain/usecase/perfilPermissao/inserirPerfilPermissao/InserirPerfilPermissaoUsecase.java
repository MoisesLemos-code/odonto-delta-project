package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.InserirPerfilException;
import br.com.molens.odontoDelta.domain.exception.InserirPerfilPermissaoException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class InserirPerfilPermissaoUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PerfilDataProvider perfilDataProvider;
    private PermissaoDataProvider permissaoDataProvider;
    public InserirPerfilPermissaoOutput executar(InserirPerfilPermissaoInput input) {
        validarDadosEntrada(input);
        validarSeJaExiste(input);
        Perfil perfil = validarPerfil(input);
        Permissao permissao = validarPermissao(input);
        PerfilPermissao perfilPermissao = setarDados(perfil, permissao);
        return inserirPerfilPermissao(perfilPermissao);
    }

    private void validarSeJaExiste(InserirPerfilPermissaoInput input) {
        List<PerfilPermissao> perfilPermissao = perfilPermissaoDataProvider.buscarPorPermissaoEhPerfil(input.getPermissaoId(), input.getPerfilId());
        if (!perfilPermissao.isEmpty()) {
            throw new InserirPerfilPermissaoException("Essa permissão já está inserida nesse perfil.");
        }
    }

    private void validarDadosEntrada(InserirPerfilPermissaoInput input) {

        if (Objects.isNull(input.getPerfilId())) {
            throw new InserirPerfilPermissaoException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getPermissaoId())) {
            throw new InserirPerfilPermissaoException("Identificador de permissão inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirPerfilException("Identificador de empresa inválido.");
        }
    }

    private Perfil validarPerfil(InserirPerfilPermissaoInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId());
        if (!perfil.isPresent()) {
            throw new InserirPerfilPermissaoException("Perfil não identificado.");
        }
        return perfil.get();
    }

    private Permissao validarPermissao(InserirPerfilPermissaoInput input) {
        Optional<Permissao> permissao = permissaoDataProvider.buscarPorId(input.getPermissaoId());
        if (!permissao.isPresent()) {
            throw new InserirPerfilPermissaoException("Permissão não identificada.");
        }
        return permissao.get();
    }

    private PerfilPermissao setarDados(Perfil perfil, Permissao permissao) {
        return PerfilPermissao.builder()
                .ativo(Boolean.TRUE)
                .perfil(perfil)
                .permissao(permissao)
                .build();
    }

    private InserirPerfilPermissaoOutput inserirPerfilPermissao(PerfilPermissao perfilPermissao) {
        Long perfilPermissaoId = perfilPermissaoDataProvider.inserir(perfilPermissao);

        return new InserirPerfilPermissaoOutput(perfilPermissaoId);
    }

}

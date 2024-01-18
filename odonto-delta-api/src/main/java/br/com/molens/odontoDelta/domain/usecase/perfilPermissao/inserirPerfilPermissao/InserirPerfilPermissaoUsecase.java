package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.inserirPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.InserirPerfilException;
import br.com.molens.odontoDelta.domain.exception.InserirPerfilPermissaoException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class InserirPerfilPermissaoUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PerfilDataProvider perfilDataProvider;
    public InserirPerfilPermissaoOutput executar(InserirPerfilPermissaoInput input) {
        validarDadosEntrada(input);
        Perfil perfil = validarPerfil(input);
        PerfilPermissao perfilPermissao = setarDados(input, perfil);
        return inserirPerfilPermissao(perfilPermissao);
    }

    private void validarDadosEntrada(InserirPerfilPermissaoInput input) {

        if (Objects.isNull(input.getPerfilId()) || input.getPerfilId() == 0) {
            throw new InserirPerfilPermissaoException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId()) || input.getEmpresaId() == 0) {
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

    private PerfilPermissao setarDados(InserirPerfilPermissaoInput input, Perfil perfil) {
        return PerfilPermissao.builder()
                .nome(input.getNome())
                .descricao(input.getDescricao())
                .ativo(Boolean.TRUE)
                .perfil(perfil)
                .build();
    }

    private InserirPerfilPermissaoOutput inserirPerfilPermissao(PerfilPermissao perfilPermissao) {
        Long perfilPermissaoId = perfilPermissaoDataProvider.inserir(perfilPermissao);

        return new InserirPerfilPermissaoOutput(perfilPermissaoId);
    }

}

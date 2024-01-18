package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.AtualizarPerfilPermissaoException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class AtualizarPerfilPermissaolUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PerfilDataProvider perfilDataProvider;

    public AtualizarPerfilPermissaoOutput executar(AtualizarPerfilPermissaoInput input) {
        validarDadosEntrada(input);
        validarPerfil(input);
        buscarPerfilPermissao(input);
        PerfilPermissao perfilPermissao = setarDados(input);
        return atualizarPermissao(perfilPermissao);
    }

    private void validarDadosEntrada(AtualizarPerfilPermissaoInput input) {

        if (Objects.isNull(input.getPerfilId()) || input.getPerfilId() == 0) {
            throw new AtualizarPerfilPermissaoException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId()) || input.getEmpresaId() == 0) {
            throw new AtualizarPerfilPermissaoException("Identificador de empresa inválido.");
        }
    }

    private void validarPerfil(AtualizarPerfilPermissaoInput input) {
        perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId()).orElseThrow( () -> new AtualizarPerfilPermissaoException("Perfil não identificado."));
    }

    private void buscarPerfilPermissao(AtualizarPerfilPermissaoInput input){
        perfilPermissaoDataProvider.buscarPorId(input.getId()).orElseThrow( () -> new AtualizarPerfilPermissaoException("Permissão não identificada."));
    }

    private PerfilPermissao setarDados(AtualizarPerfilPermissaoInput input) {
        return PerfilPermissao.builder()
                .id(input.getId())
                .nome(input.getNome())
                .perfil(Perfil.builder().id(input.getId()).build())
                .descricao(input.getDescricao())
                .ativo(input.getAtivo())
                .build();
    }

    private AtualizarPerfilPermissaoOutput atualizarPermissao(PerfilPermissao perfilPermissao) {
        perfilPermissaoDataProvider.atualizar(perfilPermissao);
        return AtualizarPerfilPermissaoOutput.builder()
                .id(perfilPermissao.getId())
                .nome(perfilPermissao.getNome())
                .descricao(perfilPermissao.getDescricao())
                .ativo(perfilPermissao.getAtivo())
                .perfil(Perfil.builder()
                        .id(perfilPermissao.getPerfil().getId())
                        .build())
                .build();
    }

}

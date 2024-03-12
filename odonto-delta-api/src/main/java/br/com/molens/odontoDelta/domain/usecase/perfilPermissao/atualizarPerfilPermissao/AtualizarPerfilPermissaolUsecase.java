package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.atualizarPerfilPermissao;

import br.com.molens.odontoDelta.domain.exception.AtualizarPerfilPermissaoException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class AtualizarPerfilPermissaolUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;

    public AtualizarPerfilPermissaoOutput executar(AtualizarPerfilPermissaoInput input) {
        validarDadosEntrada(input);
        PerfilPermissao perfilPermissao = buscarPerfilPermissao(input);
        perfilPermissao.setAtivo(input.getAtivo());
        return atualizarPermissao(perfilPermissao);
    }

    private void validarDadosEntrada(AtualizarPerfilPermissaoInput input) {

        if (Objects.isNull(input.getId())) {
            throw new AtualizarPerfilPermissaoException("Identificador de id inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new AtualizarPerfilPermissaoException("Identificador de empresa inválido.");
        }
    }
    private PerfilPermissao buscarPerfilPermissao(AtualizarPerfilPermissaoInput input){
        return perfilPermissaoDataProvider.buscarPorId(input.getId()).orElseThrow( () -> new AtualizarPerfilPermissaoException("Permissão não identificada."));
    }

    private AtualizarPerfilPermissaoOutput atualizarPermissao(PerfilPermissao perfilPermissao) {
        perfilPermissaoDataProvider.atualizar(perfilPermissao);
        return AtualizarPerfilPermissaoOutput.builder()
                .id(perfilPermissao.getId())
                .ativo(perfilPermissao.getAtivo())
                .perfil(perfilPermissao.getPerfil())
                .permissao(perfilPermissao.getPermissao())
                .build();
    }

}

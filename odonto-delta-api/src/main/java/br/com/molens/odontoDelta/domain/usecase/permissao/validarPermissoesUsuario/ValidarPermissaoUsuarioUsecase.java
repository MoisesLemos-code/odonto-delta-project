package br.com.molens.odontoDelta.domain.usecase.permissao.validarPermissoesUsuario;

import br.com.molens.odontoDelta.domain.exception.ValidarPermissaoUsuarioException;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioUsecase;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class ValidarPermissaoUsuarioUsecase {

    private BuscarPermissoesUsuarioUsecase buscarPermissoesUsuarioUsecase;

    public Boolean executar(ValidarPermissaoUsuarioInput input) {
        validarDadosEntrada(input);
        BuscarPermissoesUsuarioOutput outputPermissoes = buscarPermissoesUsuario(input);

        return isPermitido(outputPermissoes, input.getPermissao());
    }

    private void validarDadosEntrada(ValidarPermissaoUsuarioInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new ValidarPermissaoUsuarioException("Identificador de empresa inválido.");
        }
        if (Objects.isNull(input.getUsuarioId())) {
            throw new ValidarPermissaoUsuarioException("Identificador de usuário inválido.");
        }
        if (Objects.isNull(input.getPermissao())) {
            throw new ValidarPermissaoUsuarioException("Identificador de permissão inválido.");
        }
    }

    private BuscarPermissoesUsuarioOutput buscarPermissoesUsuario(ValidarPermissaoUsuarioInput input) {
        return buscarPermissoesUsuarioUsecase.executar(BuscarPermissoesUsuarioInput.builder()
                .usuarioId(input.getUsuarioId())
                .empresaId(input.getEmpresaId())
                .build());
    }

    private Boolean isPermitido(BuscarPermissoesUsuarioOutput outputPermissoes, String permissaoNecessaria) {

        if(!outputPermissoes.getItems().isEmpty()){
            Optional<BuscarPermissoesUsuarioOutput.Permissao> permissao = outputPermissoes.getItems().stream().filter(item -> item.getNome().equals(permissaoNecessaria)).findFirst();
            return permissao.isPresent();
        }

        return Boolean.FALSE;
    }
}

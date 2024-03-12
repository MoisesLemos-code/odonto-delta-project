package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPerfilPermissoesPorPerfil;

import br.com.molens.odontoDelta.domain.exception.BuscarPermissaoPorPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarPerfilPermissoesPorPerfilUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PerfilDataProvider perfilDataProvider;

    public List<PerfilPermissao> executar(BuscarPermissoesPorPerfilInput input) {
        validarDadosEntrada(input);
        validarPerfil(input);
        return buscar(input);
    }

    private void validarDadosEntrada(BuscarPermissoesPorPerfilInput input) {

        if (Objects.isNull(input.getPerfilId())) {
            throw new BuscarPermissaoPorPerfilException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscarPermissaoPorPerfilException("Identificador de empresa inválido.");
        }
    }

    private void validarPerfil(BuscarPermissoesPorPerfilInput input) {
        perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId()).orElseThrow( () -> new BuscarPermissaoPorPerfilException("Perfil não identificado."));
    }

    private List<PerfilPermissao> buscar(BuscarPermissoesPorPerfilInput input){
        return perfilPermissaoDataProvider.buscarPorPerfil(input.getPerfilId());
    }

}

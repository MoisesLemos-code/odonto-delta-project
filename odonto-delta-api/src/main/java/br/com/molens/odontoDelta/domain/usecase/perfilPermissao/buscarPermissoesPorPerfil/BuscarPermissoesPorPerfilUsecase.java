package br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil;

import br.com.molens.odontoDelta.domain.exception.BuscarPermissaoPorPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilPermissaoDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfilPermissao.buscarPermissoesPorPerfil.converter.BuscarPermissoesPorPerfilOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.PerfilPermissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarPermissoesPorPerfilUsecase {

    private PerfilPermissaoDataProvider perfilPermissaoDataProvider;
    private PerfilDataProvider perfilDataProvider;
    private BuscarPermissoesPorPerfilOutputConverter outputConverter;

    public BuscarPermissoesPorPerfilOutput executar(BuscarPermissoesPorPerfilInput input) {
        validarDadosEntrada(input);
        validarPerfil(input);
        List<PerfilPermissao> permissaoList = buscarPermissoes(input);
        return outputConverter.to(permissaoList);
    }

    private void validarDadosEntrada(BuscarPermissoesPorPerfilInput input) {

        if (Objects.isNull(input.getPerfilId()) || input.getPerfilId() == 0) {
            throw new BuscarPermissaoPorPerfilException("Identificador de perfil inválido.");
        }
        if (Objects.isNull(input.getEmpresaId()) || input.getEmpresaId() == 0) {
            throw new BuscarPermissaoPorPerfilException("Identificador de empresa inválido.");
        }
    }

    private void validarPerfil(BuscarPermissoesPorPerfilInput input) {
        perfilDataProvider.buscarPorId(input.getPerfilId(), input.getEmpresaId()).orElseThrow( () -> new BuscarPermissaoPorPerfilException("Perfil não identificado."));
    }

    private List<PerfilPermissao> buscarPermissoes(BuscarPermissoesPorPerfilInput input){
        return perfilPermissaoDataProvider.buscarPorPerfil(input.getPerfilId());
    }

}

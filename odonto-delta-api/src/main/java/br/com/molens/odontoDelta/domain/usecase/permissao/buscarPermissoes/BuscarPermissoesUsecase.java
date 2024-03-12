package br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes;

import br.com.molens.odontoDelta.domain.exception.InserirPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarPermissoesUsecase {

    private PermissaoDataProvider permissaoDataProvider;

    public List<Permissao> executar(BuscarPermissoesInput input){
        validarDadosEntrada(input);
        return buscarTodasPermissoes();
    }

    private void validarDadosEntrada(BuscarPermissoesInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirPerfilException("Identificador de empresa inválido.");
        }
    }

    private List<Permissao> buscarTodasPermissoes() {
        return permissaoDataProvider.buscarTodas();
    }
}

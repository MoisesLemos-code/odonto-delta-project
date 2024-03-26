package br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes;

import br.com.molens.odontoDelta.domain.exception.InserirPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarPermissoesUsecase {

    private PermissaoDataProvider permissaoDataProvider;

    public BuscarPermissoesOutput executar(BuscarPermissoesInput input){
        validarDadosEntrada(input);
        List<Permissao> permissaoList = buscarTodasPermissoes();
        return setarDados(permissaoList);
    }

    private void validarDadosEntrada(BuscarPermissoesInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirPerfilException("Identificador de empresa inválido.");
        }
    }

    private List<Permissao> buscarTodasPermissoes() {
        return permissaoDataProvider.buscarTodas();
    }

    private BuscarPermissoesOutput setarDados(List<Permissao> permissaoList) {
        List<BuscarPermissoesOutput.Permissao> list = new ArrayList<>();

        for(Permissao permissao : permissaoList){
            BuscarPermissoesOutput.Permissao item = new BuscarPermissoesOutput.Permissao();
            item.setId(permissao.getId());
            item.setNome(permissao.getNome());
            item.setDescricao(permissao.getDescricao());
            item.setAtivo(Boolean.FALSE);
            list.add(item);
        }

        return BuscarPermissoesOutput.builder()
                .items(list)
                .build();
    }
}

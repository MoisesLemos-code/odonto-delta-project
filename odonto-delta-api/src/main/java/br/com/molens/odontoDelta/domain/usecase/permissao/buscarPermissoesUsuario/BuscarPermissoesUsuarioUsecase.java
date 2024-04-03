package br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario;

import br.com.molens.odontoDelta.domain.exception.BuscarPermissoesUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.PermissaoDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
public class BuscarPermissoesUsuarioUsecase {

    private PermissaoDataProvider permissaoDataProvider;

    public BuscarPermissoesUsuarioOutput executar(BuscarPermissoesUsuarioInput input){
        validarDadosEntrada(input);
        List<Permissao> permissaoList = buscarTodasPermissoesPorUsuario(input);
        permissaoList = permissaoList.stream().filter(distinctByKey(Permissao::getId)).collect(Collectors.toList());
        return setarDados(permissaoList);
    }

    private void validarDadosEntrada(BuscarPermissoesUsuarioInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscarPermissoesUsuarioException("Identificador de empresa inválido.");
        }
        if (Objects.isNull(input.getUsuarioId())) {
            throw new BuscarPermissoesUsuarioException("Identificador de usuário inválido.");
        }
    }

    private List<Permissao> buscarTodasPermissoesPorUsuario(BuscarPermissoesUsuarioInput input) {
        return permissaoDataProvider.buscarTodasPermissoesPorUsuario(input.getUsuarioId());
    }

    private BuscarPermissoesUsuarioOutput setarDados(List<Permissao> permissaoList) {
        List<BuscarPermissoesUsuarioOutput.Permissao> list = new ArrayList<>();

        for(Permissao permissao : permissaoList){
            BuscarPermissoesUsuarioOutput.Permissao item = new BuscarPermissoesUsuarioOutput.Permissao();
            item.setId(permissao.getId());
            item.setNome(permissao.getNome());
            item.setDescricao(permissao.getDescricao());
            item.setAtivo(Boolean.FALSE);
            list.add(item);
        }

        return BuscarPermissoesUsuarioOutput.builder()
                .items(list)
                .build();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}

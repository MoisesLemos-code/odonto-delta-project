package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado.exception.BuscarUsuariosPaginadosException;
import br.com.molens.odontoDelta.gateway.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscarUsuariosPaginadosUsecase {

    private UsuarioDataProvider usuarioDataProvider;

    public BuscarUsuariosPaginadosOutput executar(BuscarUsuariosPaginadoInput inputData){
        validarEntrada(inputData);
        Usuario.Filtro filtro = criarFiltro(inputData);
        ListaPaginada<Usuario> usuariosList = buscarUsuariosPaginado(filtro);
        return setarDados(usuariosList);
    }

    private void validarEntrada(BuscarUsuariosPaginadoInput inputData) {
        if (Objects.isNull(inputData.getEmpresaId())) {
            throw new BuscarUsuariosPaginadosException("Informe o id da empresa.");
        }
    }

    private Usuario.Filtro criarFiltro(BuscarUsuariosPaginadoInput inputData) {
        Usuario.Filtro filtro = new Usuario.Filtro();
        filtro.setPage(inputData.getPage());
        filtro.setSize(inputData.getSize());
        filtro.setSort(inputData.getSort());
        filtro.setDirection(inputData.getDirection());
        filtro.setEmpresaId(inputData.getEmpresaId());
        return filtro;
    }

    private ListaPaginada<Usuario> buscarUsuariosPaginado(Usuario.Filtro filtro) {
        return usuarioDataProvider.buscarUsuariosPaginado(filtro);
    }

    private BuscarUsuariosPaginadosOutput setarDados(ListaPaginada<Usuario> usuariosList) {

        List<BuscarUsuariosPaginadosOutput.Item> itens = setarDadosOutput(usuariosList.getItems());

        return BuscarUsuariosPaginadosOutput.builder()
                .items(itens)
                .totalElements(usuariosList.getTotalElements())
                .totalPages(usuariosList.getTotalPages())
                .build();
    }

    private List<BuscarUsuariosPaginadosOutput.Item> setarDadosOutput(List<Usuario> items) {
        List<BuscarUsuariosPaginadosOutput.Item> itemList = new ArrayList<>();

        for(Usuario usuario : items){
            BuscarUsuariosPaginadosOutput.Item item = new BuscarUsuariosPaginadosOutput.Item();
            item.setId(usuario.getId());
            item.setNome(usuario.getNome());
            item.setLogin(usuario.getLogin());
            item.setEmail(usuario.getEmail());

            itemList.add(item);
        }
        return itemList;
    }
}

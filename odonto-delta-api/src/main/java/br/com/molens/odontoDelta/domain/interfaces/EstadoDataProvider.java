package br.com.molens.odontoDelta.domain.interfaces;


import br.com.molens.odontoDelta.gateway.dataprovider.entity.Estado;
import java.util.List;

public interface EstadoDataProvider {
    List<Estado> buscarTodos();

}

package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUsuariosPaginadoInput {

    int size;
    int page;
    String nome;
    String sort;
    String direction;
    private Long empresaId;

}

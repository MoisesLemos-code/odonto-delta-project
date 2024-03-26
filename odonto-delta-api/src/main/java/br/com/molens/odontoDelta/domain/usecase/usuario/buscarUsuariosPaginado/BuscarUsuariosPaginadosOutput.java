package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuariosPaginado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUsuariosPaginadosOutput {

    private List<Item> items;
    private Long totalPages;
    private Long totalElements;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item{
        private Long id;
        private String login;
        private String nome;
        private String email;
    }

}

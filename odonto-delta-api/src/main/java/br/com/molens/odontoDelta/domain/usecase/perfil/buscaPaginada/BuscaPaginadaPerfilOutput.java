package br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada;

import br.com.molens.odontoDelta.gateway.entity.Empresa;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPaginadaPerfilOutput {

    private List<Item> items;
    private Long totalPages;
    private Long totalElements;

    @Data
    @NoArgsConstructor
    public static class Item {
        private Long id;
        private String nome;
        private String descricao;
        private Empresa empresa;
        private Boolean ativo;
    }
}

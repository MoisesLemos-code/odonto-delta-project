package br.com.molens.odontoDelta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plano {

    private Long id;
    private String ciclo;
    private String descricao;
    private BigDecimal valor;
    private Boolean disponivel;
}

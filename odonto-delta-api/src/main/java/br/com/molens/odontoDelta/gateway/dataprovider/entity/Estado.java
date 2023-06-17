package br.com.molens.odontoDelta.gateway.dataprovider.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ESTADO", schema = "odonto")
@SequenceGenerator(name = "seq_estado", sequenceName = "odonto.seq_estado", allocationSize = 1)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_estado")
    @Column(name = "uf_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "uf_sigla")
    private String sigla;

    @Column(name = "uf_nome")
    private String nome;

}
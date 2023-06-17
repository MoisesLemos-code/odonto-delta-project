package br.com.molens.odontoDelta.gateway.dataprovider.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_MUNICIPIO", schema = "odonto")
@SequenceGenerator(name = "seq_municipio", sequenceName = "odonto.seq_municipio", allocationSize = 1)
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_municipio")
    @Column(name = "mu_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "mu_nome")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uf_id")
    private Estado estado;

    @Column(name = "mu_cod_ibge")
    private String codigoIbge;

}
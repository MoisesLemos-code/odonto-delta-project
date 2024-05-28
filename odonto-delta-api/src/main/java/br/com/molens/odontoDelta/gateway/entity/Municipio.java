package br.com.molens.odontoDelta.gateway.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_MUNICIPIO", schema = "odonto")
@SequenceGenerator(name = "seq_municipio", sequenceName = "odonto.seq_municipio", allocationSize = 1)
public class Municipio{

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
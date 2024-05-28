package br.com.molens.odontoDelta.gateway.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ESTADO", schema = "odonto")
@SequenceGenerator(name = "seq_estado", sequenceName = "odonto.seq_estado", allocationSize = 1)
public class Estado{

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
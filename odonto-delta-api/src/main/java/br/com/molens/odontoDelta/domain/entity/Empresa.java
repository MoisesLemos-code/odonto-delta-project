package br.com.molens.odontoDelta.domain.entity;

import br.com.molens.odontoDelta.gateway.dataprovider.entity.MunicipioEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.PlanoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    private Long id;
    private String nome;
    private String cnpjCpf;
    private String tipoPessoa;
    private String email;
    private String razaoSocial;
    private String logradouro;
    private String logradouroNumero;
    private Municipio municipio;
    private String telefone;
    private String inscricaoEstadual;
    private Plano plano;

}

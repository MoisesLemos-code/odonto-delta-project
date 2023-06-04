package br.com.molens.odontoDelta.domain.entity;

import br.com.molens.odontoDelta.gateway.dataprovider.listener.AuditListener;
import lombok.Data;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class BaseObject implements Serializable {

    protected LocalDateTime dataCadastro;
    protected LocalDateTime dataAlteracao;
    protected String usuarioCadastro;
    protected String usuarioAlteracao;
}

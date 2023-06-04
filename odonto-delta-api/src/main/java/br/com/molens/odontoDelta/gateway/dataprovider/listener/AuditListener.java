package br.com.molens.odontoDelta.gateway.dataprovider.listener;

import br.com.molens.odontoDelta.domain.entity.BaseObject;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class AuditListener {

    private static final String USUARIO_JOB = "JobSystem";

    @Autowired
    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;

    @PreUpdate
    public void auditUpdate(BaseObject entity) {

        try {
            entity.setUsuarioAlteracao(sessaoUsuarioDataProvider.get().getLogin());
        } catch (Exception e) {
            entity.setUsuarioAlteracao(USUARIO_JOB);
            e.getMessage();
        }
        entity.setDataAlteracao(LocalDateTime.now());
    }

    @PrePersist
    public void auditPersist(BaseObject entity) {

        try {
            entity.setUsuarioCadastro(sessaoUsuarioDataProvider.get().getLogin());
        } catch (Exception e) {
            entity.setUsuarioCadastro(USUARIO_JOB);
            e.getMessage();
        }

        entity.setDataCadastro(LocalDateTime.now());
        entity.setDataAlteracao(LocalDateTime.now());
    }
}
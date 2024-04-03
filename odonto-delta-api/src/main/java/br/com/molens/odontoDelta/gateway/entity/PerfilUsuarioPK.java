package br.com.molens.odontoDelta.gateway.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PerfilUsuarioPK implements Serializable {

    @Column(name = "PF_ID", nullable = false)
    private Long idPerfil;

    @Column(name = "US_ID", nullable = false)
    private Long idUsuario;

}
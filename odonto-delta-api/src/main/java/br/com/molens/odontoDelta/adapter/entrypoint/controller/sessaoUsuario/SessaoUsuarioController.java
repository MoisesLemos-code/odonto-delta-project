package br.com.molens.odontoDelta.adapter.entrypoint.controller.sessaoUsuario;

import br.com.molens.odontoDelta.application.security.JWTUtil;
import br.com.molens.odontoDelta.application.security.ResponseUserBody;
import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdOutput;
import br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId.BuscarUsuarioPorIdUsecase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/auth")
@Controller
@AllArgsConstructor
public class SessaoUsuarioController {

    @Autowired
    private JWTUtil jwtUtil;

    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;
    private BuscarUsuarioPorIdUsecase buscarUsuarioPorIdUsecase;

    @PostMapping("/refreshToken")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        SessaoUsuario user = sessaoUsuarioDataProvider.get();
        String token = jwtUtil.generateToken(user.getLogin());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/userData")
    public ResponseEntity<ResponseUserBody> userData(HttpServletResponse response) {
        BuscarUsuarioPorIdOutput usuario = buscarUsuarioPorIdUsecase.executar(BuscarUsuarioPorIdInput.builder()
                .usuarioId(sessaoUsuarioDataProvider.get().getId())
                .build());
        return ResponseEntity.ok().body(ResponseUserBody.builder()
                        .id(usuario.getId())
                        .login(usuario.getLogin())
                        .nomeCompleto(usuario.getNome())
                        .empresa(ResponseUserBody.Empresa.builder()
                                .id(usuario.getEmpresa().getId())
                                .nome(usuario.getEmpresa().getNome())
                                .razaoSocial(usuario.getEmpresa().getRazaoSocial())
                                .build())
                .build());
    }

}
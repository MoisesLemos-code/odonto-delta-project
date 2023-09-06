package br.com.molens.odontoDelta.adapter.entrypoint.controller.sessaoUsuario;

import br.com.molens.odontoDelta.application.security.JWTUtil;
import br.com.molens.odontoDelta.application.security.ResponseUserBody;
import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData.UserDataUsecase;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
@AllArgsConstructor
public class SessaoUsuarioController {

    @Autowired
    private JWTUtil jwtUtil;

    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;
    private UserDataUsecase userDataUsecase;

    @PostMapping("/refreshToken")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        SessaoUsuario user = sessaoUsuarioDataProvider.get();
        String token = jwtUtil.generateToken(user.getLogin());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/userData")
    public ResponseEntity<ResponseUserBody> userData(HttpServletResponse response) {
        return ResponseEntity.ok().body(userDataUsecase.executar());
    }

}
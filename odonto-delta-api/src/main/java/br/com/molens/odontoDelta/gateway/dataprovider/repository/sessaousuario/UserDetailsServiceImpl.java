package br.com.molens.odontoDelta.gateway.dataprovider.repository.sessaousuario;

import br.com.molens.odontoDelta.application.security.UserSS;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import br.com.molens.odontoDelta.gateway.dataprovider.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLogin(login);

        if(!usuario.isPresent()){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return  UserSS.builder()
                .id(usuario.get().getId())
                .login(usuario.get().getLogin())
                .nomeCompleto(usuario.get().getNome())
                .senha(usuario.get().getSenha())
                .build();
    }
}

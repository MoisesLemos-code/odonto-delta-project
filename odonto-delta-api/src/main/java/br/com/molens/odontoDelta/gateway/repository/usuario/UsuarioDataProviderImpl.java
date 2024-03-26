package br.com.molens.odontoDelta.gateway.repository.usuario;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.entity.QUsuario;
import br.com.molens.odontoDelta.gateway.entity.Usuario;
import br.com.molens.odontoDelta.utils.ConstrutorPaginacao;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioDataProviderImpl implements UsuarioDataProvider {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Optional<Usuario> buscarUsuarioPorLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPorIdEmpresaId(Long usuarioId, Long empresaId) {
        return repository.buscarPorIdEmpresaId(usuarioId, empresaId);
    }

    @Override
    public Long inserir(Usuario usuario) {
        usuario.setId(null);
        Usuario usuarioEntity = repository.save(usuario);

        return usuarioEntity.getId();
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ListaPaginada<Usuario> buscarUsuariosPaginado(Usuario.Filtro filtro) {

        QUsuario qUsuario = QUsuario.usuario;
        BooleanExpression expression = qUsuario.empresa.id.eq(filtro.getEmpresaId());

        if(StringUtils.isNotEmpty(filtro.getNome())){
            BooleanExpression expressionNome = qUsuario.nome.lower().contains(filtro.getNome().toLowerCase());
            expression = expression.and(expressionNome);
        }

        Page<Usuario> entidadesEncontradas = repository.findAll(expression, ConstrutorPaginacao.extrairPaginacao(filtro));

        List<Usuario> entidades = new ArrayList<>(entidadesEncontradas.getContent());

        return ListaPaginada.<Usuario>builder()
                .items(entidades)
                .totalElements(entidadesEncontradas.getTotalElements())
                .totalPages((long) entidadesEncontradas.getTotalPages())
                .build();
    }
}

package br.com.molens.odontoDelta.seguranca;

import br.com.molens.odontoDelta.seguranca.annotation.Autorizacao;
import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AutorizacaoAspect {

    @Autowired
    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;

    @Around("@annotation(br.com.molens.odontoDelta.seguranca.annotation.Autorizacao)")
    public Object validar(ProceedingJoinPoint joinPoint) throws Throwable{
        SessaoUsuario sessaoUsuario = sessaoUsuarioDataProvider.get();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Autorizacao autorizacao = methodSignature.getMethod().getAnnotation(Autorizacao.class);

        validarPermissao(autorizacao, sessaoUsuario);

        return joinPoint.proceed();
    }

    private void validarPermissao(Autorizacao autorizacao, SessaoUsuario sessaoUsuario) {
//        List<String> funcoes = List.of(autorizacao.funcoes());
//        List<String> permissoes = sessaoUsuario.getPermissoes();
//        if (permissoes.stream().noneMatch(permissao -> funcoes.stream().anyMatch(funcao -> funcao.equals(permissao))))
//            throw new AutorizacaoException("Usuário não possui permissão.");
    }
}

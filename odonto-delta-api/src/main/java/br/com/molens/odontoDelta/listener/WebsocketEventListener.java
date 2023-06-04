package br.com.molens.odontoDelta.listener;

import br.com.molens.odontoDelta.domain.entity.InformacoesConexao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;
import java.util.Objects;

@Component
public class WebsocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebsocketConnect(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        List<String> usuarioId = headerAccessor.getNativeHeader("usuarioId");
        if(Objects.nonNull(usuarioId)) {
            InformacoesConexao conexao = InformacoesConexao.builder()
                    .usuarioId(usuarioId.get(0))
                    .sessaoId(headerAccessor.getSessionId())
                    .build();
            notificarConexao(conexao);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        InformacoesConexao conexao = InformacoesConexao.builder()
                .sessaoId(headerAccessor.getSessionId())
                .build();
        notificarDesconexao(conexao);
    }

    private void notificarConexao(InformacoesConexao conexao) {
        messagingTemplate.convertAndSend(
                "/topic/conexaofornecedor",
                conexao
        );
    }

    private void notificarDesconexao(InformacoesConexao conexao) {
        messagingTemplate.convertAndSend(
                "/topic/desconexaofornecedor",
                conexao
        );
    }
}

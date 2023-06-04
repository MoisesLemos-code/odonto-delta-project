package br.com.molens.odontoDelta.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
@Controller
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("127.0.0.1")
    private String brokerRelayHost;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        final String brokerUsername = getEnv("RABBITMQ_USERNAME", "guest");
        final String brokerPassword = getEnv("RABBITMQ_PASSWORD", "guest");
        final String clientBrokerLogin = getEnv("CLIENT_BROKER_LOGIN", "guest");
        final String clientBrokerPassword = getEnv("CLIENT_BROKER_PASSWORD", "guest");

        registry.enableStompBrokerRelay( "/topic")
                .setRelayHost(brokerRelayHost)
                .setSystemLogin(brokerUsername)
                .setSystemPasscode(brokerPassword)
                .setClientLogin(clientBrokerLogin)
                .setClientPasscode(clientBrokerPassword);
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/sockjs")
            .setAllowedOrigins(getAllowedOrigins())
            .withSockJS()
            .setClientLibraryUrl( "https://cdn.jsdelivr.net/npm/sockjs-client@1.5.2/dist/sockjs.min.js" );
    }

    private String[] getAllowedOrigins() {
        return new String[]{
                "*"
        };
    }

    private String getEnv(String nome, String valorPadrao) {
        final String valor = System.getenv(nome);
        return valor != null ? valor : valorPadrao;
    }

}

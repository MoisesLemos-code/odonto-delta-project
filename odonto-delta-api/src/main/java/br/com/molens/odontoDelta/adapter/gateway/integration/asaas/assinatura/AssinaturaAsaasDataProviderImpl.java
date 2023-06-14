package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.assinatura;

import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.AssinaturaAsaas;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.RestUtils;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception.AsaasValidationException;
import br.com.molens.odontoDelta.domain.interfaces.integration.AssinaturaAsaasDataProvider;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class AssinaturaAsaasDataProviderImpl implements AssinaturaAsaasDataProvider {

    @Autowired
    RestUtils restUtils;

    @Override
    public AssinaturaAsaas criarAssinatura(AssinaturaAsaas assinaturaAsaas, String url, String token) {
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), gson.toJson(assinaturaAsaas));

        try {
            Response response = restUtils.post(url, token, body);
            RestUtils.handleStatusError(response, "Problemas na execução da requisição de criação de assinatura.");

            return extrairResposta(response);
        } catch (IOException e) {
            String mensagem = "Falha ao tentar consumir o serviço de criação de assinatura";
            Logger.getGlobal().log(Level.SEVERE, String.format("%s (url: %s)", mensagem, url), e);

            throw new AsaasValidationException(mensagem);
        }
    }

    private AssinaturaAsaas extrairResposta(Response response) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(Objects.requireNonNull(response.body()).string(), AssinaturaAsaas.class);
    }
}

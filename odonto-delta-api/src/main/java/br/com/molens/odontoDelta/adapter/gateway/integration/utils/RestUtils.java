package br.com.molens.odontoDelta.adapter.gateway.integration.utils;

import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.AsaasErrorResponseList;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception.AsaasValidationException;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception.ExceptionList;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RestUtils {

    public static final int HTTP_UNAUTHORIZED = 401;
    public static final String ASAAS_AUTHORIZATION_HEADER_NAME = "access_token";
    public static final String UNAUTHORIZED_MESSAGE = "O token de acesso ao serviço externo é inválido.";

    private OkHttpClient createHttpClient(Request request) {
        if (request.isHttps()) {
            return new OkHttpClient
                    .Builder()
                    .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();
        }
        return new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    public Response execute(Request request) throws IOException {
        return executeRequest(request);
    }

    public Response get(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(new Headers.Builder().add(ASAAS_AUTHORIZATION_HEADER_NAME, token).build())
                .build();
        return executeRequest(request);
    }

    public Response get(String url, String[] headersArr) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headersArr))
                .build();
        return executeRequest(request);
    }

    public Response post(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(new Headers.Builder().add(ASAAS_AUTHORIZATION_HEADER_NAME, getJwt()).build())
                .post(requestBody)
                .build();
        return executeRequest(request);
    }

    public Response post(String url, String token, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(new Headers.Builder().add(ASAAS_AUTHORIZATION_HEADER_NAME, token).build())
                .post(requestBody)
                .build();
        return executeRequest(request);
    }

    public Response post(String url, String[] headersArr, RequestBody requestBody) throws IOException {
        Headers headers = buildHeaders(headersArr);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();
        return executeRequest(request);
    }

    public Response delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(new Headers.Builder().add(ASAAS_AUTHORIZATION_HEADER_NAME, getJwt()).build())
                .delete()
                .build();
        return executeRequest(request);
    }

    public Response delete(String url, String[] headersArr) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headersArr))
                .delete()
                .build();
        return executeRequest(request);
    }

    private Response executeRequest(Request request) throws IOException {
        logRequest(request);
        logHeaders(request.headers());
        Response response = createHttpClient(request).newCall(request).execute();
        logResponse(response);
        return response;
    }

    private static Headers buildHeaders(String[] headersArr) {
        Headers.Builder headers = new Headers.Builder();
        for (String header : headersArr) {
            headers.add(header);
        }
        headers.add(ASAAS_AUTHORIZATION_HEADER_NAME, getJwt());

        return headers.build();
    }

    public static String getJwt() {
        String token = Jwts.builder()
                .setIssuer("externo")
                .setAudience("integrar")
                .signWith(SignatureAlgorithm.HS256, "chave-secreta")
                .compact();

        return String.format("Bearer %s", token);
    }

    private static void logHeaders(Headers headers) {
        log.debug("[HEADERS]: " + headers.toString().replace("\n", "; "));
    }

    private static void logRequest(Request request) {
        log.debug("[REQUEST]: " + request.toString());
    }

    private static void logResponse(Response response) {
        log.debug("[RESPONSE]: " + response.toString());
    }

    public static void handleStatusError(Response response, String messageIfError) {
        if (!response.isSuccessful()) {
            if (response.code() == HTTP_UNAUTHORIZED) {
                log.error(String.format("%s %n %s", response.code(), UNAUTHORIZED_MESSAGE));
                throw new ExceptionList(UNAUTHORIZED_MESSAGE, Collections.singletonList(getSubcause(response, UNAUTHORIZED_MESSAGE)));
            }

            try {
                Gson gson = new Gson();
                AsaasErrorResponseList errors = gson.fromJson(Objects.requireNonNull(response.body()).string(), AsaasErrorResponseList.class);
                throwAsaasOneException(errors);
            } catch (IOException e) {
                log.error(String.format("%s %n %s", response.toString(), messageIfError));
                throw new ExceptionList(messageIfError, Collections.singletonList(getSubcause(response, messageIfError)));
            }
        }
    }

    public static void handleStatusCodeError(Response response, String messageIfError) throws IOException {
        if (!response.isSuccessful()) {
            log.error(String.format("%n Response Error: %s %n Response Status: %s %n Error message: %s", response.message(), response.code(),
                    messageIfError));
            throw new IOException(String.format("%s -- %s", response.code(), messageIfError));
        }
    }

    private static void throwAsaasOneException(AsaasErrorResponseList errors) {
        if (!errors.getErrors().isEmpty()) {
            StringBuilder exceptions = new StringBuilder(errors.getErrors().get(0).getDescription());
            throw new AsaasValidationException(exceptions.toString());
        }
    }

    private static AsaasValidationException getSubcause(Response response, String message) {
        String httpMessage = String.format("http{code:[%s], message:[%s]}", response.code(), response.message());
        StringBuilder stringBuilder = new StringBuilder(httpMessage).append("\n").append(message);
        Optional.ofNullable(response.body()).ifPresent(responseBody -> stringBuilder.append("\n").append(responseBody.toString()));

        return new AsaasValidationException(stringBuilder.toString());
    }
}

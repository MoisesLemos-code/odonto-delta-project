package br.com.molens.odontoDelta.utils;

import br.com.molens.odontoDelta.domain.validation.GenericValidationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RestUtils {

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private String cookieSession = null;

    //TODO parametrizar os timeouts abaixo
    private OkHttpClient createHttpClient(Request request) {

        return new OkHttpClient
                .Builder()
                .hostnameVerifier((hostname, sslSession) -> true)
                .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.RESTRICTED_TLS))
                .connectTimeout(5, TimeUnit.MINUTES)
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        if(Objects.isNull(cookieSession)){
                            cookieSession = cookies.toString();
                        }
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    public Response execute(Request request) throws IOException {
        return executeRequest(request);
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        executeRequest(request);
        return cookieSession;
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
                .headers(new Headers.Builder().add(AUTHORIZATION_HEADER_NAME, getJwt()).build())
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
                .headers(new Headers.Builder().add(AUTHORIZATION_HEADER_NAME, getJwt()).build())
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
        Response response = createHttpClient(request).newCall(request).execute();
        return response;
    }

    private static Headers buildHeaders(String[] headersArr) {
        Headers.Builder headers = new Headers.Builder();
        for (String header : headersArr) {
            headers.add(header);
        }
        headers.add(AUTHORIZATION_HEADER_NAME, getJwt());

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


    private static GenericValidationException getSubcause(Response response, String message) {
        String httpMessage = String.format("http{code:[%s], message:[%s]}", response.code(), response.message());
        StringBuilder stringBuilder = new StringBuilder(httpMessage).append("\n").append(message);
        Optional.ofNullable(response.body()).ifPresent(responseBody -> stringBuilder.append("\n").append(responseBody.toString()));

        return new GenericValidationException(stringBuilder.toString());
    }
}

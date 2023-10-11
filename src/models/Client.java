package models;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Client {
    HttpClient client;
    public Client() {
        client = HttpClient.newHttpClient();
    }
    public CompletableFuture<String> get(String uri) {        
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .build();        

        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> post(String uri, String body, String... token) {
        List<String> headers = genHeaders();
        setToken(headers, token);
        // if(token.length > 0) {
        //     headers.add("Authorizatin");
        //     headers.add("Bearer " + token[0]);
        // }

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .headers(headers.toArray(String[]::new))
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();

        // return client.sendAsync(request, BodyHandlers.ofString())
        // .thenApply(HttpResponse::body);
        return sendRequest(request);
    }

    public CompletableFuture<String> put(String uri, String body, String... token) {        
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");

        if(token.length > 0) {
            headers.add("Authorization");
            headers.add("Bearer " + token[0]);
        }

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .headers(headers.toArray(String[]::new))
        .PUT(HttpRequest.BodyPublishers.ofString(body))
        .build();

        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> delete(String uri, String... token) {
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");

        if(token.length > 0) {
            headers.add("Authorization");
            headers.add("Bearer " + token[0]);
        }

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .headers(headers.toArray(String[]::new))
        .DELETE()
        .build();
        
        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }

    private List<String> genHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");
        return headers;
    }
    private void setToken(List<String> headers, String[] token) {
        if(token.length > 0) {
            headers.add("Authorization");
            headers.add("Bearer " + token[0]);
        }
    }
    public CompletableFuture<String> sendRequest(HttpRequest request) {
        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }
}

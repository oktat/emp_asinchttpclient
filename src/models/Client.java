package models;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class Client {
    HttpClient client;
    public Client() {
        client = HttpClient.newHttpClient();
    }
    public CompletableFuture<String> get(String url) {        
        HttpRequest request = this.generateGetRequest(url);
        return sendRequest(request);
    }

    public CompletableFuture<String> post(String uri, String body, String... token) {
        HttpRequest request = generatePostRequest(uri, body, token);
        return sendRequest(request);
    }

    public CompletableFuture<String> put(String uri, String body, String... token) {        
        HttpRequest request = generatePutRequest(uri, body, token);
        return sendRequest(request);
    }

    public CompletableFuture<String> delete(String uri, String... token) {
        HttpRequest request = generateDeleteRequest(uri, token);        
        return sendRequest(request);
    }

    private HttpRequest generateGetRequest(String url) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.GET();
        
        return builder.build();
    }

    private HttpRequest generatePostRequest(String url, String body, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.POST(HttpRequest.BodyPublishers.ofString(body));
        builder.header("Content-Type", "application/json");
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);
        }        
        return builder.build();
    }

    private HttpRequest generatePutRequest(String url, String body, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.PUT(HttpRequest.BodyPublishers.ofString(body));
        builder.header("Content-Type", "application/json");
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);
        }        
        return builder.build();
    }

    private HttpRequest generateDeleteRequest(String url, String... args) {
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.DELETE();
        if(args.length > 0) {
            String token = args[0];
            builder.header("Authorization", "Bearer " + token);
        }        
        return builder.build();
    }

    public CompletableFuture<String> sendRequest(HttpRequest request) {
        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
    }
}

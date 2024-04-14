package com.conversor.fetchapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

public class FetchApi {
  public HttpResponse<String> getExchangeRates(String currency) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://v6.exchangerate-api.com/v6/a5ac18a09648a8aa1232aefe/latest/" + currency))
        .build();

    return client
        .send(request, BodyHandlers.ofString());
  }
}

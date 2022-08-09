package com.github.coaixy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Post {
    Post(){
        try {
            var values = new HashMap<String, String>() {{
                put("name", "John Doe");
                put ("occupation", "gardener");
            }};

            var objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writeValueAsString(values);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
